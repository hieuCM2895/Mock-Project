package fa.training.impsystem.service.impl;

import fa.training.impsystem.dto.UserDTO;
import fa.training.impsystem.entities.Roles;
import fa.training.impsystem.entities.Users;
import fa.training.impsystem.exception.EmailHadRegistered;
import fa.training.impsystem.exception.EmailNotFoundException;
import fa.training.impsystem.repository.UserDao;
import fa.training.impsystem.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    public static final int MAX_FAILED_ATTEMPTS = 10;

    public static Users checkUser;

    // 24 hours
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean save(UserDTO userDTO) throws EmailHadRegistered {

        if (userDao.findByEmail(userDTO.getEmail().trim()) != null) {
            throw new EmailHadRegistered("Email had Existed in Database");
        }

        Users user = new Users();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRoles(List.of(new Roles(userDTO.getRole())));

        return userDao.save(user) != null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = userDao.findByEmail(email);

        checkUser = user;

        if (user == null) {
            throw new UsernameNotFoundException("User Is Not Exits");
        }

        if (!user.isAccountNonLocked()) {
            unlockWhenTimeExpired(user);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.isEnable(), true, true, user.isAccountNonLocked(), mapRolesToAuthorities(user.getRoles())
        );
    }

    @Override
    public UserDetails loadUserById(int userId) throws UsernameNotFoundException {
        Users user = userDao.findById(userId);

        if (user == null) {
            throw new UsernameNotFoundException("User Is Not Exits");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLastName(), user.getPassword(), mapRolesToAuthorities(user.getRoles())
        );
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        Users user = userDao.findByEmail(email);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    public void increaseFailedAttempts(Users user) {
        int newFailAttempts = user.getFailedAttempt() + 1;
        userDao.updateFailedAttempt(newFailAttempts, user.getEmail());
    }

    public void resetFailedAttempts(String email) {
        userDao.updateFailedAttempt(0, email);
    }

    public void lock(Users user) {
        user.setAccountNonLocked(false);
        user.setLockTime(new Date());

        userDao.save(user);
    }

    public void unlockWhenTimeExpired(Users user) {
        long lockTimeInMillis = user.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            user.setAccountNonLocked(true);
            user.setLockTime(null);
            user.setFailedAttempt(0);

            userDao.save(user);
        }

    }

    public void updateResetPasswordToken(String token, String email) throws EmailNotFoundException {
        Users customer = userDao.findByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            userDao.save(customer);
        } else {
            throw new EmailNotFoundException("Could not find any user with the email " + email);
        }
    }

    public UserDTO getByResetPasswordToken(String token) {
        Users user = userDao.findByResetPasswordToken(token);
        if (user == null) {
            return null;
        }
        return modelMapper.map(user, UserDTO.class);
    }

    public boolean updatePassword(UserDTO userDTO, String newPassword) {
        String encodedPassword = passwordEncoder.encode(newPassword);
        return userDao.updatePasswordToken(userDTO.getEmail(), null, encodedPassword) != 0;
    }
}

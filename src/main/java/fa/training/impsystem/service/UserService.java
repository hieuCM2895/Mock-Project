package fa.training.impsystem.service;

import fa.training.impsystem.dto.UserDTO;
import fa.training.impsystem.exception.EmailHadRegistered;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {

    boolean save(UserDTO userDTO) throws UsernameNotFoundException, EmailHadRegistered;

    UserDetails loadUserById(int userId);

    UserDTO findUserByEmail(String email);
}

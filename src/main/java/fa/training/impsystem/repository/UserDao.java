package fa.training.impsystem.repository;

import fa.training.impsystem.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserDao extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    Users findById(int userId);

    @Query("UPDATE Users u SET u.enable = ?2 WHERE u.id = ?1")
    @Modifying
    void updateEnabledStatus(@Param("id") Long id, @Param("enabled") boolean enable);

    @Query("UPDATE Users u SET u.failedAttempt = ?1 WHERE u.email = ?2")
    @Modifying
    void updateFailedAttempt(int failedAttempt, String email);

    Users findByResetPasswordToken(String token);

    @Query("UPDATE Users u SET u.resetPasswordToken = ?2, u.password = ?3 WHERE u.email = ?1")
    @Modifying
    int updatePasswordToken(String email, String token, String newPassword);
}

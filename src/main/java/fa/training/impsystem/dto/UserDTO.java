package fa.training.impsystem.dto;

import fa.training.impsystem.consts.RegexConst;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserDTO {

    @Pattern(regexp = RegexConst.REGEX_NAME, message = RegexConst.FORMAT_FIRST_NAME)
    @NotBlank(message = "Blank First Name User DTO")
    private String firstName;

    @Pattern(regexp = RegexConst.REGEX_NAME, message = RegexConst.FORMAT_LAST_NAME)
    @NotBlank(message = "Blank Last Name User DTO")
    private String lastName;

    @Pattern(regexp = RegexConst.REGEX_EMAIL, message = RegexConst.FORMAT_EMAIL)
    @NotBlank(message = "Blank Email User DTO")
    private String email;

    @Pattern(regexp = RegexConst.REGEX_PASSWORD, message = RegexConst.FORMAT_PASSWORD)
    @NotBlank(message = "Blank Password User DTO")
    private String password;

    private boolean enabled;
    private boolean accountNonLocked;
    private int failedAttempt;
    private Date lockTime;
    private String resetPasswordToken;
    @NotBlank(message = "Blank Role Of User")
    private String role;

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public UserDTO setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public UserDTO setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public UserDTO setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
        return this;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public UserDTO setLockTime(Date lockTime) {
        this.lockTime = lockTime;
        return this;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public UserDTO setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserDTO setRole(String role) {
        this.role = role;
        return this;
    }
}

package fa.training.impsystem.entities;

import fa.training.impsystem.consts.RegexConst;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
public class Users extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Pattern(regexp = RegexConst.REGEX_NAME, message = RegexConst.FORMAT_FIRST_NAME)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Pattern(regexp = RegexConst.REGEX_NAME, message = RegexConst.FORMAT_LAST_NAME)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    @Pattern(regexp = RegexConst.REGEX_EMAIL, message = RegexConst.FORMAT_EMAIL)
    private String email;

    @Column(name = "password", nullable = false)
    @Pattern(regexp = RegexConst.REGEX_PASSWORD, message = RegexConst.FORMAT_PASSWORD)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enable")
    private boolean enable = true;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked = true;

    @Column(name = "failed_attempt")
    private int failedAttempt = 0;

    @Column(name = "lock_time")
    private Date lockTime;

    @Column(name = "reset_password_token")
    private String resetPasswordToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "role_id"))
    private Collection<Roles> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Recruitment> recruitments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Interview> interviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Report> reports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Account> accounts;

    public Long getId() {
        return id;
    }

    public Users setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Users setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Users setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Users setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Users setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Users setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Users setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public boolean isEnable() {
        return enable;
    }

    public Users setEnable(boolean enable) {
        this.enable = enable;
        return this;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public Users setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public int getFailedAttempt() {
        return failedAttempt;
    }

    public Users setFailedAttempt(int failedAttempt) {
        this.failedAttempt = failedAttempt;
        return this;
    }

    public Date getLockTime() {
        return lockTime;
    }

    public Users setLockTime(Date lockTime) {
        this.lockTime = lockTime;
        return this;
    }

    public String getResetPasswordToken() {
        return resetPasswordToken;
    }

    public Users setResetPasswordToken(String resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
        return this;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public Users setRoles(Collection<Roles> roles) {
        this.roles = roles;
        return this;
    }

    public Set<Recruitment> getRecruitments() {
        return recruitments;
    }

    public Users setRecruitments(Set<Recruitment> recruitments) {
        this.recruitments = recruitments;
        return this;
    }

    public Set<Interview> getInterviews() {
        return interviews;
    }

    public Users setInterviews(Set<Interview> interviews) {
        this.interviews = interviews;
        return this;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public Users setReports(Set<Report> reports) {
        this.reports = reports;
        return this;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public Users setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
        return this;
    }
}

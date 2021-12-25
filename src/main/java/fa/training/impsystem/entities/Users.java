package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Users extends BaseEntity{

    @Id
    @Column(name = "user_name")
    private String userName;

    @NotBlank(message = MessageConst.INVALID_NAME)
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NotBlank(message = MessageConst.INVALID_PHONE)
    @Column(nullable = false, unique = true)
    private String phone;

    @Email(message = MessageConst.INVALID_EMAIL)
    @Column(nullable = false, unique = true)
    private String email;

    @Email(message = MessageConst.INVALID_PASSWORD)
    @Column(nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Recruitment> recruitments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Interview> interviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Report> reports;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Account> accounts;

    public Users() {

    }

    public Users(String userName, String fullName, String phone, String email, String password) {
        this.userName = userName;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Recruitment> getRecruitments() {
        return recruitments;
    }

    public void setRecruitments(Set<Recruitment> recruitments) {
        this.recruitments = recruitments;
    }

    public Set<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(Set<Interview> interviews) {
        this.interviews = interviews;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}

package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name", "role_id"})}
)
public class Account extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private int accountId;

    @NotNull(message = MessageConst.INVALID_ROLES_NULL)
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private Roles roles;

    @NotNull(message = MessageConst.INVALID_USER_NULL)
    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private Users users;

    public Account() {

    }

    public Account(int accountId, Roles roles, Users users) {
        this.accountId = accountId;
        this.roles = roles;
        this.users = users;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}

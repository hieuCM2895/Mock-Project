package fa.training.impsystem.entities;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Roles extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name_role")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roles")
    private Set<Account> accounts;

    public Roles(String role) {

    }

    public Long getId() {
        return id;
    }

    public Roles setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Roles setName(String name) {
        this.name = name;
        return this;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}

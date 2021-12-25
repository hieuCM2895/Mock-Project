package fa.training.impsystem.entities;

import fa.training.impsystem.consts.CommonConst;
import fa.training.impsystem.consts.MessageConst;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Set;

@Entity
public class Recruitment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruitment_id")
    private int recruitmentId;

    @Min(value = 0, message = MessageConst.INVALID_SALARY)
    @Column
    private float salary;

    @Column(name = "date_start", nullable = false)
    @DateTimeFormat(pattern = CommonConst.FORMAT_DATE)
    @Past(message = MessageConst.INVALID_DATE)
    private Date dateStart;

    @Column(name = "date_end", nullable = false)
    @DateTimeFormat(pattern = CommonConst.FORMAT_DATE)
    @Past(message = MessageConst.INVALID_DATE)
    private Date dateEnd;

    @NotNull(message = MessageConst.INVALID_VACANCIES_NULL)
    @ManyToOne
    @JoinColumn(name = "vacancies_id", referencedColumnName = "vacancies_id")
    private Vacancies vacancies;

    @NotNull(message = MessageConst.INVALID_USER_NULL)
    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private Users users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recruitment")
    private Set<SkillRecruitment> skill_recruitments;

    public Recruitment() {

    }

    public Recruitment(int recruitmentId, float salary, Date dateStart, Date dateEnd, Vacancies vacancies, Users users) {
        this.recruitmentId = recruitmentId;
        this.salary = salary;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.vacancies = vacancies;
        this.users = users;
    }

    public int getRecruitmentId() {
        return recruitmentId;
    }

    public void setRecruitmentId(int recruitmentId) {
        this.recruitmentId = recruitmentId;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Vacancies getVacancies() {
        return vacancies;
    }

    public void setVacancies(Vacancies vacancies) {
        this.vacancies = vacancies;
    }

    public Set<SkillRecruitment> getSkill_recruitments() {
        return skill_recruitments;
    }

    public void setSkill_recruitments(Set<SkillRecruitment> skill_recruitments) {
        this.skill_recruitments = skill_recruitments;
    }
}

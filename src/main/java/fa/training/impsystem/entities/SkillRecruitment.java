package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"skill_id", "recruitment_id"})}
)
public class SkillRecruitment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_recruitment_id")
    private int skillRecruitmentId;

    @NotNull(message = MessageConst.INVALID_SKILL_NULL)
    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skills skills;

    @NotNull(message = MessageConst.INVALID_RECRUITMENT_NULL)
    @ManyToOne
    @JoinColumn(name = "recruitment_id", referencedColumnName = "recruitment_id")
    private Recruitment recruitment;

    public SkillRecruitment() {

    }

    public SkillRecruitment(int skillRecruitmentId, Skills skills, Recruitment recruitment) {
        this.skillRecruitmentId = skillRecruitmentId;
        this.skills = skills;
        this.recruitment = recruitment;
    }

    public int getSkillRecruitmentId() {
        return skillRecruitmentId;
    }

    public void setSkillRecruitmentId(int skillRecruitmentId) {
        this.skillRecruitmentId = skillRecruitmentId;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }
}

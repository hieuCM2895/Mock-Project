package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Skills extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_id")
    private int skillId;

    @NotBlank(message = MessageConst.INVALID_NAME)
    @Column(name = "skill_name", nullable = false)
    private String skillName;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skills")
    private Set<SkillRecruitment> skillRecruitments;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skills")
    private Set<SkillCandidate> skillCandidates;

    public Skills() {

    }

    public Skills(int skillId, String skillName, String description) {
        this.skillId = skillId;
        this.skillName = skillName;
        this.description = description;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SkillRecruitment> getSkillRecruitments() {
        return skillRecruitments;
    }

    public void setSkillRecruitments(Set<SkillRecruitment> skillRecruitments) {
        this.skillRecruitments = skillRecruitments;
    }

    public Set<SkillCandidate> getSkillCandidates() {
        return skillCandidates;
    }

    public void setSkillCandidates(Set<SkillCandidate> skillCandidates) {
        this.skillCandidates = skillCandidates;
    }
}

package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
        uniqueConstraints = {@UniqueConstraint(columnNames = {"skill_id", "candidate_id"})}
)
public class SkillCandidate extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "skill_candidate_id")
    private int skillCandidateId;

    @NotNull(message = MessageConst.INVALID_SKILL_NULL)
    @ManyToOne
    @JoinColumn(name = "skill_id", referencedColumnName = "skill_id")
    private Skills skills;

    @NotNull(message = MessageConst.INVALID_CANDIDATE_NULL)
    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    private Candidates candidates;

    public SkillCandidate() {

    }

    public SkillCandidate(int skillCandidateId, Skills skills, Candidates candidates) {
        this.skillCandidateId = skillCandidateId;
        this.skills = skills;
        this.candidates = candidates;
    }

    public int getSkillCandidateId() {
        return skillCandidateId;
    }

    public void setSkillCandidateId(int skillCandidateId) {
        this.skillCandidateId = skillCandidateId;
    }

    public Skills getSkills() {
        return skills;
    }

    public void setSkills(Skills skills) {
        this.skills = skills;
    }

    public Candidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates candidates) {
        this.candidates = candidates;
    }
}

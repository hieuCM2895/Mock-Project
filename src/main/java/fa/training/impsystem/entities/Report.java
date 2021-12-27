package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Report extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private int reportId;

    @NotBlank(message = MessageConst.INVALID_EVALUATE)
    @Column(nullable = false)
    private String evaluate;

    @Column
    private String note;

    @NotNull(message = MessageConst.INVALID_USER_NULL)
    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private Users users;

    @NotNull(message = MessageConst.INVALID_CANDIDATE_NULL)
    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    private Candidates candidates;

    public Report() {

    }

    public Report(int reportId, String evaluate, String note, Users users, Candidates candidates) {
        this.reportId = reportId;
        this.evaluate = evaluate;
        this.note = note;
        this.users = users;
        this.candidates = candidates;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Candidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates candidates) {
        this.candidates = candidates;
    }
}

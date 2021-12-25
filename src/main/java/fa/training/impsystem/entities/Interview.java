package fa.training.impsystem.entities;

import fa.training.impsystem.consts.CommonConst;
import fa.training.impsystem.consts.MessageConst;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@Entity
public class Interview extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private int interviewId;

    @Column(nullable = false)
    @DateTimeFormat(pattern = CommonConst.FORMAT_DATE)
    @Past(message = MessageConst.INVALID_DATE)
    private Date date;

    @NotBlank(message = MessageConst.INVALID_ADDRESS)
    @Column(nullable = false)
    private String address;

    @Min(value = 1, message = MessageConst.INVALID_ROUND)
    @Column(nullable = false)
    private int round;

    @NotBlank(message = MessageConst.INVALID_CONTACT)
    @Column(nullable = false)
    private String contact;

    @NotNull(message = MessageConst.INVALID_USER_NULL)
    @ManyToOne
    @JoinColumn(name = "user_name", referencedColumnName = "user_name")
    private Users users;

    @NotNull(message = MessageConst.INVALID_CANDIDATE_NULL)
    @ManyToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    private Candidates candidates;

    public Interview() {

    }

    public Interview(int interviewId, Date date, String address, int round, String contact, Users users, Candidates candidates) {
        this.interviewId = interviewId;
        this.date = date;
        this.address = address;
        this.round = round;
        this.contact = contact;
        this.users = users;
        this.candidates = candidates;
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

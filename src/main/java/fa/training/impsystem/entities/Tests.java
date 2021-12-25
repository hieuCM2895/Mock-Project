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
import java.util.Set;

@Entity
public class Tests extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private int testId;

    @NotBlank(message = MessageConst.INVALID_NAME)
    @Column(name = "test_name", nullable = false)
    private String testName;

    @Column(name = "date_from", nullable = false)
    @DateTimeFormat(pattern = CommonConst.FORMAT_DATE)
    @Past(message = MessageConst.INVALID_DATE)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    @DateTimeFormat(pattern = CommonConst.FORMAT_DATE)
    @Past(message = MessageConst.INVALID_DATE)
    private Date dateTo;

    @Min(value = 15, message = MessageConst.INVALID_TIME)
    @Column(nullable = false)
    private int time;

    @Min(value = 1, message = MessageConst.INVALID_NO_OF_QUESTION)
    @Column(name = "no_of_question", nullable = false)
    private int noOfQuestion;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tests")
    private EntryTest entryTest;

    @NotNull(message = MessageConst.INVALID_QUESTION_NULL)
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Questions questions;

    public Tests() {

    }

    public Tests(int testId, String testName, Date dateFrom, Date dateTo, int time, int noOfQuestion, Questions questions) {
        this.testId = testId;
        this.testName = testName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.time = time;
        this.noOfQuestion = noOfQuestion;
        this.questions = questions;
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getNoOfQuestion() {
        return noOfQuestion;
    }

    public void setNoOfQuestion(int noOfQuestion) {
        this.noOfQuestion = noOfQuestion;
    }

    public EntryTest getEntryTest() {
        return entryTest;
    }

    public void setEntryTest(EntryTest entryTest) {
        this.entryTest = entryTest;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }
}

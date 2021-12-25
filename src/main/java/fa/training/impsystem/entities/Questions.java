package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Questions extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private int questionId;

    @NotBlank(message = MessageConst.INVALID_NAME)
    @Column(name = "question_name", nullable = false)
    private String questionName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questions")
    private Set<Tests> tests;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "questions")
    private Set<Answers> answers;

    public Questions() {

    }

    public Questions(int questionId, String questionName) {
        this.questionId = questionId;
        this.questionName = questionName;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Set<Tests> getTests() {
        return tests;
    }

    public void setTests(Set<Tests> tests) {
        this.tests = tests;
    }

    public Set<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answers> answers) {
        this.answers = answers;
    }
}

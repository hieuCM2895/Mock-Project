package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Answers extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private int answerId;

    @NotBlank(message = MessageConst.INVALID_NAME)
    @Column(name = "answer_name", nullable = false)
    private String answerName;

    @Min(value = 0, message = MessageConst.INVALID_IS_CORRECT)
    @Column(name = "is_correct")
    private int isCorrect;

    @NotNull(message = MessageConst.INVALID_QUESTION_NULL)
    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "question_id")
    private Questions questions;

    public Answers() {

    }

    public Answers(int answerId, String answerName, int isCorrect, Questions questions) {
        this.answerId = answerId;
        this.answerName = answerName;
        this.isCorrect = isCorrect;
        this.questions = questions;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public int getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(int isCorrect) {
        this.isCorrect = isCorrect;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }
}

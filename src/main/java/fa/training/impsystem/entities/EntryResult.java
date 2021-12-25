package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class EntryResult extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_result_id")
    private int entryResultId;

    @Min(value = 0, message = MessageConst.INVALID_IS_CORRECT)
    @Column(name = "no_correct")
    private int noCorrect;

    @Min(value = 0, message = MessageConst.INVALID_NO_CORRECT)
    @Column(name = "no_incorrect")
    private int noIncorrect;

    @Min(value = 0, message = MessageConst.INVALID_UN_CORRECT)
    @Column(name = "no_unanswered")
    private int noUnanswered;

    @Min(value = 0, message = MessageConst.INVALID_SCORE)
    @Column
    private int score;

    @NotBlank(message = MessageConst.INVALID_RANKING)
    @Column
    private String ranking;

    @NotNull(message = MessageConst.INVALID_ENTRY_TEST_NULL)
    @OneToOne
    @JoinColumn(name = "entry_test_id", referencedColumnName = "entry_test_id")
    private EntryTest entryTest;

    public EntryResult() {

    }

    public EntryResult(int entryResultId, int noCorrect, int noIncorrect, int noUnanswered, int score, String ranking, EntryTest entryTest) {
        this.entryResultId = entryResultId;
        this.noCorrect = noCorrect;
        this.noIncorrect = noIncorrect;
        this.noUnanswered = noUnanswered;
        this.score = score;
        this.ranking = ranking;
        this.entryTest = entryTest;
    }

    public int getEntryResultId() {
        return entryResultId;
    }

    public void setEntryResultId(int entryResultId) {
        this.entryResultId = entryResultId;
    }

    public int getNoCorrect() {
        return noCorrect;
    }

    public void setNoCorrect(int noCorrect) {
        this.noCorrect = noCorrect;
    }

    public int getNoIncorrect() {
        return noIncorrect;
    }

    public void setNoIncorrect(int noIncorrect) {
        this.noIncorrect = noIncorrect;
    }

    public int getNoUnanswered() {
        return noUnanswered;
    }

    public void setNoUnanswered(int noUnanswered) {
        this.noUnanswered = noUnanswered;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public EntryTest getEntryTest() {
        return entryTest;
    }

    public void setEntryTest(EntryTest entryTest) {
        this.entryTest = entryTest;
    }
}

package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class EntryTest extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_test_id")
    private int entryTestId;

    @NotNull(message = MessageConst.INVALID_CANDIDATE_NULL)
    @OneToOne
    @JoinColumn(name = "candidate_id", referencedColumnName = "candidate_id")
    private Candidates candidates;

    @NotNull(message = MessageConst.INVALID_TEST_NULL)
    @OneToOne
    @JoinColumn(name = "test_id", referencedColumnName = "test_id")
    private Tests tests;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "entryTest")
    private EntryResult entryResult;

    public EntryTest() {

    }

    public EntryTest(int entryTestId, Candidates candidates, Tests tests) {
        this.entryTestId = entryTestId;
        this.candidates = candidates;
        this.tests = tests;
    }

    public int getEntryTestId() {
        return entryTestId;
    }

    public void setEntryTestId(int entryTestId) {
        this.entryTestId = entryTestId;
    }

    public Candidates getCandidates() {
        return candidates;
    }

    public void setCandidates(Candidates candidates) {
        this.candidates = candidates;
    }

    public Tests getTests() {
        return tests;
    }

    public void setTests(Tests tests) {
        this.tests = tests;
    }

    public EntryResult getEntryResult() {
        return entryResult;
    }

    public void setEntryResult(EntryResult entryResult) {
        this.entryResult = entryResult;
    }
}

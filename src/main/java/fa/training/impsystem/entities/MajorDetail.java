package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class MajorDetail extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "major_detail_id")
    private int majorDetailId;

    @NotBlank(message = MessageConst.INVALID_NAME)
    @Column(name = "major_detail_name", nullable = false)
    private String majorDetailName;

    @NotNull(message = MessageConst.INVALID_MAJOR_NULL)
    @ManyToOne
    @JoinColumn(name = "major_id", referencedColumnName = "major_id")
    private Major major;

    public MajorDetail() {

    }

    public MajorDetail(int majorDetailId, String majorDetailName, Major major) {
        this.majorDetailId = majorDetailId;
        this.majorDetailName = majorDetailName;
        this.major = major;
    }

    public int getMajorDetailId() {
        return majorDetailId;
    }

    public void setMajorDetailId(int majorDetailId) {
        this.majorDetailId = majorDetailId;
    }

    public String getMajorDetailName() {
        return majorDetailName;
    }

    public void setMajorDetailName(String majorDetailName) {
        this.majorDetailName = majorDetailName;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }
}

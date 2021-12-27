package fa.training.impsystem.entities;

import fa.training.impsystem.consts.MessageConst;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Major extends BaseEntity{

    @Id
    @Column(name = "major_id")
    private String majorId;

    @NotBlank(message = MessageConst.INVALID_NAME)
    @Column(name = "major_name", nullable = false)
    private String majorName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "major")
    private Set<MajorDetail> majorDetail;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "major")
    private Set<Vacancies> vacancies;

    public Major() {

    }

    public Major(String majorId, String majorName) {
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Set<MajorDetail> getMajorDetail() {
        return majorDetail;
    }

    public void setMajorDetail(Set<MajorDetail> majorDetail) {
        this.majorDetail = majorDetail;
    }

    public Set<Vacancies> getVacancies() {
        return vacancies;
    }

    public void setVacancies(Set<Vacancies> vacancies) {
        this.vacancies = vacancies;
    }
}

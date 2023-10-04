package soft.media.lab.test.entity;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "student")
public class Student extends BaseEntity{

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "performance_id")
    private Performance performance;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Performance getPerformance() {
        return performance;
    }

    public void setPerformance(Performance performance) {
        this.performance = performance;
    }
}

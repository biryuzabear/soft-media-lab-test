package soft.media.lab.test.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "performance")
public class Performance extends BaseEntity{

    @Column(name = "text", nullable = false)
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package goanvi.web.weblab3.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Mark implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Double xValue;
    @Column
    private Double yValue;
    @Column
    private Double rValue;
    @Column
    private String hit;
    @Column
    private LocalDateTime time;
    @Column
    private Long leadTime;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    public Mark(Double xValue, Double yValue, Double rValue, String hit, LocalDateTime time, Long leadTime) {
        this.xValue = xValue;
        this.yValue = yValue;
        this.rValue = rValue;
        this.hit = hit;
        this.time = time;
        this.leadTime = leadTime;
    }
}

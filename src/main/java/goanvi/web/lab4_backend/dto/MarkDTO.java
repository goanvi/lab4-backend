package goanvi.web.lab4_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MarkDTO implements Serializable {
    private Long id;
    @NotNull
    @Max(5) @Min(-5)
    private Double xValue;
    @NotNull
    @Max(3) @Min(-3)
    private Double yValue;
    @NotNull
    @Max(5) @Min(0)
    private Double rValue;
    private String hit;
    private LocalDateTime time;
    private Long leadTime;

}

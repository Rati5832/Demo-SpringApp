package FirstSpringApplication.DemoSpringApp.Model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class TeamDTO {

    @NotBlank(message = "teamname cannot be blank")
    @Length(min = 5, max = 16, message = "teamname must be between 5-16 characters")
    private String teamname;
    @NotNull

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate starttime;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate endtime;

    @Min(value = 5, message = "Minimum must be 5")
    @Max(value = 15, message = "Maximum must be 15")
    private Integer maxstudentsenrolled;
    private Long courseId;

}

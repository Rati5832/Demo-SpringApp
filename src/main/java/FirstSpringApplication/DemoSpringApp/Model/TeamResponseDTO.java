package FirstSpringApplication.DemoSpringApp.Model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeamResponseDTO {

    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;

    private Integer maxstudentsenrolled;


    private boolean finished;
    private boolean deleted;
    private Long teamId;

    private CourseDTO course;

}

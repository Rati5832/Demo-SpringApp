package FirstSpringApplication.DemoSpringApp.Model;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class At {

    private long id;
    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;
    private Integer maxstudentsenrolled;
    private boolean finished = Boolean.FALSE;
    private boolean deleted = Boolean.FALSE;
    private CourseDTO course;
    private Set<StudentResponseDTO> students;

}
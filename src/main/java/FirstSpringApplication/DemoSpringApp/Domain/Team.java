package FirstSpringApplication.DemoSpringApp.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String teamname;
    private LocalDate starttime;
    private LocalDate endtime;
    private Integer maxstudentsenrolled;
    private boolean deleted = Boolean.FALSE;
    @ManyToOne
    private Course course;

    @ManyToMany
    @JoinTable(name="student_team",
            joinColumns=@JoinColumn(name ="team_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students = new HashSet<>();



}
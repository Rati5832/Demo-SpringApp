package FirstSpringApplication.DemoSpringApp.Domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String idnumber;
    private String adress;
    private boolean active=Boolean.TRUE;
    @OneToMany( mappedBy="student")
    private Set<Contact> contacts = new HashSet<>();
    @ManyToMany(mappedBy = "students")
    private Set<Team> teams = new HashSet<>();
}
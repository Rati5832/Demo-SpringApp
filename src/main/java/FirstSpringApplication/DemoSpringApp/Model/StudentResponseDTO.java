package FirstSpringApplication.DemoSpringApp.Model;

import lombok.Data;

import java.util.Set;

@Data
public class StudentResponseDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String idnumber;
    private String adress;
    private boolean active;
    private Set<ContactResponseForList> contacts;
}

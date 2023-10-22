package FirstSpringApplication.DemoSpringApp.Model;

import FirstSpringApplication.DemoSpringApp.Domain.ContactType;
import lombok.Data;

@Data
public class ContactResponseDTO {

    private ContactType contacttype;
    private String contactvalue;

    private StudentDTO student;
    private Long studentId;
    private Long contactId;

}

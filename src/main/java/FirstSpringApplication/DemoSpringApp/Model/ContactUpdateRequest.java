package FirstSpringApplication.DemoSpringApp.Model;

import FirstSpringApplication.DemoSpringApp.Domain.ContactType;
import lombok.Data;

@Data
public class ContactUpdateRequest {

    private ContactType contacttype;
    private String contactvalue;

}

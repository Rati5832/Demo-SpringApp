package FirstSpringApplication.DemoSpringApp.Model;

import FirstSpringApplication.DemoSpringApp.Domain.ContactType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequest {

    private ContactType contacttype;
    @NotBlank(message = "ContactValue cannot be blank")
    @Length(min = 5, max = 20, message = "ContactValue must be between 5-20 characters")
    private String contactvalue;
    private Long studentId;

}

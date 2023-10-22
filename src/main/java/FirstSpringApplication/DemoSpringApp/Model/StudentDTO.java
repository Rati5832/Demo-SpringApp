package FirstSpringApplication.DemoSpringApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    @NotBlank(message = "Firstname cannot be blank")
    @Length(min = 3, max = 30, message = "Firstname must be between 5-16 characters")
    private String firstname;
    @NotBlank(message = "Lastname cannot be blank")
    @Length(min = 3, max = 30, message = "Lastname must be between 5-16 characters")
    private String lastname;
    @Pattern(regexp="[\\d]{11}", message = "idnumber must be 11 characters long")
    private String idnumber;
    @NotBlank(message = "Adress cannot be blank")
    @Length(min = 4, max = 30, message = "Lastname must be between 5-16 characters")
    private String adress;

}

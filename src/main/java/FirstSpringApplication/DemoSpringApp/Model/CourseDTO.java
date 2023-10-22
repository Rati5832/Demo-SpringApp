package FirstSpringApplication.DemoSpringApp.Model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
public class CourseDTO {

    @NotBlank(message = "Coursename cannot be blank")
    @Length(min = 5, max = 16, message = "Coursename must be between 5-16 characters")
    private String name;
    @NotBlank(message = "Description cannot be blank")
    @Length(min = 10, max = 30, message = "Description must be between 5-16 characters")
    private String description;

}
package FirstSpringApplication.DemoSpringApp.Model;

import lombok.Data;

@Data
public class CourseResponseDTO {

    private String name;

    private String description;

    private Long courseId;
}

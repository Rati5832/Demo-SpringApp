package FirstSpringApplication.DemoSpringApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CourseListDTO {
    List<CourseResponseDTO> courses;


}

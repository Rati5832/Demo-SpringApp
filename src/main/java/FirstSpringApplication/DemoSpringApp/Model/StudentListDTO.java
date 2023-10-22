package FirstSpringApplication.DemoSpringApp.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentListDTO {
    List<StudentResponseDTO> students;

}

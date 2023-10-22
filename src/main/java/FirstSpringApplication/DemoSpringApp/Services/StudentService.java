package FirstSpringApplication.DemoSpringApp.Services;

import FirstSpringApplication.DemoSpringApp.Model.StudentDTO;
import FirstSpringApplication.DemoSpringApp.Model.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO getStudentById(Long id);

    StudentResponseDTO createNewStudent(StudentDTO studentDTO);

    StudentResponseDTO updateStudent(Long id, StudentDTO studentDTO);

    void deactiveStudentById(Long id);

    void deleteDeactiveateddStudent(Long id);

}

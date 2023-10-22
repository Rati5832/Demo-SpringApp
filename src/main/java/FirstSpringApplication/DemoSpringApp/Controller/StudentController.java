package FirstSpringApplication.DemoSpringApp.Controller;

import FirstSpringApplication.DemoSpringApp.Model.StudentDTO;
import FirstSpringApplication.DemoSpringApp.Model.StudentListDTO;
import FirstSpringApplication.DemoSpringApp.Model.StudentResponseDTO;
import FirstSpringApplication.DemoSpringApp.Services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(StudentController.BASE_URL)
public class StudentController {

    public static final String BASE_URL = "/api/v1/students";
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public StudentListDTO getListOfStudent() {
        return new StudentListDTO(studentService.getAllStudents());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO createNewStudent(@RequestBody @Valid StudentDTO studentDTO) {
        return studentService.createNewStudent(studentDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public StudentResponseDTO updateStudent(@PathVariable Long id, @Valid @RequestBody StudentDTO studentDTO) {
        return studentService.updateStudent(id, studentDTO);
    }

    @DeleteMapping({"/deactive/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deactiveStudentById(@PathVariable Long id) {
        studentService.deactiveStudentById(id);
    }
    @DeleteMapping({"/deleteDeactivated/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public  void deleteDeactiveateddStudent(Long id) {
        studentService.deleteDeactiveateddStudent(id);
    }
}

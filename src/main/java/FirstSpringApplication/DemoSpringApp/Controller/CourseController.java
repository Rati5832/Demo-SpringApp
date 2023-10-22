package FirstSpringApplication.DemoSpringApp.Controller;

import FirstSpringApplication.DemoSpringApp.Model.CourseDTO;
import FirstSpringApplication.DemoSpringApp.Model.CourseListDTO;
import FirstSpringApplication.DemoSpringApp.Model.CourseResponseDTO;
import FirstSpringApplication.DemoSpringApp.Services.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(CourseController.BASE_URL)
public class CourseController {

    public static final String BASE_URL = "/api/v1/courses";
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Get Course ", description = "course  shesaxeb ")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CourseListDTO getallCourses() {

        return new CourseListDTO(courseService.getAllCourses());
    }

    @Operation(summary = "Get Course by id ", description = "course  shesaxeb ")
    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CourseResponseDTO getTeamById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    @Operation(summary = "add teamToCourse ", description = "Activecourse  shesaxeb ")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseResponseDTO createNewTeam(@Valid @RequestBody CourseDTO courseDTO) {
        return courseService.createNewCourse(courseDTO);
    }

    @Operation(summary = "update Course ", description = "course  shesaxeb ")
    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public CourseResponseDTO updateCourse(@PathVariable Long id,@Valid @RequestBody CourseDTO courseDTO) {
        return courseService.updateCourseByDTO(id, courseDTO);
    }

    @Operation(summary = "delete Course ", description = "course  shesaxeb ")
    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudent(@PathVariable Long id) {
        courseService.deleteCourseById(id);
    }

}

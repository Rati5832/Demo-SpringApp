package FirstSpringApplication.DemoSpringApp.Services;

import FirstSpringApplication.DemoSpringApp.Model.CourseDTO;
import FirstSpringApplication.DemoSpringApp.Model.CourseResponseDTO;

import java.util.List;

public interface CourseService {

    List<CourseResponseDTO> getAllCourses();
    //

    CourseResponseDTO getCourseById(Long id);

    CourseResponseDTO createNewCourse(CourseDTO courseDTO);

    CourseResponseDTO updateCourseByDTO(Long id, CourseDTO courseDTO);

    void deleteCourseById(Long id);

}
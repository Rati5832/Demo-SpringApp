package FirstSpringApplication.DemoSpringApp.Services;

import FirstSpringApplication.DemoSpringApp.Domain.Course;
import FirstSpringApplication.DemoSpringApp.Mapper.CourseMapper;
import FirstSpringApplication.DemoSpringApp.Model.CourseDTO;
import FirstSpringApplication.DemoSpringApp.Model.CourseResponseDTO;
import FirstSpringApplication.DemoSpringApp.Repositories.CourseRepository;
import FirstSpringApplication.DemoSpringApp.Repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseMapper courseMapper, CourseRepository courseRepository,
                             TeamRepository teamRepository) {
        this.courseMapper = courseMapper;
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseResponseDTO> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .map(course -> {
                    CourseResponseDTO courseResponseDTO = courseMapper.courseToresponse(course);
                    courseResponseDTO.setCourseId(course.getId());
                    return courseResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO getCourseById(Long id) {

        Optional<Course> courseOPT = courseRepository.findById(id);
        Course course = courseOPT.get();
        CourseResponseDTO courseResponse = new CourseResponseDTO();
        courseResponse = courseMapper.courseToresponse(course);
        courseResponse.setCourseId(course.getId());
        return courseResponse;
    }

    @Override
    @Transactional
    public CourseResponseDTO createNewCourse(CourseDTO courseDTO) {
        Optional<Course> courseName = courseRepository.findByName(courseDTO.getName());
        if(courseName.isPresent()) {
            throw new RuntimeException("course name already exists");
        } else {
            Course course = courseMapper.courseDtoTocourse(courseDTO);
            courseRepository.save(course);

            CourseResponseDTO courseResponse = courseMapper.courseToresponse(course);
            courseResponse.setCourseId(course.getId());
            return courseResponse;
        }
    }

    @Override
    @Transactional
    public CourseResponseDTO updateCourseByDTO(Long id, CourseDTO courseDTO) {
        Course course = courseRepository.findById(id).get();
        course.setDescription(courseDTO.getDescription());
        course.setName(courseDTO.getName());
        CourseResponseDTO courseResponse = new CourseResponseDTO();
        courseResponse.setCourseId(course.getId());
        courseResponse.setDescription(course.getDescription());
        courseResponse.setName(course.getName());
        return courseResponse;
    }

    @Override
    @Transactional
    public void deleteCourseById(Long id) {
        Course course = courseRepository.findById(id).get();
        if (course.getTeams().isEmpty()) {
            courseRepository.deleteById(id);
        } else {
            throw new RuntimeException("Course is active");
        }
    }
}

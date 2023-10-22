package FirstSpringApplication.DemoSpringApp.Mapper;

import FirstSpringApplication.DemoSpringApp.Domain.Course;
import FirstSpringApplication.DemoSpringApp.Model.CourseDTO;
import FirstSpringApplication.DemoSpringApp.Model.CourseResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseDTO courseToCourseDTO(Course course);

    Course courseDtoTocourse(CourseDTO courseDTO);

    CourseResponseDTO courseToresponse(Course course);

}

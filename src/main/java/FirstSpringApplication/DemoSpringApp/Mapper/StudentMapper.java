package FirstSpringApplication.DemoSpringApp.Mapper;

import FirstSpringApplication.DemoSpringApp.Domain.Student;
import FirstSpringApplication.DemoSpringApp.Model.StudentDTO;
import FirstSpringApplication.DemoSpringApp.Model.StudentResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentDTO studentToDto(Student student);

    Student studentDtoTostudent(StudentDTO studentDTO);

    StudentResponseDTO studentToREsponse(Student student);

}

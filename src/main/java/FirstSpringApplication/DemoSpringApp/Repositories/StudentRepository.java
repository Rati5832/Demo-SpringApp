package FirstSpringApplication.DemoSpringApp.Repositories;

import FirstSpringApplication.DemoSpringApp.Domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByIdnumber(String idnumber);
}

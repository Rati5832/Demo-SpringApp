package FirstSpringApplication.DemoSpringApp.Repositories;

import FirstSpringApplication.DemoSpringApp.Domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByName(String name);
}

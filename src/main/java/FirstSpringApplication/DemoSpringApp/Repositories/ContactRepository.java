package FirstSpringApplication.DemoSpringApp.Repositories;

import FirstSpringApplication.DemoSpringApp.Domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    Optional<Contact> findByContactvalue(String contactvalue);
}

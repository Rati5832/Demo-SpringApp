package FirstSpringApplication.DemoSpringApp.Repositories;

import FirstSpringApplication.DemoSpringApp.Domain.Team;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@SQLDelete(sql = "UPDATE Team SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public interface TeamRepository extends JpaRepository<Team,Long> {
    Optional<Team> findByTeamname(String teamname);
}

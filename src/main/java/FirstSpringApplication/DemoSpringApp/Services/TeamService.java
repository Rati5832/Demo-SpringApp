package FirstSpringApplication.DemoSpringApp.Services;

import FirstSpringApplication.DemoSpringApp.Model.At;
import FirstSpringApplication.DemoSpringApp.Model.TeamDTO;
import FirstSpringApplication.DemoSpringApp.Model.TeamResponseDTO;

import java.util.List;

public interface TeamService {

    List<At> getActiveTeams();

    List<TeamResponseDTO> getAllTeam();

    TeamResponseDTO getTeamsById(Long id);

    At assignStudentToTeam(Long teamId, Long studentId);

    TeamResponseDTO createNewTeam(TeamDTO teamDTO);

    TeamResponseDTO updateTeam(Long id, TeamDTO teamDTO);

    void deleteTeamById(Long id);

    void softDeleteTeamById(Long teamId);

}

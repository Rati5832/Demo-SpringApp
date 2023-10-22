package FirstSpringApplication.DemoSpringApp.Controller;

import FirstSpringApplication.DemoSpringApp.Model.*;
import FirstSpringApplication.DemoSpringApp.Services.TeamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(TeamController.BASE_URL)
public class TeamController {

    public static final String BASE_URL = "/api/v1/teams";
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping({"/active"})
    @ResponseStatus(HttpStatus.OK)
    public ListOfAt getActiveTeams() {
        return new ListOfAt(teamService.getActiveTeams());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TeamListDTO getAllTeam() {

        return new TeamListDTO(teamService.getAllTeam());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeamResponseDTO getTeamById(@PathVariable Long id) {
        return teamService.getTeamsById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeamResponseDTO createNewTeam(@Valid @RequestBody TeamDTO teamDTO) {
        return teamService.createNewTeam(teamDTO);
    }

    @PostMapping("/{studentId}/student/{teamId}")
    @ResponseStatus(HttpStatus.CREATED)
    public At assignStudentToTeam(@PathVariable Long teamId, @PathVariable Long studentId) {
        return teamService.assignStudentToTeam(teamId, studentId);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TeamResponseDTO updateTeam( @PathVariable Long id,@Valid @RequestBody TeamDTO teamDTO) {

        return teamService.updateTeam(id, teamDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTeam(@PathVariable Long id) {
        teamService.deleteTeamById(id);
    }

    @DeleteMapping({"/softDelete/{teamId}"})
    @ResponseStatus(HttpStatus.OK)
    public void softDeleteTeamById(@PathVariable Long teamId) {
        teamService.softDeleteTeamById(teamId);
    }

}
package FirstSpringApplication.DemoSpringApp.Mapper;

import FirstSpringApplication.DemoSpringApp.Domain.Team;
import FirstSpringApplication.DemoSpringApp.Model.At;
import FirstSpringApplication.DemoSpringApp.Model.TeamDTO;
import FirstSpringApplication.DemoSpringApp.Model.TeamResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeamMapper {
    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    TeamDTO teamToTeamDTO(Team team);

    Team teamDtoToteam(TeamDTO teamDTO);

    TeamResponseDTO teamToResponse(Team team);

    At teamToAssign(Team team);

}
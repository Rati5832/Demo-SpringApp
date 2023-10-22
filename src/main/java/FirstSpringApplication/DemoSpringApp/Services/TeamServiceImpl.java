package FirstSpringApplication.DemoSpringApp.Services;

import FirstSpringApplication.DemoSpringApp.Domain.Course;
import FirstSpringApplication.DemoSpringApp.Domain.Student;
import FirstSpringApplication.DemoSpringApp.Domain.Team;
import FirstSpringApplication.DemoSpringApp.Mapper.CourseMapper;
import FirstSpringApplication.DemoSpringApp.Mapper.TeamMapper;
import FirstSpringApplication.DemoSpringApp.Model.At;
import FirstSpringApplication.DemoSpringApp.Model.CourseDTO;
import FirstSpringApplication.DemoSpringApp.Model.TeamDTO;
import FirstSpringApplication.DemoSpringApp.Model.TeamResponseDTO;
import FirstSpringApplication.DemoSpringApp.Repositories.CourseRepository;
import FirstSpringApplication.DemoSpringApp.Repositories.StudentRepository;
import FirstSpringApplication.DemoSpringApp.Repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {

    private final CourseMapper courseMapper;
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public TeamServiceImpl(CourseMapper courseMapper, TeamMapper teamMapper, TeamRepository teamRepository,
                           CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseMapper = courseMapper;
        this.teamMapper = teamMapper;
        this.teamRepository = teamRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<At> getActiveTeams() {
        return teamRepository
                .findAll()
                .stream()
                .map(team -> {
                    At assignTeamToStudentDTO = teamMapper.teamToAssign(team);

                    return assignTeamToStudentDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamResponseDTO> getAllTeam() {

        return teamRepository
                .findAll()
                .stream()
                .map(team -> {
                    TeamResponseDTO teamResponseDTO = teamMapper.teamToResponse(team);
                    teamResponseDTO.setTeamId(team.getId());
                    return teamResponseDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public TeamResponseDTO getTeamsById(Long id) {

        Team team = teamRepository.findById(id).get();
        Course course = courseRepository.findById(team.getCourse().getId()).get();
        CourseDTO courseDTO = courseMapper.courseToCourseDTO(course);
        TeamResponseDTO team1 = teamMapper.teamToResponse(team);
        team1.setTeamId(team.getId());
        team1.setCourse(courseDTO);

        return team1;

    }

    @Override
    @Transactional
    public At assignStudentToTeam(Long teamId, Long studentId) {
        Team team = teamRepository.findById(teamId).get();
        if (team.isDeleted() == true) {
            throw new RuntimeException("team is deactivated");
        }
        Student student = studentRepository.findById(studentId).get();
        if(student.isActive()){
            team.getStudents().add(student);

            if (team.getMaxstudentsenrolled() > team.getStudents().size()) {
                At assign = new At();
                assign = teamMapper.teamToAssign(team);

                return assign;
            } else {
                throw new RuntimeException("Team is full");
            }
        } else {
            throw new RuntimeException("Student is deactive");
        }
    }

    @Override
    @Transactional
    public TeamResponseDTO createNewTeam(TeamDTO teamDTO) {
        Optional<Team> teamName = teamRepository.findByTeamname(teamDTO.getTeamname());
        if (teamName.isPresent()) {
            throw new RuntimeException ("Teamname already exists");
        } else {
            Course course = courseRepository.findById(teamDTO.getCourseId()).get();
            Team team = teamMapper.teamDtoToteam(teamDTO);
            team.setCourse(course);
            if (team.getStarttime() != null && team.getEndtime() != null) {
                if (!team.getStarttime().isBefore(team.getEndtime())) {
                    throw new RuntimeException("End Date must be after the Start Date");
                }
            }
                team = teamRepository.save(team);

                CourseDTO coursedto = courseMapper.courseToCourseDTO(course);

                TeamResponseDTO team1 = teamMapper.teamToResponse(team);
                team1.setTeamId(team.getId());
                team1.setCourse(coursedto);

                return team1;
            }
        }

    @Override
    @Transactional
    public TeamResponseDTO updateTeam(Long id, TeamDTO teamDTO) {
        Course course = courseRepository.findById(teamDTO.getCourseId()).get();
        Team team = teamRepository.findById(id).get();
        if (team.isDeleted() == true) {
            throw new RuntimeException("Team is deactivated ");
        } else {
            team.setTeamname(teamDTO.getTeamname());
            team.setEndtime(teamDTO.getEndtime());
            team.setStarttime(team.getStarttime());
            team.setMaxstudentsenrolled(teamDTO.getMaxstudentsenrolled());
            team.setCourse(course);

            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setName(course.getName());
            courseDTO.setDescription(course.getDescription());

            TeamResponseDTO teamResponse = new TeamResponseDTO();
            teamResponse.setMaxstudentsenrolled(team.getMaxstudentsenrolled());
            teamResponse.setTeamname(team.getTeamname());
            teamResponse.setStarttime(team.getStarttime());
            teamResponse.setEndtime(team.getEndtime());
            teamResponse.setCourse(courseDTO);
            teamResponse.setTeamId(team.getId());
            teamResponse.setDeleted(team.isDeleted());
            return teamResponse;
        }
    }

    @Override
    @Transactional
    public void deleteTeamById(Long id) {
        Optional<Team> teamOPT = teamRepository.findById(id);

        Team team1 = teamOPT.get();

        teamRepository.deleteById(team1.getId());

    }

    @Override
    @Transactional
    public void softDeleteTeamById(Long teamId) {
        Optional<Team> teamOPT = teamRepository.findById(teamId);
        Team team1 = teamOPT.get();
        if (team1.getStudents() != null) {
            team1.setDeleted(true);
            teamRepository.save(team1);
        } else {
            throw new RuntimeException("Team can be hard deleted");
        }

    }
}
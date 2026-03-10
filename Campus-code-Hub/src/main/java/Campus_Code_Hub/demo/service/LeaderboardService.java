package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.LeaderboardResponse;
import Campus_Code_Hub.demo.dto.LeaderboardStudentDTO;
import Campus_Code_Hub.demo.model.EventType;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.EventRegistrationRepository;
import Campus_Code_Hub.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LeaderboardService {

    private final EventRegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public List<LeaderboardResponse> getCodingLeaderboard() {
        return registrationRepository.getCodingLeaderboard();
    }

    @Transactional(readOnly = true)
    public List<LeaderboardResponse> getAptitudeLeaderboard() {
        return registrationRepository.getAptitudeLeaderboard();
    }



    public List<LeaderboardStudentDTO> getTopCodingStudents() {

        List<Student> students = studentRepository.findTop5ByOrderByCodingPointsDesc();

        return students.stream()
                .map(s -> new LeaderboardStudentDTO(
                        s.getId(),
                        s.getName(),
                        s.getCodingPoints()
                ))
                .collect(Collectors.toList());
    }

    public List<LeaderboardStudentDTO> getTopAptitudeStudents() {

        List<Student> students = studentRepository.findTop5ByOrderByAptitudePointsDesc();

        return students.stream()
                .map(s -> new LeaderboardStudentDTO(
                        s.getId(),
                        s.getName(),
                        s.getAptitudePoints()
                ))
                .collect(Collectors.toList());
    }
}

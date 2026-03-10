package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.DashboardStatsDTO;
import Campus_Code_Hub.demo.repository.StudentRepository;
import Campus_Code_Hub.demo.repository.QuestionRepository;
import Campus_Code_Hub.demo.repository.EventRepository;
import Campus_Code_Hub.demo.repository.CareerTrackRepository;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final StudentRepository studentRepository;
    private final QuestionRepository questionRepository;
    private final EventRepository eventRepository;
    private final CareerTrackRepository careerTrackRepository;

    public DashboardService(
            StudentRepository studentRepository,
            QuestionRepository questionRepository,
            EventRepository eventRepository,
            CareerTrackRepository careerTrackRepository
    ) {
        this.studentRepository = studentRepository;
        this.questionRepository = questionRepository;
        this.eventRepository = eventRepository;
        this.careerTrackRepository = careerTrackRepository;
    }

    public DashboardStatsDTO getStats() {

        long students = studentRepository.count();
        long questions = questionRepository.count();
        long events = eventRepository.count();
        long tracks = careerTrackRepository.count();

        return new DashboardStatsDTO(
                students,
                questions,
                events,
                tracks
        );
    }
}
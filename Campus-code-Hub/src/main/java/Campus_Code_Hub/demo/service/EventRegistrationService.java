package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.AssignPointsRequest;
import Campus_Code_Hub.demo.dto.EventRegistrationResponse;
import Campus_Code_Hub.demo.model.Event;
import Campus_Code_Hub.demo.model.EventRegistration;
import Campus_Code_Hub.demo.model.EventStatus;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.EventRegistrationRepository;
import Campus_Code_Hub.demo.repository.EventRepository;
import Campus_Code_Hub.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventRegistrationService {

    private final EventRepository eventRepository;
    private final EventRegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public void registerForEvent(Long eventId, String studentEmail) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // ✅ Only allow registration when open
        if (event.getStatus() != EventStatus.REGISTRATION_OPEN) {
            throw new IllegalStateException("Registration is not open for this event");
        }

        Student student = studentRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new RuntimeException("Student not found"));


        if (registrationRepository.existsByEventAndStudent(event, student)) {
            throw new IllegalStateException("Already registered for this event");
        }

        EventRegistration registration = new EventRegistration();
        registration.setEvent(event);
        registration.setStudent(student);

        registrationRepository.save(registration);
    }



}

package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.*;
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

import java.time.temporal.WeekFields;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventRegistrationRepository eventRegistrationRepository;

    private final StudentRepository studentRepository;

    public EventResponse createEvent(CreateEventRequest request) {

        // Generate eventWeek (ISO week)
        WeekFields weekFields = WeekFields.ISO;
        int week = request.getEventDate().get(weekFields.weekOfWeekBasedYear());
        int year = request.getEventDate().getYear();

        Event event = new Event();
        event.setName(request.getName());
        event.setType(request.getType());
        event.setEventDate(request.getEventDate());
        event.setEventWeek(year + "-W" + week);

        event.setRegistrationOpenDate(request.getRegistrationOpenDate());
        event.setRegistrationCloseDate(request.getRegistrationCloseDate());

        // Default status
        event.setStatus(EventStatus.REGISTRATION_OPEN);

        Event saved = eventRepository.save(event);

        // Map to response
        EventResponse response = new EventResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setType(saved.getType());
        response.setStatus(saved.getStatus());
        response.setEventDate(saved.getEventDate());
        response.setEventWeek(saved.getEventWeek());

        return response;
    }

    public EventResponse updateEvent(Long eventId, UpdateEventRequest request) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Update name
        if (request.getName() != null) {
            event.setName(request.getName());
        }

        // Update status
        if (request.getStatus() != null) {
            event.setStatus(request.getStatus());
        }

        // Update event date + eventWeek
        if (request.getEventDate() != null) {
            event.setEventDate(request.getEventDate());

            WeekFields weekFields = WeekFields.ISO;
            int week = request.getEventDate()
                    .get(weekFields.weekOfWeekBasedYear());
            int year = request.getEventDate().getYear();

            event.setEventWeek(year + "-W" + week);
        }

        // Update registration dates
        if (request.getRegistrationOpenDate() != null) {
            event.setRegistrationOpenDate(request.getRegistrationOpenDate());
        }

        if (request.getRegistrationCloseDate() != null) {
            event.setRegistrationCloseDate(request.getRegistrationCloseDate());
        }

        Event updated = eventRepository.save(event);

        // Map to response
        EventResponse response = new EventResponse();
        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setType(updated.getType());
        response.setStatus(updated.getStatus());
        response.setEventDate(updated.getEventDate());
        response.setEventWeek(updated.getEventWeek());
        response.setRegistrationOpenDate(updated.getRegistrationOpenDate());
        response.setRegistrationCloseDate(updated.getRegistrationCloseDate());

        return response;
    }

    @Transactional
    public void deleteEvent(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // ❌ Do not allow deleting completed events
        if (event.getStatus() == EventStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed events cannot be deleted"
            );
        }

        // ✅ Delete registrations first
        eventRegistrationRepository.deleteByEvent(event);

        // ✅ Delete event
        eventRepository.delete(event);
    }


    @Transactional(readOnly = true)
    public List<EventRegistrationResponse> getRegistrations(Long eventId) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        return eventRegistrationRepository.findByEvent(event)
                .stream()
                .map(reg -> {
                    EventRegistrationResponse dto = new EventRegistrationResponse();
                    dto.setRegistrationId(reg.getId());
                    dto.setStudentId(reg.getStudent().getId());
                    dto.setStudentName(reg.getStudent().getName());
                    dto.setStudentEmail(reg.getStudent().getEmail());
                    dto.setPoints(reg.getPoints());
                    return dto;
                })
                .toList();
    }


    @Transactional
    public void assignPoints(Long eventId, AssignPointsRequest request) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        // Recommended rule
        if (event.getStatus() != EventStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Points can be assigned only after event completion"
            );
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        EventRegistration registration =
                eventRegistrationRepository.findByEventAndStudent(event, student)
                        .orElseThrow(() ->
                                new RuntimeException("Student not registered for this event"));

        registration.setPoints(request.getPoints());
        eventRegistrationRepository.save(registration);
    }
}




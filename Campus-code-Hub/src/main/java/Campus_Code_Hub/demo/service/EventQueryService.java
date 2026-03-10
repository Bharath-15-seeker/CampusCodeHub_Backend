package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.EventResponse;
import Campus_Code_Hub.demo.model.Event;
import Campus_Code_Hub.demo.model.EventStatus;
import Campus_Code_Hub.demo.model.EventType;
import Campus_Code_Hub.demo.repository.EventRegistrationRepository;
import Campus_Code_Hub.demo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventQueryService {

    private final EventRegistrationRepository registrationRepository;
    private final EventRepository eventRepository;

    public List<EventResponse> getAllEvents(
            EventType type,
            EventStatus status,
            String studentEmail
    ) {
        List<Event> events;

        if (type != null && status != null) {
            events = eventRepository.findByTypeAndStatus(type, status);
        } else if (type != null) {
            events = eventRepository.findByType(type);
        } else if (status != null) {
            events = eventRepository.findByStatus(status);
        } else {
            events = eventRepository.findAll();
        }

        return events.stream()
                .map(event -> toResponse(event, studentEmail))
                .toList();
    }

    private EventResponse toResponse(Event event,String studentEmail) {
        EventResponse dto = new EventResponse();
        dto.setId(event.getId());
        dto.setName(event.getName());
        dto.setType(event.getType());
        dto.setStatus(event.getStatus());
        dto.setEventDate(event.getEventDate());
        dto.setEventWeek(event.getEventWeek());
        dto.setRegistrationOpenDate(event.getRegistrationOpenDate());
        dto.setRegistrationCloseDate(event.getRegistrationCloseDate());
        boolean registered =
                registrationRepository.existsByEventIdAndStudentEmail(
                        event.getId(),
                        studentEmail
                );

        dto.setRegistered(registered);
        return dto;
    }
}


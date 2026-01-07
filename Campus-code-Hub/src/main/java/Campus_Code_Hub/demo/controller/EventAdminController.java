package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.*;
import Campus_Code_Hub.demo.model.Event;
import Campus_Code_Hub.demo.model.EventStatus;
import Campus_Code_Hub.demo.repository.EventRepository;
import Campus_Code_Hub.demo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/events")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class EventAdminController {

    private final EventService eventService;
    private final EventRepository eventRepository;


    @PostMapping
    public ResponseEntity<EventResponse> createEvent(
            @RequestBody CreateEventRequest request
    ) {
        return ResponseEntity.ok(eventService.createEvent(request));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponse> updateEvent(
            @PathVariable Long eventId,
            @RequestBody UpdateEventRequest request
    ) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, request));
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(
            @PathVariable Long eventId
    ) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully");
    }

    @GetMapping("/{eventId}/registrations")
    public ResponseEntity<List<EventRegistrationResponse>> getRegistrations(
            @PathVariable Long eventId
    ) {
        return ResponseEntity.ok(
                eventService.getRegistrations(eventId)
        );
    }

    @PostMapping("/{eventId}/points")
    public ResponseEntity<String> assignPoints(
            @PathVariable Long eventId,
            @RequestBody AssignPointsRequest request
    ) {
        eventService.assignPoints(eventId, request);
        return ResponseEntity.ok("Points assigned successfully");
    }
}


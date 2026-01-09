package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.EventResponse;
import Campus_Code_Hub.demo.model.EventStatus;
import Campus_Code_Hub.demo.model.EventType;
import Campus_Code_Hub.demo.service.EventQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventQueryController {

    private final EventQueryService eventQueryService;

    @GetMapping
    public ResponseEntity<List<EventResponse>> getAllEvents(
            @RequestParam(required = false) EventType type,
            @RequestParam(required = false) EventStatus status
    ) {
        return ResponseEntity.ok(
                eventQueryService.getAllEvents(type, status)
        );
    }
}


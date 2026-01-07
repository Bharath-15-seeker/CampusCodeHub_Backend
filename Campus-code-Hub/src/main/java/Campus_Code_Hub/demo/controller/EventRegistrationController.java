package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventRegistrationController {

    private final EventRegistrationService registrationService;

    @PostMapping("/{eventId}/register")
    public ResponseEntity<String> registerForEvent(
            @PathVariable Long eventId,
            Authentication authentication
    ) {
        String email = authentication.getName();
        registrationService.registerForEvent(eventId, email);
        return ResponseEntity.ok("Registered successfully");
    }
}


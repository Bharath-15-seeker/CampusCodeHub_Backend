package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventRegistrationController {

    private final EventRegistrationService registrationService;

    @PostMapping("/{eventId}/register")
    public ResponseEntity<?> registerForEvent(
            @PathVariable Long eventId,
            Authentication authentication) {

        if(authentication == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User not authenticated");
        }

        String email = authentication.getName();

       registrationService.registerForEvent(eventId, email);

        return ResponseEntity.ok("Registered successfully");
    }

    @DeleteMapping("/{eventId}/unregister")
    public ResponseEntity<?> unregister(@PathVariable Long eventId, Authentication auth) {
        registrationService.unregister(eventId, auth.getName());
        return ResponseEntity.ok().build();
    }
}


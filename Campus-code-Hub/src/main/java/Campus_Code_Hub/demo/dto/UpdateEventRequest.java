package Campus_Code_Hub.demo.dto;

import Campus_Code_Hub.demo.model.EventStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateEventRequest {

    private String name; // optional
    private EventStatus status; // REGISTRATION_OPEN / REGISTRATION_CLOSED / COMPLETED

    private LocalDate eventDate; // optional
    private LocalDate registrationOpenDate; // optional
    private LocalDate registrationCloseDate; // optional
}

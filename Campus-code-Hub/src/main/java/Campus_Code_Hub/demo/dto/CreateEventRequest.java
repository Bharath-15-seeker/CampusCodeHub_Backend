package Campus_Code_Hub.demo.dto;

import Campus_Code_Hub.demo.model.EventType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateEventRequest {

    private String name;
    private EventType type; // CODING / APTITUDE

    private LocalDate eventDate;

    private LocalDate registrationOpenDate;
    private LocalDate registrationCloseDate;
}


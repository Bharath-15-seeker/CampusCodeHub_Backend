package Campus_Code_Hub.demo.dto;

import Campus_Code_Hub.demo.model.EventStatus;
import Campus_Code_Hub.demo.model.EventType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventResponse {

    private Long id;
    private String name;
    private EventType type;
    private EventStatus status;

    private LocalDate eventDate;
    private String eventWeek;

    private LocalDate registrationOpenDate;
    private LocalDate registrationCloseDate;
}

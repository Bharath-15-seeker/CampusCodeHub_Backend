package Campus_Code_Hub.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    // "Week 1 Coding Contest"

    @Enumerated(EnumType.STRING)
    private EventType type;
    // CODING / APTITUDE

    @Enumerated(EnumType.STRING)
    private EventStatus status;
    // REGISTRATION_OPEN, REGISTRATION_CLOSED, COMPLETED

    // 👉 When the event actually happens
    private LocalDate eventDate;

    // 👉 For weekly leaderboard (2026-W01, 2026-W02)
    private String eventWeek;

    // Optional: registration window
    private LocalDate registrationOpenDate;
    private LocalDate registrationCloseDate;
}


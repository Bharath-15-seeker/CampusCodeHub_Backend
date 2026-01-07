package Campus_Code_Hub.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"event_id", "student_id"}
        )
)
@Data
@RequiredArgsConstructor
public class EventRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Event event;

    @ManyToOne(optional = false)
    private Student student;

    private Integer points = 0; // assigned by admin later
}


package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Event;
import Campus_Code_Hub.demo.model.EventStatus;
import Campus_Code_Hub.demo.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByType(EventType type);

    List<Event> findByStatus(EventStatus status);

    List<Event> findByTypeAndStatus(EventType type, EventStatus status);

}


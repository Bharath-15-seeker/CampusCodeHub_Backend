package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}


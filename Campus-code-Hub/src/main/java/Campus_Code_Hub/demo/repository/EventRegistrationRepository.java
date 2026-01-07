package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.dto.LeaderboardResponse;
import Campus_Code_Hub.demo.model.Event;
import Campus_Code_Hub.demo.model.EventRegistration;
import Campus_Code_Hub.demo.model.EventType;
import Campus_Code_Hub.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface EventRegistrationRepository
        extends JpaRepository<EventRegistration, Long> {

    boolean existsByEventAndStudent(Event event, Student student);

    List<EventRegistration> findByEvent(Event event);

    void deleteByEvent(Event event);

    @Query("""
        SELECT new Campus_Code_Hub.demo.dto.LeaderboardResponse(
            er.student.id,
            er.student.name,
            er.student.email,
            SUM(er.points)
        )
        FROM EventRegistration er
        WHERE er.event.type = :type
          AND er.event.status = Campus_Code_Hub.demo.model.EventStatus.COMPLETED
        GROUP BY er.student.id, er.student.name, er.student.email
        ORDER BY SUM(er.points) DESC
    """)
    List<LeaderboardResponse> getLeaderboardByType(
            @Param("type") EventType type
    );
    Optional<EventRegistration> findByEventAndStudent(Event event, Student student);
}



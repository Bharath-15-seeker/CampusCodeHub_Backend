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
        s.id,
        s.name,
        s.email,
        s.codingPoints
    )
    FROM Student s
    ORDER BY s.codingPoints DESC
""")
    List<LeaderboardResponse> getCodingLeaderboard();

    @Query("""
    SELECT new Campus_Code_Hub.demo.dto.LeaderboardResponse(
        s.id,
        s.name,
        s.email,
        s.aptitudePoints
    )
    FROM Student s
    ORDER BY s.aptitudePoints DESC
""")
    List<LeaderboardResponse> getAptitudeLeaderboard();


    Optional<EventRegistration> findByEventAndStudent(Event event, Student student);
}



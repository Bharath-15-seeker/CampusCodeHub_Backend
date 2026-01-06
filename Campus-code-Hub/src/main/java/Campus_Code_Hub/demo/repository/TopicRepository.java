package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}

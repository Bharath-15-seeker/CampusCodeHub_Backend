package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Question;
import Campus_Code_Hub.demo.model.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}

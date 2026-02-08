package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Question;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.model.UserQuestionProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserQuestionProgressRepository
        extends JpaRepository<UserQuestionProgress, Long> {

    Optional<UserQuestionProgress> findByUserAndQuestion(Student user, Question question);

    Optional<UserQuestionProgress>
    findByUserIdAndQuestionId
            (Long userId, Long questionId);

    long countByUserIdAndSolvedTrueAndQuestionIdIn(
            Long userId, List<Long> questionIds
    );
    List<UserQuestionProgress> findByUser(Student user);
}


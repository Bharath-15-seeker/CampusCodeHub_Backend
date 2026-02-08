package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.UserQuestionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuestionStatusRepository
        extends JpaRepository<UserQuestionStatus, Long> {

    long countByUserIdAndSolvedTrueAndQuestionIdIn(
            Long userId, List<Long> questionIds
    );
}


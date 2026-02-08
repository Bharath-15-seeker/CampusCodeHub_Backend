package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Question;
import Campus_Code_Hub.demo.model.Sheet;
import Campus_Code_Hub.demo.model.SheetType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    long countBySheetType(SheetType sheetType);
    List<Question> findBySheetType(SheetType sheetType);

}

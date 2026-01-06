package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Sheet;
import Campus_Code_Hub.demo.model.SheetType;
import Campus_Code_Hub.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SheetRepository extends JpaRepository<Sheet, Long> {
    Optional<Sheet> findBySheetType(SheetType sheetType);
}

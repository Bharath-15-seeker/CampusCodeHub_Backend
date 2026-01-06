package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Sheet;
import Campus_Code_Hub.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SheetRepository extends JpaRepository<Sheet, Long> {
}

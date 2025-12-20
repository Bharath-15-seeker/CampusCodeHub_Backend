package Campus_Code_Hub.demo.repository;

import Campus_Code_Hub.demo.model.Role;
import Campus_Code_Hub.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {

     Optional<Student> findByEmail(String email);

     boolean existsByEmail(String email);

    List<Student> findByRole(Role role);
}

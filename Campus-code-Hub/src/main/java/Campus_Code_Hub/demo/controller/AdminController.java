package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.model.Role;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/students")
@RequiredArgsConstructor
public class AdminController {

    private final StudentRepository studentRepository;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public String deleteStudentById(@PathVariable Long id) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH = " + auth);

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        if (student.getRole() == Role.ADMIN) {
            throw new RuntimeException("Cannot delete ADMIN user");
        }

        studentRepository.delete(student);
        return "Student deleted successfully";
    }
}

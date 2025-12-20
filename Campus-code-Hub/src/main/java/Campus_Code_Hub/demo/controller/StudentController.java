package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.StudentRepository;
import Campus_Code_Hub.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentService studentService;


    // GET /api/students -> get all students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudentby_id(@PathVariable Long id)
    {
        Optional<Student> student =studentService.getstudentbyid(id);
        return ResponseEntity.ok(student);
    }

}

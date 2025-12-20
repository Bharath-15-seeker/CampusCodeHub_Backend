package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.model.Role;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {

        return studentRepository.findByRole(Role.STUDENT);
    }

    public Optional<Student> getstudentbyid(Long id) {
        return studentRepository.findById(id);
    }
}

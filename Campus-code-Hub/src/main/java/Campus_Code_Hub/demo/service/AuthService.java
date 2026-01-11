package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.RegisterRequest;
import Campus_Code_Hub.demo.model.Role;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    private static final String COLLEGE_DOMAIN = "@krcollege.net";

    @Transactional
    public void register(RegisterRequest request) {

        if (request.getEmail() == null ||
                !request.getEmail().toLowerCase().endsWith(COLLEGE_DOMAIN)) {
            throw new RuntimeException("Only valid email addresses are allowed");
        }


        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Role role = (request.getRole() == null || request.getRole().isEmpty())
                ? Role.STUDENT
                : Role.valueOf(request.getRole());


        Student student = Student.builder()
                .email(request.getEmail().toLowerCase())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .department(request.getDepartment())
                .year(request.getYear())
                .registerNumber(request.getRegisterNumber())
                .role(role)
                .build();

        studentRepository.save(student);
    }
}

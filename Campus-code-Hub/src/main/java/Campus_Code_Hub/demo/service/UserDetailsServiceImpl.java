package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.model.Student; // or your User entity
import Campus_Code_Hub.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final StudentRepository studentRepository;

    public UserDetailsServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Map your Student entity to Spring Security UserDetails:
        return User.builder()
                .username(student.getEmail())
                .password(student.getPassword()) // hashed
                .authorities(student.getRole()) // adapt to collection if needed
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
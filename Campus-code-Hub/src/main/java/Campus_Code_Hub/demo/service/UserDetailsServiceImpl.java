package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.model.Student; // or your User entity
import Campus_Code_Hub.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .password(student.getPassword())
                .authorities(
                        List.of(new SimpleGrantedAuthority(student.getRole().name()))
                )
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();

    }
}
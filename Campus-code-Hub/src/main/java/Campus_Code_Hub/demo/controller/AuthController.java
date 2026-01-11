package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.Security.JwtUtil;
import Campus_Code_Hub.demo.dto.*;

import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.StudentRepository;
import Campus_Code_Hub.demo.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequiredArgsConstructor

@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    private final AuthService authService;
    private final StudentRepository studentRepository;

//    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, AuthService authService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtUtil = jwtUtil;
//        this.authService = authService;
//    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        System.out.println("enter endpoint");
        authService.register(request);
        return ResponseEntity.ok(new RegisterResponse("User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(), request.password()
                    )
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtUtil.generateToken(userDetails.getUsername());

            // 🔹 Fetch user entity (Student / Admin)
            Student student = studentRepository
                    .findByEmail(userDetails.getUsername())
                    .orElseThrow();

            UserResponse userResponse = new UserResponse(
                    student.getId(),
                    student.getEmail(),
                    student.getName(),
                    student.getRole()
            );

            return ResponseEntity.ok(
                    new AuthResponse(token, userResponse)
            );

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }


}

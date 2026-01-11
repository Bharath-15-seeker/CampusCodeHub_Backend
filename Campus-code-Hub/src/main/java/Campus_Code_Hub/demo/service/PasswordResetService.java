package Campus_Code_Hub.demo.service;

import Campus_Code_Hub.demo.dto.ResetPasswordRequest;
import Campus_Code_Hub.demo.model.PasswordResetToken;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final StudentRepository studentRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Transactional
    public void sendOtp(String email) {

        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("Email not registered"));

        // Remove old OTPs
        tokenRepository.deleteByEmail(email);

        String otp = String.valueOf(
                (int)(Math.random() * 900000) + 100000
        );

        PasswordResetToken token = new PasswordResetToken();
        token.setEmail(email);
        token.setOtp(otp);
        token.setExpiryTime(LocalDateTime.now().plusMinutes(10));

        tokenRepository.save(token);

        emailService.sendEmail(
                email,
                "Password Reset OTP",
                "Your OTP is: " + otp + "\nValid for 10 minutes."
        );
    }

    @Transactional
    public void resetPassword(ResetPasswordRequest request) {

        PasswordResetToken token = tokenRepository
                .findByEmailAndOtpAndUsedFalse(
                        request.getEmail(),
                        request.getOtp()
                )
                .orElseThrow(() ->
                        new RuntimeException("Invalid OTP"));

        if (token.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        Student student = studentRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        student.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        token.setUsed(true);

        studentRepository.save(student);
        tokenRepository.save(token);
    }
}


package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.*;
import Campus_Code_Hub.demo.model.Student;
import Campus_Code_Hub.demo.repository.StudentRepository;
import Campus_Code_Hub.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final StudentRepository studentRepository;

    /* =========================
       VIEW PROFILE (PUBLIC)
    ========================= */

    @GetMapping("/{id}")
    public StudentProfileDTO viewProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

    /* =========================
       UPDATE PROFILE (OWNER)
    ========================= */

    @PutMapping("/me")
    public StudentProfileDTO updateMyProfile(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody UpdateProfileRequest request) {

        String email = userDetails.getUsername();
        Student student = studentRepository.findByEmail(email)
                .orElseThrow();

        return profileService.updateProfile(student.getId(), request);
    }

}

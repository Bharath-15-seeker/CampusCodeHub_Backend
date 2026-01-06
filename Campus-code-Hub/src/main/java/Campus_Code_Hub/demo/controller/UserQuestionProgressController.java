package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.MyQuestionProgressResponse;
import Campus_Code_Hub.demo.dto.QuestionProgressRequest;
import Campus_Code_Hub.demo.service.UserQuestionProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public class UserQuestionProgressController {

    private final UserQuestionProgressService progressService;

    @PostMapping("/question")
    public ResponseEntity<String> updateQuestionProgress(
            @RequestBody QuestionProgressRequest request,
            Authentication authentication
    ) {
        String email = authentication.getName(); // logged-in user
        progressService.updateProgress(email, request);
        return ResponseEntity.ok("Progress updated");
    }


    @GetMapping("/my")
    public ResponseEntity<List<MyQuestionProgressResponse>> getMyProgress(
            Authentication authentication
    ) {
        String email = authentication.getName();
        return ResponseEntity.ok(progressService.getMyProgress(email));
    }
}


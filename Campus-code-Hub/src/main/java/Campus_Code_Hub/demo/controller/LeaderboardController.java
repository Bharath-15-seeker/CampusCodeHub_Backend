package Campus_Code_Hub.demo.controller;

import Campus_Code_Hub.demo.dto.LeaderboardResponse;
import Campus_Code_Hub.demo.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    // 🧠 Coding Leaderboard
    @GetMapping("/coding")
    public ResponseEntity<List<LeaderboardResponse>> codingLeaderboard() {
        return ResponseEntity.ok(
                leaderboardService.getCodingLeaderboard()
        );
    }

    // 🧠 Aptitude Leaderboard
    @GetMapping("/aptitude")
    public ResponseEntity<List<LeaderboardResponse>> aptitudeLeaderboard() {
        return ResponseEntity.ok(
                leaderboardService.getAptitudeLeaderboard()
        );
    }
}


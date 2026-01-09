package Campus_Code_Hub.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LeaderboardResponse {

    private Long studentId;
    private String studentName;
    private String studentEmail;
    private int totalPoints;
}


package Campus_Code_Hub.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LeaderboardStudentDTO {

    private Long id;
    private String name;
    private int points;

}
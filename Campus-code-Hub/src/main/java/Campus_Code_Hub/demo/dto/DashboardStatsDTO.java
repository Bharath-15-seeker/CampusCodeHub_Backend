package Campus_Code_Hub.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardStatsDTO {

    private long totalStudents;
    private long totalQuestions;
    private long totalEvents;
    private long totalCareerTracks;

}
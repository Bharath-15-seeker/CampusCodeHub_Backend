package Campus_Code_Hub.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class SheetResponse {
    private Long id;
    private String sheetType; // CODING / APTITUDE
    private String title;
    private List<TopicResponse> topics;
}

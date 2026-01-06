package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class CreateTopicRequest {
    private Long sheetId;   // Coding or Aptitude sheet
    private String name;
    private int orderIndex;
}


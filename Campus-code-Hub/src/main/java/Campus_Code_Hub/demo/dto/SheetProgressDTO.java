package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class SheetProgressDTO {

    private String sheetType;
    private long totalQuestions;
    private long solvedQuestions;
    private int progressPercentage;

    // getters & setters
}


package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class UpdateQuestionRequest {

    private String title;
    private String difficulty;

    private String problemLink;  // coding
    private String videoLink;    // aptitude

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;

    private String correctOption;
    private String explanation;
}

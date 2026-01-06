package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class CreateAptitudeQuestionRequest {
    private Long subTopicId;
    private String title;
    private String difficulty;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;

    private String explanation;
    private String videoLink;
}


package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class QuestionResponse {
    private Long id;
    private String title;
    private String difficulty;
    private String problemLink;
    private String videoLink;

    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}

package Campus_Code_Hub.demo.dto;

import Campus_Code_Hub.demo.model.QuestionType;
import lombok.Data;

@Data
public class QuestionResponse {
    private Long id;
    private String title;
    private String difficulty;
    private String problemLink;
    private String videoLink;
    private String correctOption;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private QuestionType sheetType;
    private Boolean solved;
}

package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class CreateCodingQuestionRequest {
    private Long subTopicId;
    private String title;
    private String difficulty;
    private String problemLink;
    private String videoLink;
}


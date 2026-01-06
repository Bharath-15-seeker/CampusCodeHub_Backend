package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class MyQuestionProgressResponse {

    private Long questionId;
    private String questionTitle;
    private String difficulty;
    private boolean solved;
}

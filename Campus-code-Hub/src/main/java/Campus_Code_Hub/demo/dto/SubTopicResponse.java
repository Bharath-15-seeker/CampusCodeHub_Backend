package Campus_Code_Hub.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class SubTopicResponse {
    private Long id;
    private String name;
    private String youtubeLink;
    private List<QuestionResponse> questions;
}

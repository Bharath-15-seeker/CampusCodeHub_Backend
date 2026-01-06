package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class CreateSubTopicRequest {
    private Long topicId;
    private String name;
    private String youtubeLink;   // concept video
}


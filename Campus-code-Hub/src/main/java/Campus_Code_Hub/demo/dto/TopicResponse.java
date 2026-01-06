package Campus_Code_Hub.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TopicResponse {
    private Long id;
    private String name;
    private int orderIndex;
    private List<SubTopicResponse> subTopics;
}


package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class UpdateCareerResourceRequest {

    private String title;
    private String description;
    private String youtubeLink;
    private Integer orderIndex;
}


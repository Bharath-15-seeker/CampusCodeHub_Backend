package Campus_Code_Hub.demo.dto;

import lombok.Data;

@Data
public class EventRegistrationResponse {

    private Long registrationId;

    private Long studentId;
    private String studentName;
    private String studentEmail;

    private Integer points;
}

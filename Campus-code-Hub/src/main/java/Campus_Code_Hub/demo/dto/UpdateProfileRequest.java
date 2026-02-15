package Campus_Code_Hub.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileRequest {

    private String name;
    private String department;
    private Integer year;
    private String registerNumber;
    private String codingProfileUrl;
}

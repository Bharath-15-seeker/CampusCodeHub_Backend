package Campus_Code_Hub.demo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentProfileDTO {

    private Long id;
    private String name;
    private String email;
    private String department;
    private int year;
    private String registerNumber;
    private String codingProfileUrl;

    private int codingPoints;
    private int aptitudePoints;
}

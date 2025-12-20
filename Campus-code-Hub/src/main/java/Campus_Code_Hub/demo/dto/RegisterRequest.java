package Campus_Code_Hub.demo.dto;


import lombok.Data;

@Data
public class RegisterRequest {

    private String email;
    private String password;
    private String name;

    private String department;
    private int year;
    private String registerNumber;

    // ADMIN or STUDENT
    private String role;
}

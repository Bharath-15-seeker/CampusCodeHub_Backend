package Campus_Code_Hub.demo.dto;

public record AuthResponse(
        String token,
        UserResponse user
) {}

package Campus_Code_Hub.demo.dto;

import Campus_Code_Hub.demo.model.Role;

public record UserResponse(
        Long id,
        String email,
        String name,
        Role role
) {}

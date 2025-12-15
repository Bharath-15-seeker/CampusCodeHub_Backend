package Campus_Code_Hub.demo.dto;


import lombok.Data;
import org.jspecify.annotations.Nullable;


public record AuthRequest(String email, String password){
    public @Nullable Object getEmail() {
        return null;
    }

    public @Nullable Object getPassword() {
        return null;
    }
}

package com.sabrigulseven.flight.dto.request;

import com.sabrigulseven.flight.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(min = 3, max = 30, message = "Name must be min 3, max 30 characters")
    private String name;

    @Size(min = 6, max = 20, message = "Username must be min 3, max 20 characters")
    private String username;

    @Size(min = 6, max = 30, message = "Password must be min 6, max 30 characters")
    private String password;

    @Email(message = "Must be Email")
    private String email;

    private UserRole role;
}

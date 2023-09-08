package com.sabrigulseven.flight.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
    @Size(min = 6, max = 20, message = "Username must be min 3, max 20 characters")
    private String username;

    @Size(min = 6, max = 30, message = "Password must be min 6, max 30 characters")
    private String password;
}

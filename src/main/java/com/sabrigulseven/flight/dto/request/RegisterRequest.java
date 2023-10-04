package com.sabrigulseven.flight.dto.request;

import com.sabrigulseven.flight.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


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

    public RegisterRequest(String name, String username, String password, String email, UserRole role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }


}

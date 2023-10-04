package com.sabrigulseven.flight.controller;


import com.sabrigulseven.flight.api.AuthenticationApi;
import com.sabrigulseven.flight.dto.request.AuthenticationRequest;
import com.sabrigulseven.flight.dto.request.RegisterRequest;
import com.sabrigulseven.flight.dto.response.AuthenticationResponse;
import com.sabrigulseven.flight.service.AuthenticationService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController implements AuthenticationApi {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @RateLimiter(name = "basic")
    public ResponseEntity<AuthenticationResponse> register (
            @Valid @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    @RateLimiter(name = "auth")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
}

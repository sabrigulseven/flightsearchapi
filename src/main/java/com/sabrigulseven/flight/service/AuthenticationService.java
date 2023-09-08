package com.sabrigulseven.flight.service;


import com.sabrigulseven.flight.config.JwtService;
import com.sabrigulseven.flight.dto.request.AuthenticationRequest;
import com.sabrigulseven.flight.dto.request.RegisterRequest;
import com.sabrigulseven.flight.dto.response.AuthenticationResponse;
import com.sabrigulseven.flight.exception.UserNotFoundException;
import com.sabrigulseven.flight.model.User;
import com.sabrigulseven.flight.model.UserRole;
import com.sabrigulseven.flight.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userRole(request.getRole())
                .build();
        System.out.println(user);
        repository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User user = findByUsername(request);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    private User findByUsername(AuthenticationRequest request) {
        return repository.findByUsername(request.getUsername())
                .orElseThrow(
                        () -> new UserNotFoundException("Username not found"));
    }
}

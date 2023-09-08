package com.sabrigulseven.flight.api;

import com.sabrigulseven.flight.dto.request.AuthenticationRequest;
import com.sabrigulseven.flight.dto.request.RegisterRequest;
import com.sabrigulseven.flight.dto.response.AuthenticationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Authentication")
public interface AuthenticationApi {

    /**
     * Registers a new user and returns an authentication token upon success.
     *
     * @param request The registration request.
     * @return ResponseEntity containing the authentication response with a bearer token if successful.
     */
    @Operation(
            description = "Registers a new user.",
            summary = "Register User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The registration request.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RegisterRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - User registered successfully. The response includes a bearer token.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Username or email already exists or invalid input. "
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @Valid @RequestBody RegisterRequest request
    );

    /**
     * Authenticates a user and returns an authentication token upon success.
     *
     * @param request The authentication request.
     * @return ResponseEntity containing the authentication response with a bearer token if successful.
     */
    @Operation(
            description = "Authenticates a user.",
            summary = "Authenticate User",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "The authentication request.",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthenticationRequest.class)
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - User authenticated successfully. The response includes a bearer token.",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AuthenticationResponse.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Invalid authentication request. "
                    )
            }
    )
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate (
            @Valid @RequestBody AuthenticationRequest request
    );

}

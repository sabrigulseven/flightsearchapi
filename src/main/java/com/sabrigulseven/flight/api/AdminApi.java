package com.sabrigulseven.flight.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Tag(name = "Admin")
@SecurityRequirement(name = "bearerAuth", scopes = {"ROLE_ADMIN"})
public interface AdminApi {

    /**
     * Retrieves data for the admin endpoint.
     *
     * @return ResponseEntity with admin data.
     */
    @Operation(
            description = "Retrieve data for the admin endpoint.",
            summary = "Get Admin Data",
            security = @SecurityRequirement(name = "bearerAuth", scopes = {"ROLE_ADMIN"}),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - Admin data retrieved successfully."
                    )
            }
    )
    @GetMapping("/admin-data")
    ResponseEntity<String> getAdminData();
}

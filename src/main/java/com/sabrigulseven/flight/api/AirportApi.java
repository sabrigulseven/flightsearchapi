package com.sabrigulseven.flight.api;

import com.sabrigulseven.flight.dto.AirportDto;
import com.sabrigulseven.flight.dto.request.CreateAirportRequest;
import com.sabrigulseven.flight.dto.request.UpdateAirportRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Airport")
@Validated
public interface AirportApi {

    @Operation(
            description = "Create an airport",
            summary = "Create Airport",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The airport information to create.", required = true, content = @Content(mediaType = "application/json")),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success - Airport created successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Invalid request. Check the request format and data for errors.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping
    ResponseEntity<String> createAirport(
            @Valid @RequestBody CreateAirportRequest createAirportRequest
    );


    @Operation(
            description = "Get an airport by ID",
            summary = "Get Airport by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - Airport retrieved successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AirportDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found - Airport with the specified ID does not exist.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<AirportDto> getAirportById(
            @Parameter(description = "The ID of the airport to retrieve.", required = true)
            @PathVariable Long id
    );


    @Operation(
            description = "Update an airport",
            summary = "Update Airport",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "The updated airport information.", required = true, content = @Content(mediaType = "application/json")),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - Airport updated successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Invalid request. Check the request format and data for errors.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found - Airport with the specified ID does not exist.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<String> updateAirport(
            @Parameter(description = "The ID of the airport to update.", required = true)
            @PathVariable Long id,
            @Valid @RequestBody UpdateAirportRequest updateAirportRequest
    );


    @Operation(
            description = "Delete an airport by ID",
            summary = "Delete Airport by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Success - Airport deleted successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found - Airport with the specified ID does not exist.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteAirportById(
            @Parameter(description = "The ID of the airport to delete.", required = true)
            @PathVariable Long id
    );


    @Operation(
            description = "Get all airports",
            summary = "Get All Airports",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - All airports retrieved successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AirportDto.class))
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<AirportDto>> getAllAirports();
}

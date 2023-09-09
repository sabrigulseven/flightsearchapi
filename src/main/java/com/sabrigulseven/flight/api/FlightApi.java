package com.sabrigulseven.flight.api;

import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.dto.request.CreateFlightRequest;
import com.sabrigulseven.flight.dto.request.UpdateFlightRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Tag(name = "Flight")
@Validated
public interface FlightApi {

    @Operation(
            description = "Create a flight",
            summary = "Create Flight",
            requestBody = @RequestBody(description = "The flight information to create.", required = true, content = @Content(mediaType = "application/json")),
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success - Flight created successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Invalid request. Check the request format and data for errors.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PostMapping
    ResponseEntity<FlightDto> createFlight(
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateFlightRequest createFlightRequest
    );

    @Operation(
            description = "Get a flight by ID",
            summary = "Get Flight by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - Flight retrieved successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightDto.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found - Flight with the specified ID does not exist.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @GetMapping("/{id}")
    ResponseEntity<FlightDto> getFlightById(
            @Parameter(description = "The ID of the flight to retrieve.", required = true)
            @PathVariable Long id
    );

    @Operation(
            description = "Update a flight",
            summary = "Update Flight",
            requestBody = @RequestBody(description = "The updated flight information.", required = true,
                    content = @Content(mediaType = "application/json")),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - Flight updated successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad Request - Invalid request. Check the request format and data for errors.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found - Flight with the specified ID does not exist.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @PutMapping("/{id}")
    ResponseEntity<FlightDto> updateFlight(
            @Parameter(description = "The ID of the flight to update.", required = true)
            @PathVariable Long id,
            @Valid @org.springframework.web.bind.annotation.RequestBody UpdateFlightRequest updateFlightRequest
    );

    @Operation(
            description = "Delete a flight by ID",
            summary = "Delete Flight by ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Success - Flight deleted successfully."
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found - Flight with the specified ID does not exist.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    )
            }
    )
    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteFlightById(
            @Parameter(description = "The ID of the flight to delete.", required = true)
            @PathVariable Long id
    );

    @Operation(
            description = "Get all flights",
            summary = "Get All Flights",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - All flights retrieved successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightDto.class))
                    )
            }
    )
    @GetMapping
    ResponseEntity<List<FlightDto>> getAllFlights();

    @Operation(
            description = "Search flights based on origin, destination, departure date, and return date",
            summary = "Search Flights",
            parameters = {
                    @Parameter(
                            name = "originAirportId",
                            description = "The ID of the origin airport",
                            required = true,
                            example = "1"
                    ),
                    @Parameter(
                            name = "destinationAirportId",
                            description = "The ID of the destination airport",
                            required = true,
                            example = "2"
                    ),
                    @Parameter(
                            name = "departureDate",
                            description = "The departure date (yyyy-MM-dd), optional",
                            example = "2023-09-15"
                    ),
                    @Parameter(
                            name = "returnDate",
                            description = "The return date (yyyy-MM-dd), optional",
                            example = "2023-09-22"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success - Flights retrieved successfully.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FlightDto.class))
                    )
            }
    )
    @GetMapping("/search")
    ResponseEntity<List<FlightDto>> searchFlights(
            @RequestParam Long originAirportId,
            @RequestParam Long destinationAirportId,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate returnDate
    );
}

package com.sabrigulseven.flight.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFlightRequest {

    @NotNull(message = "Origin Airport Id must not be null")
    private Long originAirportId;

    @NotNull(message = "Destination Airport Id must not be null")
    private Long destinationAirportId;

    @FutureOrPresent(message = "Departure date must be in the future or present")
    private OffsetDateTime departureDate;

    @FutureOrPresent(message = "Return date must be in the future or present")
    private OffsetDateTime returnDate;

    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    private BigDecimal price;
}

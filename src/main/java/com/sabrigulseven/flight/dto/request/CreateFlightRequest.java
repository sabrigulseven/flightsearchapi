package com.sabrigulseven.flight.dto.request;

import com.sabrigulseven.flight.model.Airport;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFlightRequest {

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

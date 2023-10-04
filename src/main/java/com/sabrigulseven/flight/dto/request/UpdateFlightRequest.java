package com.sabrigulseven.flight.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


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

    public UpdateFlightRequest(Long originAirportId, Long destinationAirportId, OffsetDateTime departureDate, OffsetDateTime returnDate, BigDecimal price) {
        this.originAirportId = originAirportId;
        this.destinationAirportId = destinationAirportId;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
    }

    public Long getOriginAirportId() {
        return originAirportId;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    public OffsetDateTime getDepartureDate() {
        return departureDate;
    }

    public OffsetDateTime getReturnDate() {
        return returnDate;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

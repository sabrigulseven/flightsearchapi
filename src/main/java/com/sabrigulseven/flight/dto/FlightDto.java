package com.sabrigulseven.flight.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


public class FlightDto {
    private Long id;

    private FlightAirportDto origin;

    private FlightAirportDto destination;

    private OffsetDateTime departureDate;

    private OffsetDateTime returnDate;

    private BigDecimal price;

    public FlightDto(Long id, FlightAirportDto origin, FlightAirportDto destination, OffsetDateTime departureDate, OffsetDateTime returnDate, BigDecimal price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public FlightAirportDto getOrigin() {
        return origin;
    }

    public FlightAirportDto getDestination() {
        return destination;
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

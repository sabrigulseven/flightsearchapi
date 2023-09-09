package com.sabrigulseven.flight.dto;

import com.sabrigulseven.flight.model.Airport;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long id;

    private FlightAirportDto origin;

    private FlightAirportDto destination;

    private OffsetDateTime departureDate;

    private OffsetDateTime returnDate;

    private BigDecimal price;
}

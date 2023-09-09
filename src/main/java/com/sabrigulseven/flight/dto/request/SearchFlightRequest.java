package com.sabrigulseven.flight.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchFlightRequest {
    private Long originAirportId;

    private Long destinationAirportId;

    private LocalDate departureDate;

    private LocalDate returnDate;
}

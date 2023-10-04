package com.sabrigulseven.flight.dto.request;

import java.time.LocalDate;


public class SearchFlightRequest {
    private Long originAirportId;

    private Long destinationAirportId;

    private LocalDate departureDate;

    private LocalDate returnDate;

    public SearchFlightRequest(Long originAirportId, Long destinationAirportId, LocalDate departureDate, LocalDate returnDate) {
        this.originAirportId = originAirportId;
        this.destinationAirportId = destinationAirportId;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public Long getOriginAirportId() {
        return originAirportId;
    }

    public Long getDestinationAirportId() {
        return destinationAirportId;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }
}

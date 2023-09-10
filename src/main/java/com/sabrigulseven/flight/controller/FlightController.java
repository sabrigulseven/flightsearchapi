package com.sabrigulseven.flight.controller;

import com.sabrigulseven.flight.api.FlightApi;
import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.dto.request.CreateFlightRequest;
import com.sabrigulseven.flight.dto.request.SearchFlightRequest;
import com.sabrigulseven.flight.dto.request.UpdateFlightRequest;
import com.sabrigulseven.flight.service.FlightService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RequiredArgsConstructor
public class FlightController implements FlightApi {

    private final FlightService flightService;

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<FlightDto> createFlight(CreateFlightRequest createFlightRequest) {
        return ResponseEntity.ok(flightService.save(createFlightRequest));
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<FlightDto> getFlightById(Long id) {
        return ResponseEntity.ok(flightService.getById(id));
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<FlightDto> updateFlight(Long id, UpdateFlightRequest updateFlightRequest) {
        return ResponseEntity.ok(flightService.update(id, updateFlightRequest));
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<String> deleteFlightById(Long id) {
        return ResponseEntity.ok(flightService.deleteById(id));

    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAll());
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<List<FlightDto>> searchFlights(
            Long originAirportId,
            Long destinationAirportId,
            LocalDate departureDate,
            LocalDate returnDate) {

        SearchFlightRequest request = new SearchFlightRequest();
        request.setOriginAirportId(originAirportId);
        request.setDestinationAirportId(destinationAirportId);
        request.setDepartureDate(departureDate);
        request.setReturnDate(returnDate);

        return ResponseEntity.ok(flightService.searchFlights(request));
    }
}

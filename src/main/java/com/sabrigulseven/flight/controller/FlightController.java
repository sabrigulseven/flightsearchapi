package com.sabrigulseven.flight.controller;

import com.sabrigulseven.flight.api.FlightApi;
import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.dto.request.CreateFlightRequest;
import com.sabrigulseven.flight.dto.request.SearchFlightRequest;
import com.sabrigulseven.flight.dto.request.UpdateFlightRequest;
import com.sabrigulseven.flight.service.FlightService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/flights")
@RateLimiter(name = "basic")
public class FlightController implements FlightApi {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @Override
    public ResponseEntity<FlightDto> createFlight(CreateFlightRequest createFlightRequest) {
        return ResponseEntity.ok(flightService.save(createFlightRequest));
    }

    @Override
    public ResponseEntity<FlightDto> getFlightById(Long id) {
        return ResponseEntity.ok(flightService.getById(id));
    }

    @Override
    public ResponseEntity<FlightDto> updateFlight(Long id, UpdateFlightRequest updateFlightRequest) {
        return ResponseEntity.ok(flightService.update(id, updateFlightRequest));
    }

    @Override
    public ResponseEntity<String> deleteFlightById(Long id) {
        return ResponseEntity.ok(flightService.deleteById(id));

    }

    @Override
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAll());
    }

    @Override
    public ResponseEntity<List<FlightDto>> searchFlights(
            Long originAirportId,
            Long destinationAirportId,
            LocalDate departureDate,
            LocalDate returnDate) {

        SearchFlightRequest request = new SearchFlightRequest(originAirportId, destinationAirportId, departureDate, returnDate);
        return ResponseEntity.ok(flightService.searchFlights(request));
    }
}

package com.sabrigulseven.flight.controller;

import com.sabrigulseven.flight.api.AirportApi;
import com.sabrigulseven.flight.dto.AirportDto;
import com.sabrigulseven.flight.dto.request.CreateAirportRequest;
import com.sabrigulseven.flight.dto.request.UpdateAirportRequest;
import com.sabrigulseven.flight.service.AirportService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airports")
@RequiredArgsConstructor
public class AirportController implements AirportApi {
    private final AirportService airportService;

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<AirportDto> createAirport(CreateAirportRequest createAirportRequest) {
        return ResponseEntity.ok(airportService.save(createAirportRequest));
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<AirportDto> getAirportById(Long id) {
        return ResponseEntity.ok(airportService.getById(id));
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<AirportDto> updateAirport(Long id, UpdateAirportRequest updateAirportRequest) {
        return ResponseEntity.ok(airportService.update(id, updateAirportRequest));
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<String> deleteAirportById(Long id) {
        return ResponseEntity.ok(airportService.deleteById(id));
    }

    @Override
    @RateLimiter(name = "basic")
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }
}

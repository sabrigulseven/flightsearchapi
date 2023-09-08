package com.sabrigulseven.flight.controller;

import com.sabrigulseven.flight.api.FlightApi;
import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.dto.request.CreateFlightRequest;
import com.sabrigulseven.flight.dto.request.UpdateFlightRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flight")
public class FlightController implements FlightApi {
    @Override
    public ResponseEntity<String> createFlight(CreateFlightRequest createFlightRequest) {
        return null;
    }

    @Override
    public ResponseEntity<FlightDto> getFlightById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateFlight(Long id, UpdateFlightRequest updateFlightRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteFlightById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return null;
    }
}

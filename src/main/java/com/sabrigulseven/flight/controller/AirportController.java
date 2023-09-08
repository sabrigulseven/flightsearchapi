package com.sabrigulseven.flight.controller;

import com.sabrigulseven.flight.api.AirportApi;
import com.sabrigulseven.flight.dto.AirportDto;
import com.sabrigulseven.flight.dto.request.CreateAirportRequest;
import com.sabrigulseven.flight.dto.request.UpdateAirportRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/airport")
public class AirportController implements AirportApi {
    @Override
    public ResponseEntity<String> createAirport(CreateAirportRequest createAirportRequest) {
        return null;
    }

    @Override
    public ResponseEntity<AirportDto> getAirportById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateAirport(Long id, UpdateAirportRequest updateAirportRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteAirportById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        return null;
    }
}

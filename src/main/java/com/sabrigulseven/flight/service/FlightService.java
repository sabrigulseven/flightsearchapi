package com.sabrigulseven.flight.service;

import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.dto.converter.FlightDtoConverter;
import com.sabrigulseven.flight.dto.request.CreateFlightRequest;
import com.sabrigulseven.flight.dto.request.UpdateFlightRequest;
import com.sabrigulseven.flight.exception.FlightNotFoundException;
import com.sabrigulseven.flight.model.Airport;
import com.sabrigulseven.flight.model.Flight;
import com.sabrigulseven.flight.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository repository;
    private final AirportService airportService;
    private final FlightDtoConverter converter;
    protected Flight findById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new FlightNotFoundException("Flight could not found by id: " + id));
    }

    public FlightDto save(CreateFlightRequest request) {
        Airport origin = airportService.findById(request.getOriginAirportId());
        Airport destination = airportService.findById(request.getDestinationAirportId());
        Flight flight = Flight.builder()
                .origin(origin)
                .destination(destination)
                .departureDate(request.getReturnDate())
                .returnDate(request.getReturnDate())
                .price(request.getPrice())
                .build();
        return converter.convert(repository.save(flight));
    }

    public FlightDto getById(Long id) {
        return converter.convert(findById(id));
    }

    public FlightDto update(Long id, UpdateFlightRequest request) {
        Airport origin = airportService.findById(request.getOriginAirportId());
        Airport destination = airportService.findById(request.getDestinationAirportId());
        Flight flight = findById(id);
        flight.setOrigin(origin);
        flight.setDestination(destination);
        flight.setDepartureDate(request.getDepartureDate());
        flight.setReturnDate(request.getReturnDate());
        flight.setPrice(request.getPrice());
        return converter.convert(repository.save(flight));
    }

    public String deleteById(Long id) {
        Flight flight = findById(id);
        repository.delete(flight);
        return "Flight deleted by id: "+id;
    }

    public List<FlightDto> getAll() {
        return repository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}

package com.sabrigulseven.flight.service;

import com.sabrigulseven.flight.dto.AirportDto;
import com.sabrigulseven.flight.dto.converter.AirportDtoConverter;
import com.sabrigulseven.flight.dto.request.CreateAirportRequest;
import com.sabrigulseven.flight.dto.request.UpdateAirportRequest;
import com.sabrigulseven.flight.exception.AirportNotFoundException;
import com.sabrigulseven.flight.model.Airport;
import com.sabrigulseven.flight.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository repository;
    private final AirportDtoConverter converter;

    protected Airport findById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new AirportNotFoundException("Airport could not find by id: " + id));
    }

    public AirportDto save(CreateAirportRequest createAirportRequest) {
        Airport airport = new Airport();
        airport.setCity(createAirportRequest.getCity());
        return converter.convert(repository.save(airport));
    }

    public List<AirportDto> getAllAirports() {
        return repository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public AirportDto getById(Long id) {
        return converter.convert(findById(id));
    }

    public AirportDto update(Long id, UpdateAirportRequest updateAirportRequest) {
        Airport airport = findById(id);
        airport.setCity(updateAirportRequest.getCity());
        return converter.convert(repository.save(airport));
    }

    public String  deleteById(Long id) {
        Airport airport = findById(id);
        repository.delete(airport);
        return "Airport deleted with id: " +id;
    }
}

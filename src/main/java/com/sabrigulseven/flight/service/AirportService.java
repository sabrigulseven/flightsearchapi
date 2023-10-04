package com.sabrigulseven.flight.service;

import com.sabrigulseven.flight.dto.AirportDto;
import com.sabrigulseven.flight.dto.converter.AirportDtoConverter;
import com.sabrigulseven.flight.dto.request.CreateAirportRequest;
import com.sabrigulseven.flight.dto.request.UpdateAirportRequest;
import com.sabrigulseven.flight.exception.AirportNotFoundException;
import com.sabrigulseven.flight.model.Airport;
import com.sabrigulseven.flight.repository.AirportRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"airports"})
public class AirportService {
    private final AirportRepository repository;
    private final AirportDtoConverter converter;

    public AirportService(AirportRepository repository, AirportDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Cacheable(key = "#id")
    protected Airport findById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new AirportNotFoundException("Airport could not find by id: " + id));
    }

    @CacheEvict(key = "'all'", allEntries = true)
    public AirportDto save(CreateAirportRequest createAirportRequest) {
        Airport airport = new Airport(createAirportRequest.getCity());
        return converter.convert(repository.save(airport));
    }

    @Cacheable(key = "'all'")
    public List<AirportDto> getAllAirports() {
        return repository.findAll()
                .stream()
                .map(converter::convert)
                .toList();
    }

    @Cacheable(key = "#id")
    public AirportDto getById(Long id) {
        return converter.convert(findById(id));
    }

    @CacheEvict(key = "'all'", allEntries = true)
    public AirportDto update(Long id, UpdateAirportRequest updateAirportRequest) {
        Airport airport = findById(id);
        Airport updatedAirport = new Airport(airport.getId(), updateAirportRequest.getCity());
        return converter.convert(repository.save(updatedAirport));
    }

    @CacheEvict(key = "'all'", allEntries = true)
    public String deleteById(Long id) {
        Airport airport = findById(id);
        repository.delete(airport);
        return "Airport deleted with id: " + id;
    }
}

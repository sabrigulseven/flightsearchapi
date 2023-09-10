package com.sabrigulseven.flight.service;

import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.dto.converter.FlightDtoConverter;
import com.sabrigulseven.flight.dto.request.CreateFlightRequest;
import com.sabrigulseven.flight.dto.request.SearchFlightRequest;
import com.sabrigulseven.flight.dto.request.UpdateFlightRequest;
import com.sabrigulseven.flight.exception.FlightNotFoundException;
import com.sabrigulseven.flight.model.Airport;
import com.sabrigulseven.flight.model.Flight;
import com.sabrigulseven.flight.repository.FlightRepository;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.function.Function;
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
                .departureDate(request.getDepartureDate())
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
        return "Flight deleted by id: " + id;
    }

    public List<FlightDto> getAll() {
        return repository.findAll()
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public List<FlightDto> searchFlights(SearchFlightRequest request) {
        Specification<Flight> spec = buildSearchSpecification(request);
        Sort sort = Sort.by("departureDate").ascending();

        return repository.findAll(spec, sort)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    private Specification<Flight> buildSearchSpecification(SearchFlightRequest request) {
        Specification<Flight> spec = Specification.where(null);

        if (request.getOriginAirportId() != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get("origin").get("id"), request.getOriginAirportId()));
        }

        if (request.getDestinationAirportId() != null) {
            spec = spec.and((root, query, criteriaBuilder) -> criteriaBuilder
                    .equal(root.get("destination").get("id"), request.getDestinationAirportId()));
        }

        if (request.getDepartureDate() != null) {
            spec = spec.and(buildDateBetweenSpecification(
                    root -> root.get("departureDate"),
                    request.getDepartureDate()
            ));
        }

        if (request.getReturnDate() != null) {
            spec = spec.and(buildDateBetweenSpecification(
                    root -> root.get("returnDate"),
                    request.getReturnDate()
            ));
        }

        return spec;
    }

    private Specification<Flight> buildDateBetweenSpecification(
            Function<Root<Flight>, Expression<OffsetDateTime>> dateFieldExtractor,
            LocalDate date) {
        OffsetDateTime startOfDay = date.atStartOfDay().atOffset(ZoneOffset.UTC);
        OffsetDateTime endOfDay = date.atTime(23, 59, 59, 999_999_999).atOffset(ZoneOffset.UTC);

        return (root, query, criteriaBuilder) -> criteriaBuilder.between(
                dateFieldExtractor.apply(root),
                startOfDay,
                endOfDay
        );
    }

    @Transactional
    public List<FlightDto> saveFlights(List<FlightDto> flightsDto) {
        List<Flight> savedFlights = repository.saveAll(flightsDto
                .stream()
                .map(converter::revert)
                .collect(Collectors.toList()));

        return savedFlights.stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }
}

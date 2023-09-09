package com.sabrigulseven.flight.service;

import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.dto.converter.FlightDtoConverter;
import com.sabrigulseven.flight.dto.request.CreateFlightRequest;
import com.sabrigulseven.flight.dto.request.UpdateFlightRequest;
import com.sabrigulseven.flight.exception.FlightNotFoundException;
import com.sabrigulseven.flight.model.Airport;
import com.sabrigulseven.flight.model.Flight;
import com.sabrigulseven.flight.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class FlightServiceTest {

    private FlightService flightService;
    private FlightRepository flightRepository;
    private AirportService airportService;
    private FlightDtoConverter converter;

    @BeforeEach
    public void init() {
        flightRepository = mock(FlightRepository.class);
        airportService = mock(AirportService.class);
        converter = mock(FlightDtoConverter.class);
        flightService = new FlightService(flightRepository, airportService, converter);
    }

    @Test
    public void testFindFlightById_whenFlightExists_ShouldReturnFlight() {
        Long flightId = 1L;
        OffsetDateTime departureDate = OffsetDateTime.now();
        BigDecimal price = BigDecimal.valueOf(500.0);
        Flight flight = new Flight(flightId, null, null, departureDate, null, price);

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        Flight result = flightService.findById(flightId);

        assertEquals(result, flight);
    }

    @Test
    public void testFindFlightById_whenFlightDoesNotExist_ShouldThrowFlightNotFoundException() {
        Long flightId = 1L;

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> flightService.findById(flightId));
    }

    @Test
    public void testGetFlightById_whenFlightExists_ShouldReturnFlightDto() {
        Long flightId = 1L;
        OffsetDateTime departureDate = OffsetDateTime.now();
        BigDecimal price = BigDecimal.valueOf(500.0);
        Flight flight = new Flight(flightId, null, null, departureDate, null, price);
        FlightDto flightDto = new FlightDto(flightId, null, null, departureDate, null, price);

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));
        Mockito.when(converter.convert(flight)).thenReturn(flightDto);

        FlightDto result = flightService.getById(flightId);

        assertEquals(result, flightDto);
    }

    @Test
    public void testGetFlightById_whenFlightDoesNotExist_ShouldThrowFlightNotFoundException() {
        Long flightId = 1L;

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> flightService.getById(flightId));

        Mockito.verifyNoInteractions(converter);
    }

    @Test
    public void testSaveFlight_whenCreateFlightCalledWithValidRequest_ShouldReturnValidFlightDto() {
        CreateFlightRequest request = new CreateFlightRequest(1L, 2L, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(500.0));
        Flight flight = new Flight(null, null, null, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(500.0));
        FlightDto flightDto = new FlightDto(1L, null, null, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(500.0));

        Mockito.when(airportService.findById(request.getOriginAirportId())).thenReturn(new Airport());
        Mockito.when(airportService.findById(request.getDestinationAirportId())).thenReturn(new Airport());
        Mockito.when(flightRepository.save(Mockito.any(Flight.class))).thenReturn(flight);
        Mockito.when(converter.convert(flight)).thenReturn(flightDto);

        FlightDto result = flightService.save(request);

        assertEquals(flightDto, result);
    }

    @Test
    public void testUpdateFlight_whenFlightExists_ShouldReturnUpdatedFlightDto() {
        Long flightId = 1L;
        UpdateFlightRequest request = new UpdateFlightRequest(2L, 3L, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(600.0));
        Flight existingFlight = new Flight(flightId, null, null, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(500.0));
        Flight updatedFlight = new Flight(flightId, null, null, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(600.0));
        FlightDto flightDto = new FlightDto(flightId, null, null, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(600.0));

        Mockito.when(airportService.findById(request.getOriginAirportId())).thenReturn(new Airport());
        Mockito.when(airportService.findById(request.getDestinationAirportId())).thenReturn(new Airport());
        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.of(existingFlight));
        Mockito.when(flightRepository.save(Mockito.any(Flight.class))).thenReturn(updatedFlight);
        Mockito.when(converter.convert(updatedFlight)).thenReturn(flightDto);

        FlightDto result = flightService.update(flightId, request);

        assertEquals(result.getPrice(), BigDecimal.valueOf(600.0));
    }

    @Test
    public void testUpdateFlight_whenFlightDoesNotExist_ShouldThrowFlightNotFoundException() {
        Long flightId = 1L;
        UpdateFlightRequest request = new UpdateFlightRequest(2L, 3L, OffsetDateTime.now(), OffsetDateTime.now(), BigDecimal.valueOf(600.0));

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> flightService.update(flightId, request));
    }

    @Test
    public void testDeleteFlight_whenFlightExists_ShouldReturnSuccessMessage() {
        Long flightId = 1L;
        OffsetDateTime departureDate = OffsetDateTime.now();
        BigDecimal price = BigDecimal.valueOf(500.0);
        Flight existingFlight = new Flight(flightId, null, null, departureDate, null, price);

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.of(existingFlight));

        String result = flightService.deleteById(flightId);

        assertEquals(result, "Flight deleted by id: " + flightId);

        Mockito.verify(flightRepository, times(1)).delete(existingFlight);
    }

    @Test
    public void testDeleteFlight_whenFlightDoesNotExist_ShouldThrowFlightNotFoundException() {
        Long flightId = 1L;

        Mockito.when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> flightService.deleteById(flightId));
    }
}

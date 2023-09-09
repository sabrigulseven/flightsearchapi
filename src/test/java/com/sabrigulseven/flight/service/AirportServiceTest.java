package com.sabrigulseven.flight.service;

import com.sabrigulseven.flight.dto.AirportDto;
import com.sabrigulseven.flight.dto.converter.AirportDtoConverter;
import com.sabrigulseven.flight.dto.request.CreateAirportRequest;
import com.sabrigulseven.flight.dto.request.UpdateAirportRequest;
import com.sabrigulseven.flight.exception.AirportNotFoundException;
import com.sabrigulseven.flight.model.Airport;
import com.sabrigulseven.flight.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
public class AirportServiceTest {

    private AirportService airportService;
    private AirportRepository airportRepository;
    private AirportDtoConverter converter;

    @BeforeEach
    public void init() {
        airportRepository = mock(AirportRepository.class);
        converter = mock(AirportDtoConverter.class);
        airportService = new AirportService(airportRepository,converter);
    }

    @Test
    public void testFindByAirportId_whenAirportIdExists_ShouldReturnAirport() {
        Airport airport = new Airport(0L, "name");

        Mockito.when(airportRepository.findById(0L)).thenReturn(Optional.of(airport));

        Airport result = airportService.findById(0L);

        assertEquals(result, airport);
    }

    @Test
    public void testFindByAirportId_whenAirportIdDoesNotExists_ShouldThrowAirportNotFoundException() {

        Mockito.when(airportRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(AirportNotFoundException.class, () -> airportService.findById(0L));
    }

    @Test
    public void testGetAirportById_whenAirportIdExists_ShouldReturnAirport() {
        Airport airport = new Airport(0L, "name");
        AirportDto airportDto = new AirportDto(0L, "name");

        Mockito.when(airportRepository.findById(0L)).thenReturn(Optional.of(airport));
        Mockito.when(converter.convert(airport)).thenReturn(airportDto);
        AirportDto result = airportService.getById(0L);
        assertEquals(result, airportDto);
    }

    @Test
    public void testGetAirportById_whenAirportIdDoesNotExists_ShouldThrowAirportNotFoundException() {
        Mockito.when(airportRepository.findById(0L)).thenReturn(Optional.empty());

        assertThrows(AirportNotFoundException.class, () -> airportService.getById(0L));

        Mockito.verifyNoInteractions(converter);
    }
    @Test
    public void testSaveAirport_whenCreateAirportCalledWithValidRequest_itShouldReturnValidAccountDto() {
        CreateAirportRequest request = new CreateAirportRequest("City");
        Airport airport = new Airport(0L, "City");
        AirportDto airportDto = new AirportDto(0L,"City");

        Mockito.when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(airport);
        Mockito.when(converter.convert(airport)).thenReturn(airportDto);
        AirportDto result = airportService.save(request);

        assertEquals(airportDto,result);
    }

    @Test
    public void testUpdateAirport_whenAirportExists_ShouldReturnUpdatedAirport() {
        Long airportId = 1L;
        UpdateAirportRequest request = new UpdateAirportRequest();
        request.setCity("Los Angeles");

        Airport existingAirport = new Airport(airportId, "New York");
        Airport updatedAirport = new Airport(airportId, "Los Angeles");
        AirportDto airportDto = new AirportDto(airportId,"Los Angeles");

        Mockito.when(airportRepository.findById(airportId)).thenReturn(Optional.of(existingAirport));
        Mockito.when(airportRepository.save(Mockito.any(Airport.class))).thenReturn(updatedAirport);
        Mockito.when(converter.convert(updatedAirport)).thenReturn(airportDto);

        AirportDto result = airportService.update(airportId, request);

        assertEquals(result.getCity(), "Los Angeles");
    }

    @Test
    public void testUpdateAirport_whenAirportDoesNotExist_ShouldThrowAirportNotFoundException() {
        Long airportId = 1L;
        UpdateAirportRequest request = new UpdateAirportRequest();
        request.setCity("Los Angeles");

        Mockito.when(airportRepository.findById(airportId)).thenReturn(Optional.empty());

        assertThrows(AirportNotFoundException.class, () -> airportService.update(airportId, request));
    }

    @Test
    public void testDeleteAirport_whenAirportExists_ShouldReturnSuccessMessage() {
        Long airportId = 1L;

        Airport existingAirport = new Airport(airportId, "New York");

        Mockito.when(airportRepository.findById(airportId)).thenReturn(Optional.of(existingAirport));

        String result = airportService.deleteById(airportId);

        assertEquals(result, "Airport deleted with id: " + airportId);

        Mockito.verify(airportRepository, times(1)).delete(existingAirport);
    }

    @Test
    public void testDeleteAirport_whenAirportDoesNotExist_ShouldThrowAirportNotFoundException() {
        Long airportId = 1L;

        Mockito.when(airportRepository.findById(airportId)).thenReturn(Optional.empty());

        assertThrows(AirportNotFoundException.class, () -> airportService.deleteById(airportId));
    }
}

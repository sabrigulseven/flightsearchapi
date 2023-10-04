package com.sabrigulseven.flight.dto.converter;

import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.model.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightDtoConverter {
    private final FlightAirportDtoConverter flightAirportDtoConverter;

    public FlightDtoConverter(FlightAirportDtoConverter flightAirportDtoConverter) {
        this.flightAirportDtoConverter = flightAirportDtoConverter;
    }

    public FlightDto convert(Flight from) {
        return new FlightDto(
                from.getId(),
                flightAirportDtoConverter.convert(from.getOrigin()),
                flightAirportDtoConverter.convert(from.getDestination()),
                from.getDepartureDate(),
                from.getReturnDate(),
                from.getPrice()
        );
    }

    public Flight revert(FlightDto from) {
        return new Flight(
                from.getId(),
                flightAirportDtoConverter.revert(from.getOrigin()),
                flightAirportDtoConverter.revert(from.getDestination()),
                from.getDepartureDate(),
                from.getReturnDate(),
                from.getPrice()
        );

    }
}

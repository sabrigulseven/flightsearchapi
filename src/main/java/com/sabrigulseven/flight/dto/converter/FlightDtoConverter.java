package com.sabrigulseven.flight.dto.converter;

import com.sabrigulseven.flight.dto.FlightDto;
import com.sabrigulseven.flight.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightDtoConverter {
    private final FligtAirportDtoConverter fligtAirportDtoConverter;

    public FlightDto convert(Flight from) {
        return new FlightDto(
                from.getId(),
                fligtAirportDtoConverter.convert(from.getOrigin()),
                fligtAirportDtoConverter.convert(from.getDestination()),
                from.getDepartureDate(),
                from.getReturnDate(),
                from.getPrice());
    }
}

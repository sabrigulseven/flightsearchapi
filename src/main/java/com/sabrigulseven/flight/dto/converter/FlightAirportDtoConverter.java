package com.sabrigulseven.flight.dto.converter;

import com.sabrigulseven.flight.dto.FlightAirportDto;
import com.sabrigulseven.flight.model.Airport;
import org.springframework.stereotype.Component;

@Component
public class FlightAirportDtoConverter {
    public FlightAirportDto convert(Airport from) {
        return new FlightAirportDto(from.getId(),from.getCity());
    }

    public Airport revert(FlightAirportDto from) {
        return new Airport(from.getId(), from.getCity());
    }
}

package com.sabrigulseven.flight.dto.converter;

import com.sabrigulseven.flight.dto.AirportDto;
import com.sabrigulseven.flight.model.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportDtoConverter {
    public AirportDto convert(Airport from) {
        return new AirportDto(
                from.getId(),
                from.getCity()
        );
    }
}

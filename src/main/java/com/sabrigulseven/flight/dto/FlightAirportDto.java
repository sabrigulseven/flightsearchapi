package com.sabrigulseven.flight.dto;

public class FlightAirportDto {
    private Long id;
    private String city;

    public FlightAirportDto(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
}

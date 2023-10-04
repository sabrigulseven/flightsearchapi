package com.sabrigulseven.flight.dto.request;

import jakarta.validation.constraints.NotBlank;


public class CreateAirportRequest {
    @NotBlank
    private String city;

    public CreateAirportRequest(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}

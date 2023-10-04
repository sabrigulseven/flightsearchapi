package com.sabrigulseven.flight.dto.request;

import jakarta.validation.constraints.NotBlank;


public class UpdateAirportRequest {
    @NotBlank
    private String city;

    public UpdateAirportRequest(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
}

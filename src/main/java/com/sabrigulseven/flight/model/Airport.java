package com.sabrigulseven.flight.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;

    public Airport(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Airport(String city) {
        this.city = city;
    }

    public Airport() {

    }

    public Long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }


}

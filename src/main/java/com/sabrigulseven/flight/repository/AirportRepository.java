package com.sabrigulseven.flight.repository;

import com.sabrigulseven.flight.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByCity(String city);
}

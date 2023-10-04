package com.sabrigulseven.flight.repository;

import com.sabrigulseven.flight.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Airport findByCity(String city);
}

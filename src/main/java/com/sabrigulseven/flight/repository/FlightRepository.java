package com.sabrigulseven.flight.repository;

import com.sabrigulseven.flight.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}

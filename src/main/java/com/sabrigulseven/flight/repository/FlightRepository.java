package com.sabrigulseven.flight.repository;

import com.sabrigulseven.flight.model.Flight;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findAll(Specification<Flight> spec, Sort sort);
}

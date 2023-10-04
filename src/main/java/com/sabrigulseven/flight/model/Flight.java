package com.sabrigulseven.flight.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "flight", indexes = @Index(name = "flight_date_index", columnList = "departure_date DESC"))
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    @Column(name = "departure_date")
    private OffsetDateTime departureDate;

    private OffsetDateTime returnDate;

    private BigDecimal price;

    public Flight(Long id, Airport origin, Airport destination, OffsetDateTime departureDate, OffsetDateTime returnDate, BigDecimal price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
    }

    public Flight(Airport origin, Airport destination, OffsetDateTime departureDate, OffsetDateTime returnDate, BigDecimal price) {
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.price = price;
    }

    public Flight() {

    }

    public Long getId() {
        return id;
    }

    public Airport getOrigin() {
        return origin;
    }

    public Airport getDestination() {
        return destination;
    }

    public OffsetDateTime getDepartureDate() {
        return departureDate;
    }

    public OffsetDateTime getReturnDate() {
        return returnDate;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

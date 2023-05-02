package com.example.demo.airport;

import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airport, AirportID> {
}

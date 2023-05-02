package com.example.demo;

import com.example.demo.airport.Airport;
import com.example.demo.airport.AirportID;
import com.example.demo.airport.AirportRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class AirportRepositoryTests {
    @Autowired
    private AirportRepository repository;


    @Test
    public void testSaveNew() {
        Airport airport = new Airport();
        airport.setCountryCode("VN");
        airport.setCityCode("HAN");
        airport.setName("Noi Bai International Airport");

        Airport savedAirport = repository.save(airport);
        assertThat(savedAirport).isNotNull();
        assertThat(savedAirport.getCityCode()).isEqualTo("HAN");
        assertThat(savedAirport.getCountryCode()).isEqualTo("VN");
    }

    @Test
    public void testListAll() {
        Iterable<Airport> airports = repository.findAll();
        assertThat(airports).isNotNull();
        airports.forEach(s -> System.out.println(s));
    }

    @Test
    public void testFindById() {
        AirportID id = new AirportID();
        id.setCityCode("HAN");
        id.setCountryCode("VN");

        Optional<Airport> result = repository.findById(id);
        assertThat(result).isPresent();
    }

}

package com.flights.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class FlightApplication {

  public static void main(String[] args) {
    SpringApplication.run(FlightApplication.class, args);
  }
}

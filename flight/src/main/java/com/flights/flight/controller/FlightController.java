package com.flights.flight.controller;

import com.flights.flight.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controls all relative to flight information
 *
 * @author VSaldanya
 */
@RestController
@RequestMapping("/v1/flight-information")
public class FlightController {

  final static Logger logger = LoggerFactory.getLogger(FlightController.class);

  @Value("${valid.token}")
  private String validToken;

  @Autowired
  protected FlightService flightService;

  /**
   * Retrieve information about flight information
   *
   * @param flightNumber Flight number setted to the travel
   * @param tailNumber   The plane identifier
   * @return Json response with the flight info
   */
  @GetMapping("/{tailNumber}/{flightNumber}")
  public @ResponseBody
  ResponseEntity<?> getFlightInformation(@PathVariable String tailNumber,
                                         @PathVariable String flightNumber, @RequestHeader(name = "X-Auth") String token) {
    if (!token.equals(validToken)) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    } else {
      return ResponseEntity.ok(flightService.getFlightInformation(tailNumber, flightNumber));
    }
  }
}

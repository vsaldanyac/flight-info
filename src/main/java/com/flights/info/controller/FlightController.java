package com.flights.info.controller;

import com.flights.info.service.FlightService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controls all relative to flight information
 *
 * @author VSaldanya
 */
@RestController
@RequestMapping("/v1/flight-information-tail")
public class FlightController {

  final static Logger logger = LoggerFactory.getLogger(FlightController.class);

  @Autowired
  protected FlightService flightService;

  /**
   * Retrieve information about flight information
   *
   * @param flightNumber Flight number setted to the travel
   * @param tailNumber   The plane identifier
   * @return Json response with the flight info
   */
  @GetMapping(value = "/{tailNumber/{flightNumber}")
  public @ResponseBody
  ResponseEntity<?> getFlightInformation(@PathVariable String tailNumber,
      @PathVariable String flightNumber) {
    flightService.getFlightInformation(tailNumber, flightNumber);
    return ResponseEntity.ok().build();
  }
}

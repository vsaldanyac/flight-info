package com.flights.flight.service;

import com.flights.flight.dto.FlightInfoDTO;

/**
 * Give service to all the Flights functionality
 *
 * @author vsaldanya
 */
public interface FlightService {

  FlightInfoDTO getFlightInformation(String tailNumber, String flightNumber);
}

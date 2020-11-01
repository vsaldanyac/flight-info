package com.flights.flight.service;

import com.flights.info.dao.dao.FlightInfoDAO;

/**
 * Give service to all the Flights functionality
 *
 * @author vsaldanya
 */
public interface FlightService {

  FlightInfoDAO getFlightInformation(String tailNumber, String flightNumber);
}

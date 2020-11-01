package com.flights.flight.service.impl;

import com.flights.flight.service.FlightCacheService;
import com.flights.flight.service.FlightService;
import com.flights.info.dao.dao.FlightInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation related to the flight information service
 *
 * @author vsaldanya
 */
@Service
public class FlightServiceImpl implements FlightService {

  @Autowired
  FlightCacheService flightCacheService;

  @Override
  public FlightInfoDAO getFlightInformation(String tailNumber, String flightNumber) {
    flightCacheService.getFlightsInfo();
    FlightInfoDAO flightInfoDAO = new FlightInfoDAO();
    flightInfoDAO.setFaFlightId("IBB653-1581399936-airline-0136");
    return flightInfoDAO;
  }
}

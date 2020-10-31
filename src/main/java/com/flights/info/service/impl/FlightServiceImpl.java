package com.flights.info.service.impl;

import com.flights.info.service.FlightCacheService;
import com.flights.info.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation related to the flight information service
 * @author vsaldanya
 */
@Service
public class FlightServiceImpl implements FlightService {

  @Autowired
  protected FlightCacheService flightCacheService;

  @Override public void getFlightInformation(String tailNumber, String flightNumber) {

  }
}

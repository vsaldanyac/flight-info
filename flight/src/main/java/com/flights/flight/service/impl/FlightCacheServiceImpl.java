package com.flights.flight.service.impl;

import com.flights.flight.repository.FlightCache;
import com.flights.flight.service.FlightCacheService;
import com.flights.info.dao.dao.FlightInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightCacheServiceImpl implements FlightCacheService {


  @Autowired
  private FlightCache flightCache;

  @Override
  public FlightInfoDAO getFlightsInfo(String tailNumber, String flightNumber) {
    return flightCache.getFlightInfo(tailNumber, flightNumber);
  }

  @Override
  public void setFlightInfo(FlightInfoDAO flightInfo) {
    flightCache.setTailNumber(flightInfo);
  }
}

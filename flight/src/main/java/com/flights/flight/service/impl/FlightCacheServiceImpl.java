package com.flights.flight.service.impl;

import com.flights.flight.repository.FlightCache;
import com.flights.flight.service.FlightCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightCacheServiceImpl implements FlightCacheService {


  @Autowired
  private FlightCache flightCache;

  @Override public void getFlightsInfo() {

  }
}

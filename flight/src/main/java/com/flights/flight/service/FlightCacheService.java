package com.flights.flight.service;

import com.flights.info.dao.dao.FlightInfoDAO;

public interface FlightCacheService {

  FlightInfoDAO getFlightsInfo(String tailNumber, String flightNumber);

  void setFlightInfo(FlightInfoDAO flightInfo);
}

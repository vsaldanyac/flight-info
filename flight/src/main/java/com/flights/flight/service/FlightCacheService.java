package com.flights.flight.service;

import com.flights.info.dao.dao.FlightInfoDAO;

import java.util.List;

public interface FlightCacheService {

  FlightInfoDAO getFlightsInfo(String tailNumber, String flightNumber);

  void setFlightInfo(FlightInfoDAO flightInfo);

  List<FlightInfoDAO> getTailNumberInfo(String tailNumber);
}

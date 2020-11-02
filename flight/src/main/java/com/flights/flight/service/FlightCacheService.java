package com.flights.flight.service;

import com.flights.info.dao.dao.AirportDAO;
import com.flights.info.dao.dao.FlightInfoDAO;

import java.util.List;

public interface FlightCacheService {

  FlightInfoDAO getFlightsInfo(String tailNumber, String flightNumber);

  AirportDAO getAirtportInfo(String code);

  void setFlightInfo(FlightInfoDAO flightInfo);

  List<FlightInfoDAO> getTailNumberInfo(String tailNumber);
}

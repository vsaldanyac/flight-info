package com.flights.info.dao.dao;

import lombok.Data;

@Data
public class FlightInfoDAO {

  String ident;
  String faFlightId;
  String airline;
  String airlineIata;
  String flightNumber;
  String tailNumber;
  String type;
  boolean blocked;
  boolean diverted;
  boolean cancelled;
  AirportDAO origin;
  AirportDAO detination;
}

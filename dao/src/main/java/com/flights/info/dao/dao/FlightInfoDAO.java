package com.flights.info.dao.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FlightInfoDAO {

  String ident;
  String faFlightId;
  String airline;
  String airlineIata;
  String flightNumber;
  String tailNumber;
  String codeShares;
  String type;
  boolean blocked;
  boolean diverted;
  boolean cancelled;
  AirportDAO origin;
  AirportDAO destination;
  String originCode;
  String destinationCode;
}

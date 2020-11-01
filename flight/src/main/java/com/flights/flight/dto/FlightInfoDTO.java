package com.flights.flight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.flights.info.dao.dao.AirportDAO;
import lombok.Data;

@Data
public class FlightInfoDTO {

  String ident;
  @JsonProperty("faFlightID")
  String faFlightId;
  String airline;
  @JsonProperty("airline_iata")
  String airlineIata;
  @JsonProperty("flightnumber")
  String flightNumber;
  @JsonProperty("codeshares")
  String codeShares;
  @JsonProperty("tailnumber")
  String tailNumber;
  String type;
  boolean blocked;
  boolean diverted;
  boolean cancelled;
  AirportDAO origin;
  AirportDAO detination;
}

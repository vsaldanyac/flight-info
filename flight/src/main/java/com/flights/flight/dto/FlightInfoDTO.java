package com.flights.flight.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
  AirportDTO origin;
  AirportDTO destination;
}

package com.flights.flight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AirportDTO {
  String code;
  String city;
  @JsonProperty("alternate_ident")
  String alternateIdent;
  @JsonProperty("airport_name")
  String airportName;
}

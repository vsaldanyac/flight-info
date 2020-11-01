package com.flights.info.dao.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDAO {
  String code;
  String city;
  String alternateIdent;
  String airportName;
}

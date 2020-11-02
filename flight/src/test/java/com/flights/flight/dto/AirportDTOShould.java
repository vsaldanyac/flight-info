package com.flights.flight.dto;

import com.flights.flight.dto.mapper.FlightInfoMapper;
import com.flights.info.dao.dao.AirportDAO;
import com.flights.info.dao.dao.FlightInfoDAO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class AirportDTOShould {
  FlightInfoMapper flightInfoMapper = FlightInfoMapper.INSTANCE;


  AirportDAO airportDAO = AirportDAO.builder().airportName("El Prat").alternateIdent("BCN").city("Barcelona").code("LEBL").build();
  FlightInfoDAO flightInfoDAO = FlightInfoDAO.builder().origin(airportDAO).flightNumber("A").build();

  @Test
  public void shouldReturnValidDTOAirport() {
    AirportDTO airport = flightInfoMapper.map(airportDAO);
    Assert.assertNotNull(airport);
  }

  @Test
  public void shouldReturnCityDTOAirport() {
    AirportDTO airport = flightInfoMapper.map(airportDAO);
    Assert.assertEquals(airport.airportName, "El Prat");
  }

  @Test
  public void shouldReturnAirportInfo() {
    FlightInfoDTO flightInfoDTO = flightInfoMapper.map(flightInfoDAO);
    Assert.assertEquals(flightInfoDTO.getOrigin().getAirportName(), "El Prat");
  }

  @Test
  public void shouldMapperBeSimetric() {
    FlightInfoDAO flightInfo = flightInfoMapper.map(flightInfoMapper.map(flightInfoDAO));
    Assert.assertEquals(flightInfoDAO, flightInfo);
  }
}

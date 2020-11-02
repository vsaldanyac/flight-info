package com.flights.flight.service.impl;

import com.flights.flight.dto.FlightInfoDTO;
import com.flights.flight.dto.mapper.FlightInfoMapper;
import com.flights.flight.service.FlightCacheService;
import com.flights.flight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation related to the flight information service
 *
 * @author vsaldanya
 */
@Service
public class FlightServiceImpl implements FlightService {

  @Value("${flight.info.url}")
  private String uri;

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  FlightCacheService flightCacheService;

  FlightInfoMapper flightInfoMapper = FlightInfoMapper.INSTANCE;

  @Override
  public FlightInfoDTO getFlightInformation(String tailNumber, String flightNumber) {
    FlightInfoDTO[] tailNumberInfoDTO = getExternalFlightInfo(tailNumber);
    for (FlightInfoDTO flightInfoDTO : tailNumberInfoDTO) {
      flightCacheService.setFlightInfo(flightInfoMapper.map(flightInfoDTO));
    }

    return flightInfoMapper.map(flightCacheService.getFlightsInfo(tailNumber, flightNumber));
  }

  private FlightInfoDTO[] getExternalFlightInfo(String tailNumber) {
    return restTemplate.getForObject(uri + tailNumber, FlightInfoDTO[].class);
  }
}

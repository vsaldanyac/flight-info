package com.flights.flight.service.impl;

import com.flights.flight.dto.FlightInfoDTO;
import com.flights.flight.dto.mapper.FlightInfoMapper;
import com.flights.flight.service.FlightCacheService;
import com.flights.flight.service.FlightService;
import com.flights.info.dao.dao.FlightInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

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

    List<FlightInfoDAO> flightsInfoList = flightCacheService.getTailNumberInfo(tailNumber).stream().filter(x -> x.getFlightNumber().equals(flightNumber)).collect(Collectors.toList());

    return flightsInfoList.size() > 0 ? flightInfoMapper.map(flightsInfoList.get(0)) : null;
  }

  private FlightInfoDTO[] getExternalFlightInfo(String tailNumber) {
    return restTemplate.getForObject(uri + tailNumber, FlightInfoDTO[].class);
  }
}

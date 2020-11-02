package com.flights.flight.dto.mapper;

import com.flights.flight.dto.FlightInfoDTO;
import com.flights.flight.service.FlightCacheService;
import com.flights.info.dao.dao.FlightInfoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class FlightInfoMapperDecorator implements FlightInfoMapper {

  @Autowired
  @Qualifier("delegate")
  private FlightInfoMapper delegate;

  @Autowired
  FlightCacheService flightCacheService;

  @Override
  public FlightInfoDTO map(FlightInfoDAO flightInfoDAO) {
    FlightInfoDTO flightInfoDTO = delegate.map(flightInfoDAO);

    if (flightInfoDAO.getOriginCode() != null) {
      flightInfoDTO.setOrigin(delegate.map(flightCacheService.getAirtportInfo(flightInfoDAO.getOriginCode())));
    }
    if (flightInfoDAO.getDestinationCode() != null) {
      flightInfoDTO.setDestination(delegate.map(flightCacheService.getAirtportInfo(flightInfoDAO.getDestinationCode())));
    }
    return flightInfoDTO;
  }
}

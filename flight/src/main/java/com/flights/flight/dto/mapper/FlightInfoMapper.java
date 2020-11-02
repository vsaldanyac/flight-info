package com.flights.flight.dto.mapper;

import com.flights.flight.dto.AirportDTO;
import com.flights.flight.dto.FlightInfoDTO;
import com.flights.info.dao.dao.AirportDAO;
import com.flights.info.dao.dao.FlightInfoDAO;
import org.mapstruct.DecoratedWith;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
@DecoratedWith(FlightInfoMapperDecorator.class)
public interface FlightInfoMapper {

  FlightInfoDAO map(FlightInfoDTO flightInfoDTO);

  @InheritInverseConfiguration
  FlightInfoDTO map(FlightInfoDAO flightInfoDAO);

  AirportDAO map(AirportDTO airportDTO);

  @InheritInverseConfiguration
  AirportDTO map(AirportDAO airportDAO);
}

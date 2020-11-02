package com.flights.flight.dto.mapper;

import com.flights.flight.dto.AirportDTO;
import com.flights.flight.dto.FlightInfoDTO;
import com.flights.info.dao.dao.AirportDAO;
import com.flights.info.dao.dao.FlightInfoDAO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FlightInfoMapper {
  FlightInfoMapper INSTANCE = Mappers.getMapper(FlightInfoMapper.class);

  FlightInfoDAO map(FlightInfoDTO flightInfoDTO);

  @InheritInverseConfiguration
  FlightInfoDTO map(FlightInfoDAO flightInfoDAO);

  AirportDAO map(AirportDTO airportDTO);

  @InheritInverseConfiguration
  AirportDTO map(AirportDAO airportDAO);
}

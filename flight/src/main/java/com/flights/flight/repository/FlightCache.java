package com.flights.flight.repository;

import com.flights.flight.core.BaseCache;
import com.flights.flight.core.RedisCache;
import com.flights.info.dao.dao.AirportDAO;
import com.flights.info.dao.dao.FlightInfoDAO;

import java.util.HashMap;
import java.util.Map;

import static com.flights.flight.repository.utils.KeysForRedis.*;

public class FlightCache extends BaseCache<RedisCache> {


  public FlightCache(String host, int port, String userName, String password, int poolMinIdle,
                     int poolMaxIdle, int poolMaxTotal, long timeout, RedisCache cache) {
    super(host, port, userName, password, poolMinIdle, poolMaxIdle, poolMaxTotal, timeout, cache);
  }

  public FlightCache(String host, int port, int poolMinIdle, int poolMaxIdle, int poolMaxTotal) {
    super(host, port, poolMinIdle, poolMaxIdle, poolMaxTotal, new RedisCache());
  }

  private Map<String, String> setMapForRedis(FlightInfoDAO flightInfoDAO) {
    Map<String, String> hash = new HashMap<>();
    hash.put(IDENT, flightInfoDAO.getIdent());
    hash.put(FA_FLIGHT_ID, flightInfoDAO.getFaFlightId());
    hash.put(AIRLINE, flightInfoDAO.getAirline());
    hash.put(AIRLNE_IATA, flightInfoDAO.getAirlineIata());
    hash.put(FLIGHT_NUMBER, flightInfoDAO.getFlightNumber());
    hash.put(TAIL_NUMBER, flightInfoDAO.getTailNumber());
    hash.put(CODE_SHARES, flightInfoDAO.getCodeShares());
    hash.put(TYPE, flightInfoDAO.getType());
    hash.put(BLOCKED, flightInfoDAO.isBlocked() ? "1" : "0");
    hash.put(DIVERTED, flightInfoDAO.isDiverted() ? "1" : "0");
    hash.put(CANCELLED, flightInfoDAO.isCancelled() ? "1" : "0");
    hash.put(ORIGIN, flightInfoDAO.getOrigin().getCode());
    hash.put(DESTINATION, flightInfoDAO.getDestination().getCode());
    return hash;
  }

  public void setTailNumber(FlightInfoDAO flightInfoDAO) {
    cache.hmSet(TAIL_KEY + flightInfoDAO.getTailNumber() + FLIGHT_NUMBER_KEY + flightInfoDAO.getFlightNumber(), setMapForRedis(flightInfoDAO));
  }

  public FlightInfoDAO getFlightInfo(String tailNumber, String flightNumber) {
    FlightInfoDAO flightInfoFromRedis = getFlightInfoFromRedis(tailNumber, flightNumber);
    flightInfoFromRedis.setOrigin(getAirportInfoFromRedis(flightInfoFromRedis.getOriginCode()));
    flightInfoFromRedis.setDestination(getAirportInfoFromRedis(flightInfoFromRedis.getDestinationCode()));
    return flightInfoFromRedis;
  }

  private AirportDAO getAirportInfoFromRedis(String code) {
    Map<String, String> airportInfo = cache.hGetAll(AIRPORT_KEY + code);
    return AirportDAO.builder()
        .airportName(airportInfo.get(AIRPORT_NAME))
        .alternateIdent(airportInfo.get(ALTERNATE_IDENT))
        .city(airportInfo.get(CITY))
        .code(airportInfo.get(CODE))
        .build();
  }

  private FlightInfoDAO getFlightInfoFromRedis(String tailNumber, String flightNumber) {
    Map<String, String> flightInfo = cache.hGetAll(TAIL_KEY + tailNumber + FLIGHT_NUMBER_KEY + flightNumber);
    return FlightInfoDAO.builder()
        .ident(flightInfo.get(IDENT))
        .faFlightId(flightInfo.get(FA_FLIGHT_ID))
        .airline(flightInfo.get(AIRLINE))
        .airlineIata(flightInfo.get(AIRLNE_IATA))
        .flightNumber(flightInfo.get(FLIGHT_NUMBER))
        .tailNumber(flightInfo.get(TAIL_NUMBER))
        .codeShares(flightInfo.get(CODE_SHARES))
        .type(flightInfo.get(TYPE))
        .blocked(Boolean.parseBoolean(flightInfo.get(BLOCKED)))
        .diverted(Boolean.parseBoolean(flightInfo.get(DIVERTED)))
        .cancelled(Boolean.parseBoolean(flightInfo.get(CANCELLED)))
        .originCode(flightInfo.get(ORIGIN))
        .destinationCode(flightInfo.get(DESTINATION)).build();
  }
}

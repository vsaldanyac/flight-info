package com.flights.flight.repository;

import com.flights.flight.core.BaseCache;
import com.flights.flight.core.RedisCache;
import com.flights.info.dao.dao.AirportDAO;
import com.flights.info.dao.dao.FlightInfoDAO;

import java.util.*;

import static com.flights.flight.repository.utils.KeysForRedis.*;

public class FlightCache extends BaseCache<RedisCache> {


  public FlightCache(String host, int port, String userName, String password, int poolMinIdle,
                     int poolMaxIdle, int poolMaxTotal, long timeout, RedisCache cache) {
    super(host, port, userName, password, poolMinIdle, poolMaxIdle, poolMaxTotal, timeout, cache);
  }

  public FlightCache(String host, int port, int poolMinIdle, int poolMaxIdle, int poolMaxTotal) {
    super(host, port, poolMinIdle, poolMaxIdle, poolMaxTotal, new RedisCache());
  }

  private Map<String, String> setFlightMapForRedis(FlightInfoDAO flightInfoDAO) {
    Map<String, String> hash = new HashMap<>();
    hash.put(IDENT, flightInfoDAO.getIdent());
    hash.put(FA_FLIGHT_ID, flightInfoDAO.getFaFlightId());
    hash.put(AIRLINE, flightInfoDAO.getAirline());
    hash.put(AIRLINE_IATA, flightInfoDAO.getAirlineIata());
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

  private Map<String, String> setAirportMapForRedis(AirportDAO airport) {
    Map<String, String> hash = new HashMap<>();
    hash.put(CODE, airport.getCode());
    hash.put(CITY, airport.getCity());
    hash.put(AIRPORT_NAME, airport.getAirportName());
    hash.put(ALTERNATE_IDENT, airport.getAlternateIdent());
    return hash;
  }

  public void setTailNumber(FlightInfoDAO flightInfoDAO) {
    cache.hmSet(TAIL_KEY + flightInfoDAO.getTailNumber() + FLIGHT_NUMBER_KEY + flightInfoDAO.getFlightNumber(), setFlightMapForRedis(flightInfoDAO));
    setAirportInfo(flightInfoDAO.getOrigin());
    setAirportInfo(flightInfoDAO.getDestination());
  }

  private void setAirportInfo(AirportDAO airportInfo) {
    cache.hmSet(AIRPORT_KEY + airportInfo.getCode(), setAirportMapForRedis(airportInfo));
  }

  public FlightInfoDAO getFlightInfo(String tailNumber, String flightNumber) {
    FlightInfoDAO flightInfoFromRedis = getFlightInfoFromRedis(tailNumber, flightNumber);
    flightInfoFromRedis.setOrigin(getAirportInfoFromRedis(flightInfoFromRedis.getOriginCode()));
    flightInfoFromRedis.setDestination(getAirportInfoFromRedis(flightInfoFromRedis.getDestinationCode()));
    return flightInfoFromRedis;
  }

  public List<FlightInfoDAO> getTailNumberInfo(String tailNumber) {
    List<FlightInfoDAO> flightsInfo = new ArrayList();

    Set<String> keysForTailNumber = getKeysForTailNumber(tailNumber);
    Iterator<String> it = keysForTailNumber.iterator();
    while (it.hasNext()) {
      flightsInfo.add(getFlightInfoFromRedis(it.next()));
    }

    return flightsInfo;
  }

  private Set<String> getKeysForTailNumber(String tailNumber) {
    return cache.keys(TAIL_KEY + tailNumber + "*");
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
    return getFlightInfoFromRedis(TAIL_KEY + tailNumber + FLIGHT_NUMBER_KEY + flightNumber);
  }

  private FlightInfoDAO getFlightInfoFromRedis(String key) {
    Map<String, String> flightInfo = cache.hGetAll(key);
    return FlightInfoDAO.builder()
        .ident(flightInfo.get(IDENT))
        .faFlightId(flightInfo.get(FA_FLIGHT_ID))
        .airline(flightInfo.get(AIRLINE))
        .airlineIata(flightInfo.get(AIRLINE_IATA))
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

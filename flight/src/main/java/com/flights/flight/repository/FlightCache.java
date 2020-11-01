package com.flights.flight.repository;

import com.flights.flight.core.BaseCache;
import com.flights.flight.core.RedisCache;
import com.flights.info.dao.dao.FlightInfoDAO;

public class FlightCache extends BaseCache<RedisCache> {

  public static final String PREFIX_TAIL_KEY = "TAIL_NUMBER_";

  public FlightCache(String host, int port, String userName, String password, int poolMinIdle,
      int poolMaxIdle, int poolMaxTotal, long timeout, RedisCache cache) {
    super(host, port, userName, password, poolMinIdle, poolMaxIdle, poolMaxTotal, timeout, cache);
  }

  public FlightCache(String host, int port, int poolMinIdle, int poolMaxIdle, int poolMaxTotal) {
    super(host, port, poolMinIdle, poolMaxIdle, poolMaxTotal, new RedisCache());
  }

  public void setTailNumber(String tailNumber, FlightInfoDAO flightInfoDAO) {
    cache.zAdd(PREFIX_TAIL_KEY + tailNumber, 0, flightInfoDAO.getAirline());
  }
}

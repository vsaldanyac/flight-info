package com.flights.flight.repository;

import com.flights.flight.core.BaseCache;
import com.flights.flight.core.RedisCache;

public class FlightCache extends BaseCache<RedisCache> {
  public FlightCache(String host, int port, String userName, String password, int poolMinIdle,
      int poolMaxIdle, int poolMaxTotal, long timeout, RedisCache cache) {
    super(host, port, userName, password, poolMinIdle, poolMaxIdle, poolMaxTotal, timeout, cache);
  }
}

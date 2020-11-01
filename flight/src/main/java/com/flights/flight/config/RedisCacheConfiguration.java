package com.flights.flight.config;

import com.flights.flight.repository.FlightCache;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisCacheConfiguration {

  @Value("${rediscache.host}")
  private String redisHost;

  @Value("${rediscache.port}")
  private Integer redisPort;

  private static final int POOL_MIN_IDLE = 4;

  private static final int POOL_MAX_IDLE = 150;

  private static final int POOL_MAX_TOTAL = 200;

  @Bean
  public FlightCache flightCache() {
    return new FlightCache(redisHost, redisPort, POOL_MIN_IDLE, POOL_MAX_IDLE, POOL_MAX_TOTAL);
  }

}

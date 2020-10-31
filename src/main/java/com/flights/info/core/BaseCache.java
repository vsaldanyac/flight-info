package com.flights.info.core;

public class BaseCache<T extends Cache> {

  protected T cache;

  public BaseCache(String host, int port, String userName, String password, int poolMinIdle,
      int poolMaxIdle, int poolMaxTotal, long timeout, T cache) {
    this.cache = cache;
    this.cache.setHost(host);
    this.cache.setPort(port);
    this.cache.setUserName(userName);
    this.cache.setPassword(password);
    this.cache.setTimeout(timeout);
    this.cache.setPoolMinIdle(poolMinIdle);
    this.cache.setPoolMaxIdle(poolMaxIdle);
    this.cache.setPoolMaxTotal(poolMaxTotal);
  }

  public BaseCache(String host, int port, int poolMinIdle, int poolMaxIdle, int poolMaxTotal,
      T cache) {
    this.cache = cache;
    this.cache.setHost(host);
    this.cache.setPort(port);
    this.cache.setTimeout(0); //no timeout
    this.cache.setPoolMinIdle(poolMinIdle);
    this.cache.setPoolMaxIdle(poolMaxIdle);
    this.cache.setPoolMaxTotal(poolMaxTotal);
  }
}


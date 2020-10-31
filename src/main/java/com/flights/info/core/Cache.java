package com.flights.info.core;

import com.flights.info.exception.CacheException;
import java.util.Map;
import java.util.Set;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

public abstract class Cache {
  protected String host;
  protected int port;
  protected int poolMinIdle;
  protected int poolMaxIdle;
  protected int poolMaxTotal;
  protected String userName;
  protected String password;
  protected long timeout;

  public abstract String hmSet(String key, Map<String, String> hash);

  public abstract Map<String, String> hGetAll(String key);

  /**
   * Adds a new memebr (or identified set) with a given key
   *
   * @param key    id Set.
   * @param weight Weight of the set elements
   * @param member Member to add
   * @return #inserted members
   * @throws CacheException Explicit cache exception
   */
  public abstract Long zAdd(String key, double weight, String member) throws CacheException;

  public abstract Long zAdd(String key, Map<String, Double> scoreMembers) throws CacheException;

  public abstract Set<Tuple> zRangeWithScores(String key, long start, long end)
      throws CacheException;

  /**
   * Returns a set of members with the given params
   *
   * @param key        Id Set.
   * @param weight Weight of the set elements
   * @param startIndex Index init
   * @param count      Numbers of rows to be returned
   * @return Set of members that had the given parameters
   * @throws CacheException Explicit cache exception
   */
  public abstract Set<String> zGet(String key, int weight, int startIndex, int count)
      throws CacheException;

  /**
   * Returns all the members of a given set
   *
   * @param key id Set
   * @return members of a set.
   * @throws CacheException Explicit cache exception
   */
  public abstract Set<String> zGetAll(String key) throws CacheException;

  /**
   * Removes a set of members of a Set identified with the indicated key.
   *
   * @param key     Id Set
   * @param members Set of members to remove.
   * @return Number of members removed.
   * @throws CacheException Explicit cache exception
   */
  public abstract Long zRemove(String key, String[] members) throws CacheException;

  public abstract Long zRemove(String key, String member) throws CacheException;

  public abstract ScanResult<Tuple> zScan(String key, String pattern) throws CacheException;

  public abstract ScanResult<Tuple> zScan(String key, String cursor, String pattern)
      throws CacheException;

  /**
   * Returns the size of the set of members identified with the indicated key.
   *
   * @param key    Id Set.
   * @param weight Weight of the set elements
   * @return member pool size.
   * @throws CacheException Explicit cache exception
   */
  public abstract Long zCount(String key, int weight) throws CacheException;

  /**
   * Ping the cache server.
   *
   * @return Ping return.
   * @throws CacheException Explicit cache exception
   */
  public abstract String ping() throws CacheException;

  /**
   * Assign the cache host
   *
   * @param host host to be assigned
   */
  public void setHost(String host) {
    this.host = host;
  }

  /**
   * Assign the cache port
   *
   * @param port port to be assigned
   */
  public void setPort(int port) {
    this.port = port;
  }

  /**
   * Assign the username of the cache
   *
   * @param userName username to be assigned
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Assign the cache password
   *
   * @param password password to be assigned
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Assign the cache timeout
   *
   * @param timeout timeout value
   */
  public void setTimeout(long timeout) {
    this.timeout = timeout;
  }

  /**
   * Assign the minimum idle connections
   *
   * @param poolMinIdle Minimum pool idle
   */
  public void setPoolMinIdle(int poolMinIdle) {
    this.poolMinIdle = poolMinIdle;
  }

  /**
   * Assign the maximum idle connections
   *
   * @param poolMaxIdle Maximum pool idle
   */
  public void setPoolMaxIdle(int poolMaxIdle) {
    this.poolMaxIdle = poolMaxIdle;
  }

  /**
   * Assign the maximum total connections
   *
   * @param poolMaxTotal Total maximum pool
   */
  public void setPoolMaxTotal(int poolMaxTotal) {
    this.poolMaxTotal = poolMaxTotal;
  }
}

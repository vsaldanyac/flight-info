package com.flights.flight.core;

import com.flights.flight.exception.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.emptyList;
import static redis.clients.jedis.ScanParams.SCAN_POINTER_START;

public class RedisCache extends Cache {
  private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);

  private JedisPool pool;

  @Override
  public String hmSet(String key, Map<String, String> hash) {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.hmset(key, hash);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation hmset Key: " + key, e);
      }
    }
    return "NOK";
  }

  public Long hDel(String key, String field) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.hdel(key, field);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation hDel Key: " + key, e);
      }
    }
    return null;
  }

  public Set<Tuple> zRangeWithScores(String key, long start, long end) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zrangeWithScores(key, start, end);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zRangeWithScores Key: " + key, e);
      }
    }
    return null;
  }

  public Set<Tuple> zRevRangeWithScores(String key, long start, long end) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zrevrangeWithScores(key, start, end);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zRangeWithScores Key: " + key, e);
      }
    }
    return null;
  }

  public Set<String> zRevRange(String key, long start, long end) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zrevrange(key, start, end);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zRange Key: " + key, e);
      }
    }
    return null;
  }

  @Override
  public Map<String, String> hGetAll(String key) {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.hgetAll(key);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation hgetall Key: " + key, e);
      }
    }
    return null;
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public Long zAdd(String key, double score, String member) throws CacheException {

    try (Jedis jedis = getPool().getResource()) {
      return jedis.zadd(key, score, member);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zadd Key: " + key + ", score:" + score + ", member: " + member, e);
      }
    }
    return -1L;
  }

  @Override
  public Long zAdd(String key, Map<String, Double> scoreMembers) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zadd(key, scoreMembers);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zadd Key: " + key + ", scoreMembers size:" + scoreMembers.size(), e);
      }
    }
    return -1L;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<String> zGet(String key, int score, int startIndex, int count) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zrangeByScore(key, score, score, startIndex, count);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zrangeByScore Key: " + key, e);
      }
    }
    return null;
  }

  @Override
  public ScanResult<Tuple> zScan(String key, String cursor, String pattern) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zscan(key, cursor, new ScanParams().match(pattern));
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zscan", e);
      }
    }
    return null;
  }

  @Override
  public ScanResult<Tuple> zScan(String key, String pattern) throws CacheException {
    return zScan(key, SCAN_POINTER_START, pattern);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long zRemove(String key, String[] members) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zrem(key, members);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zrem Key: " + key + ", members: " + Arrays.toString(members), e);
      }
    }
    return -1L;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long zRemove(String key, String member) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zrem(key, member);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zrem Key: " + key + ", member: " + member, e);
      }
    }
    return -1L;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Long zCount(String key, int score) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zcount(key, score, score);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zcount Key: " + key, e);
      }
    }
    return -1L;
  }

  /**
   * Returns the Pool of Connections to Redis
   *
   * @return Pool of Connections to Redis.
   */
  private JedisPool getPool() {
    if (pool == null) {
      JedisPoolConfig config = new JedisPoolConfig();
      config.setMinIdle(poolMinIdle);
      config.setMaxIdle(poolMaxIdle);
      config.setMaxTotal(poolMaxTotal);
      pool = new JedisPool(config, host, port, (int) timeout);
      logger.debug("Creando jedisPool@ " + host + ":" + port + ". MinIdle/MaxIdle/MaxTotal: " + poolMinIdle + "/" + poolMaxIdle + "/" + poolMaxTotal + ". Timeout: " + timeout);
    }
    return pool;
  }


  public Set<String> keys(String pattern) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.keys(pattern);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation keys Pattern: " + pattern, e);
      }
    }
    return null;
  }

  public Long del(String[] keys) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.del(keys);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation del Keys: " + Arrays.toString(keys), e);
      }
    }
    return -1L;
  }

  public Object eval(String script) throws CacheException {
    return eval(script, emptyList(), emptyList());
  }

  public Object eval(String script, List<String> keys, List<String> args) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.eval(script, keys, args);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation eval. Script: " + script, e);
      }
    }
    return null;
  }

  public Set<String> zGetAll(String key) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.zrange(key, 0, -1);
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zrange. Key: " + key, e);
      }
    }
    return null;
  }

  public Long zRevRank(String key, String member) throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      Long zrevrank = jedis.zrevrank(key, member);
      return zrevrank == null ? -1L : zrevrank;
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation zRevRank. Key: " + key + " Member: " + member, e);
      }
    }
    return -1L;
  }

  public String ping() throws CacheException {
    try (Jedis jedis = getPool().getResource()) {
      return jedis.ping();
    } catch (Exception e) {
      if (logger.isErrorEnabled()) {
        logger.error("Error on operation ping.");
      }
    }
    return null;
  }
}

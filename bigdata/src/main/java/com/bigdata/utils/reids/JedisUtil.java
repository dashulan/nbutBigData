package com.bigdata.utils.reids;

import redis.clients.jedis.Jedis;

public class JedisUtil {
    private Jedis jedis = null;

    public JedisUtil() {
    }

    public Jedis getJedis() {
        if (this.jedis == null) {
            this.jedis = new Jedis("10.26.231.198");
            return this.jedis;
        } else {
            return this.jedis;
        }
    }

}

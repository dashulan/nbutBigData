package com.bigdata.utils.reids;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public JedisTest() {
    }

    public static void main(String[] args) {
        Jedis jedis = new Jedis("10.26.231.198", 6379);
        System.out.println(jedis.get("浙江省"));
        System.out.println(jedis.hkeys("urls"));
    }
}

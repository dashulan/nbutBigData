package com.bigdata.utils;

import com.bigdata.storm.pojo.Entity;
import com.bigdata.storm.pojo.UrlEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ObjectToJsonRedis {
    private static ObjectMapper mapper = new ObjectMapper();

    public ObjectToJsonRedis() {
    }

    public static void FirstIn(Jedis jedis, Map<String, UrlEntity> urlEntityMap, String AKey, String urlName, Entity entity) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setName(urlName);
        List<Entity> entities = new ArrayList();
        entities.add(entity);
        urlEntity.setEntities(entities);
        urlEntityMap.put(urlName, urlEntity);

        String urlJson;
        try {
            urlJson = mapper.writeValueAsString(urlEntity);
        } catch (Exception var9) {
            var9.printStackTrace();
            return;
        }

        jedis.hset(AKey, urlName, urlJson);
    }

    public static void nestIn(Jedis jedis, Map<String, UrlEntity> urlEntityMap, String Akey, String urlName, Entity entity) {
        ((UrlEntity)urlEntityMap.get(urlName)).getEntities().add(entity);

        String urlJson;
        try {
            urlJson = mapper.writeValueAsString(urlEntityMap.get(urlName));
        } catch (Exception var7) {
            var7.printStackTrace();
            return;
        }

        jedis.hset(Akey, urlName, urlJson);
    }
}

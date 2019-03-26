package com.bigdata.storm;


import com.bigdata.storm.pojo.Entity;
import com.bigdata.storm.pojo.UrlEntity;
import com.bigdata.utils.ObjectToJsonRedis;
import com.bigdata.utils.TimeCategoryUtil;
import com.bigdata.utils.UrlUtils;
import com.bigdata.utils.reids.JedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class UrlStatisticBolt extends BaseBasicBolt {
    private static final Logger logger = LoggerFactory.getLogger(UrlStatisticBolt.class);
    private TimeCategoryUtil timeUtil;
    private JedisUtil jedisUtil;
    private Jedis jedis;
    private Map<String, UrlEntity> urls;
    private UrlUtils urlUtils;
    ObjectMapper mapper;

    public UrlStatisticBolt() {
    }

    public void prepare(Map stormConf, TopologyContext context) {
        this.timeUtil = new TimeCategoryUtil();
        this.jedisUtil = new JedisUtil();
        this.jedis = this.jedisUtil.getJedis();
        this.urls = new HashMap();
        this.mapper = new ObjectMapper();
        this.urlUtils = new UrlUtils();
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String time = input.getString(0);
        String codeAndUrl = input.getString(1);
        String returnType = input.getString(2);
        String[] codeurl = null;
        String url = "error";
        String code = "error";
        long timeLong = 0L;
        timeLong = this.timeUtil.translateTimeToLong(time);

        try {
            try {
                codeurl = codeAndUrl.split(" ", 2);
                code = codeurl[0];
                url = codeurl[1].split(" ")[0];
            } catch (ArrayIndexOutOfBoundsException var13) {
                logger.error(var13.toString());
            }

            String urlOrign = this.urlUtils.getOrignUrl(url);
            Entity entity = new Entity(code, timeLong, returnType);
            if (!this.urls.containsKey(urlOrign)) {
                ObjectToJsonRedis.FirstIn(this.jedis, this.urls, "urls", urlOrign, entity);
            } else {
                ObjectToJsonRedis.nestIn(this.jedis, this.urls, "urls", urlOrign, entity);
            }
        } catch (Exception var14) {
            var14.printStackTrace();
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}

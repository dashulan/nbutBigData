package com.bigdata.storm;

import java.util.HashMap;
import java.util.Map;

import com.bigdata.storm.pojo.UrlEntity;
import com.bigdata.utils.TimeCategoryUtil;
import com.bigdata.utils.reids.JedisUtil;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class CodeStatisticBolt extends BaseBasicBolt {
    private static final Logger logger = LoggerFactory.getLogger(CodeStatisticBolt.class);
    private JedisUtil jedisUtil;
    private TimeCategoryUtil timeUtil;
    private Jedis jedis;
    private Map<String, UrlEntity> entitys;

    public CodeStatisticBolt() {
    }

    public void prepare(Map stormConf, TopologyContext context) {
        this.timeUtil = new TimeCategoryUtil();
        this.jedisUtil = new JedisUtil();
        this.jedis = this.jedisUtil.getJedis();
        this.entitys = new HashMap();
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String code = input.getString(0);
        String time = input.getString(1);
        this.timeUtil.translateTimeToLong(time);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}
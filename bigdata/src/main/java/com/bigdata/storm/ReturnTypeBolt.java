package com.bigdata.storm;
import java.util.Map;

import com.bigdata.storm.pojo.UrlEntity;
import com.bigdata.utils.TimeCategoryUtil;
import com.bigdata.utils.UrlUtils;
import com.bigdata.utils.reids.JedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

public class ReturnTypeBolt extends BaseBasicBolt {
    private TimeCategoryUtil timeUtil;
    private JedisUtil jedisUtil;
    private Jedis jedis;
    private Map<String, UrlEntity> urls;
    private UrlUtils urlUtils;
    ObjectMapper mapper;

    public ReturnTypeBolt() {
    }

    public void prepare(Map stormConf, TopologyContext context) {
        super.prepare(stormConf, context);
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String returnType = input.getString(0);
        String url = input.getString(1);
        String time = input.getString(2);
        this.timeUtil.translateTimeToLong(time);
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}

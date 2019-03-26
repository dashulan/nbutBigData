package com.bigdata.storm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bigdata.utils.TimeCategoryUtil;
import com.bigdata.utils.reids.JedisUtil;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

public class TimeStatisticBolt extends BaseBasicBolt {
    private TimeCategoryUtil timeCategoryUtil;
    private JedisUtil jedisUtil;
    private Jedis jedis;
    private List<String> times;

    public TimeStatisticBolt() {
    }

    public void prepare(Map stormConf, TopologyContext context) {
        this.timeCategoryUtil = new TimeCategoryUtil();
        this.jedisUtil = new JedisUtil();
        this.jedis = this.jedisUtil.getJedis();
        this.times = new ArrayList();
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String date = input.getString(0);
        long timeLong = this.timeCategoryUtil.translateTimeToLong(date);
        this.jedis.lpush("time", new String[]{String.valueOf(timeLong)});
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}
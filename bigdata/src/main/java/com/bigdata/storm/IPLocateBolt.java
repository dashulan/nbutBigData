package com.bigdata.storm;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.bigdata.utils.reids.JedisUtil;
import com.bigdata.utils.rpc.GetIPServiceInterface;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.ipc.RPC;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import redis.clients.jedis.Jedis;

public class IPLocateBolt extends BaseBasicBolt {
    private InetSocketAddress inetSocketAddress;
    private GetIPServiceInterface proxy;
    private JedisUtil jedisUtil;
    private Jedis jedis;
    private HashMap<String, String> provinceMap;

    public IPLocateBolt() {
    }

    public void prepare(Map stormConf, TopologyContext context) {
        this.jedisUtil = new JedisUtil();
        this.inetSocketAddress = new InetSocketAddress("dashulan02", 8899);

        try {
            this.proxy = (GetIPServiceInterface) RPC.getProxy(GetIPServiceInterface.class, 1L, this.inetSocketAddress, new Configuration());
        } catch (IOException var4) {
            var4.printStackTrace();
        }

        this.jedis = this.jedisUtil.getJedis();
        this.provinceMap = new HashMap();
    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String ip = input.getString(0);
        String province = null;

        try {
            province = this.proxy.getIP(ip).split("\\|")[6];
        } catch (Exception var7) {
            province = "null";
        }

        String value = this.jedis.get(province);
        if (value != null) {
            int num = Integer.valueOf(value) + 1;
            this.jedis.set(province, String.valueOf(num));
        } else {
            this.jedis.set(province, String.valueOf(1));
        }

    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
    }
}

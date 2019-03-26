package com.bigdata.storm;

import com.bigdata.utils.FieldPattern;
import com.bigdata.utils.rpc.GetIPServiceInterface;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConvertIPBolt extends BaseBasicBolt {
    private InetSocketAddress inetSocketAddress;
    private GetIPServiceInterface proxy;
    private FieldPattern pattern;
    private List<String> dateUrlRefer;

    public ConvertIPBolt() {
    }

    public void prepare(Map stormConf, TopologyContext context) {
        this.dateUrlRefer = new ArrayList();
        this.pattern = new FieldPattern();
    }

    public void execute(Tuple tuple, BasicOutputCollector outputCollector) {
        String value = tuple.getString(4);
        this.dateUrlRefer = this.pattern.getIPDateHttpCode(value);
        String ip = (String)this.dateUrlRefer.get(0);
        String date = (String)this.dateUrlRefer.get(1);
        String url = (String)this.dateUrlRefer.get(2);
        String code = (String)this.dateUrlRefer.get(3);
        outputCollector.emit("ip_locate", new Values(new Object[]{ip}));
        outputCollector.emit("date_url_code", new Values(new Object[]{date, url, code}));
    }

    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declareStream("ip_locate", new Fields(new String[]{"ip"}));
        outputFieldsDeclarer.declareStream("date_url_code", new Fields(new String[]{"date", "url", "code"}));
    }
}
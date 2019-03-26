package com.bigdata.storm;


import java.util.HashMap;
import java.util.Map;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class DateFormatBolt extends BaseBasicBolt {
    private HashMap<String, String> monthMap;

    public DateFormatBolt() {
    }

    public void prepare(Map stormConf, TopologyContext context) {
        this.monthMap = new HashMap();
        String[] monthes = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String[] monthNums = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

        for(int i = 0; i < monthes.length; ++i) {
            this.monthMap.put(monthes[i], monthNums[i]);
        }

    }

    public void execute(Tuple input, BasicOutputCollector collector) {
        String date = input.getString(0).split(" ")[0].substring(1);
        String monthEng = date.split("/")[1];
        String intMon = (String)this.monthMap.get(monthEng);
        String newDate = date.replace(monthEng, intMon);
        String url = input.getString(1);
        String code = input.getString(2);
        collector.emit("date_time", new Values(new Object[]{newDate}));
        collector.emit("url_time_code", new Values(new Object[]{newDate, url, code}));
    }

    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declareStream("date_time", new Fields(new String[]{"date_time"}));
        declarer.declareStream("url_time_code", new Fields(new String[]{"date", "url", "code"}));
    }
}


package com.bigdata.storm;

import org.apache.storm.Config;
import org.apache.storm.StormSubmitter;
import org.apache.storm.generated.StormTopology;
import org.apache.storm.kafka.spout.KafkaSpout;
import org.apache.storm.kafka.spout.KafkaSpoutConfig;
import org.apache.storm.kafka.spout.KafkaSpoutConfig.Builder;
import org.apache.storm.topology.TopologyBuilder;

public class StatisticTopology {
    public StatisticTopology() {
    }

    public static void main(String[] args) throws Exception {
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        String brokerZkStr = "bigdata2:9092";
        Builder kafkaConfBuilder = KafkaSpoutConfig.builder(brokerZkStr, new String[]{"bigData9"});
        KafkaSpoutConfig conf = kafkaConfBuilder.build();
        topologyBuilder.setSpout("kafka_spout", new KafkaSpout(conf)).setNumTasks(3);
        topologyBuilder.setBolt("id_convertIp_bolt", new ConvertIPBolt()).shuffleGrouping("kafka_spout").setNumTasks(3);
        topologyBuilder.setBolt("ip_locate_bolt", new IPLocateBolt()).shuffleGrouping("id_convertIp_bolt", "ip_locate").setNumTasks(3);
        topologyBuilder.setBolt("dateFormat", new DateFormatBolt()).shuffleGrouping("id_convertIp_bolt", "date_url_code").setNumTasks(2);
        topologyBuilder.setBolt("time_Statistic_Bolt", new TimeStatisticBolt()).shuffleGrouping("dateFormat", "date_time").setNumTasks(2);
        topologyBuilder.setBolt("urlStatisticBolt", new UrlStatisticBolt()).shuffleGrouping("dateFormat", "url_time_code").setNumTasks(4);
        StormTopology topology = topologyBuilder.createTopology();
        String topologyName = StatisticTopology.class.getSimpleName() + "1";
        Config config = new Config();
        config.setNumWorkers(3);
        config.setNumAckers(0);

        StormSubmitter.submitTopology(topologyName, config, topology);
    }
}

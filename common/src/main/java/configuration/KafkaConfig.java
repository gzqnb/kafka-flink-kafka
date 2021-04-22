package configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.Properties;

/**
 * @Auther: gzq
 * @Date: 2021/1/27 - 01 - 27 - 11:30
 * @Description: configuration
 */
@Configuration
@Slf4j

public class KafkaConfig {
    @Value("${kafka.bootstrap.servers}")
    private String host;
    @Value("${kafka.acks}")
    private String acks;
    @Value("${kafka.retries}")
    private int retriesNum;
    @Value("${kafka.serializer}")
    private String serializer;
    @Value("${kafka.deserializer}")
    private String deserializer;
    @Value("${kafka.autoCommitInterval}")
    private String interval;


    public Properties createPropsOfProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", host);
        props.put("acks", acks);
        props.put("retries", retriesNum);
        props.put("key.serializer", serializer);
        props.put("value.serializer", serializer);
        return props;

    }

    public Properties createPropsOfConsumer(String group,boolean flag) {
        Properties props = new Properties();
        props.put("bootstrap.servers", host);
        props.put("group.id", group);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", interval);
        props.put("key.deserializer", deserializer);
        props.put("value.deserializer", deserializer);
        if(flag){
            //重置消费者的offset
            props.put("auto.offset.reset","earliest");
        }
        return props;
    }
}

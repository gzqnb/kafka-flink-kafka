package nevt.producerandconsumer;

import configuration.KafkaConfig;
import constants.KafkaGroupId;
import constants.KafkaTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Properties;

/**
 * @Auther: gzq
 * @Date: 2021/1/27 - 01 - 27 - 11:09
 * @Description: nevt
 */
@Component
public class Consumer {
    @Resource
    private KafkaConfig kafkaConfig;

    public void run() {

        Properties props = kafkaConfig.createPropsOfConsumer(KafkaGroupId.group2,true);

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
        consumer.subscribe(Arrays.asList(KafkaTopic.kafkaTopic_2));
        System.out.println("------------------------------------------------");
        while (true) {
            ConsumerRecords<String, String> records =
                    consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.println(record.key()+"---"+record.value());
        }
    }
}




package nevt.producerandconsumer;


import beans.kafka.Content;
import beans.kafka.Envelope0131;
import beans.kafka.Param;
import configuration.KafkaConfig;
import constants.KafkaTopic;
import org.apache.kafka.clients.producer.KafkaProducer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

/**
 * @Auther: gzq
 * @Date: 2021/1/27 - 01 - 27 - 11:09
 * @Description: nevt
 */
@Component
public class Producer {
    @Resource
    private KafkaConfig kafkaConfig;
    private KafkaProducer<String, String> producer;

    private KafkaProducer<String, String> createProducer() {
        System.out.println("create producer");
        producer = new KafkaProducer<>(kafkaConfig.createPropsOfProducer());
        return producer;
    }

    private void closeKafka() {
        producer.close();
    }

    public void run() throws ExecutionException, InterruptedException {
        KafkaProducer<String, String> kafkaProducer = createProducer();
        System.out.println("begin write to kafka producer");
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            Envelope0131 envelope0131_1 = new Envelope0131(10003, 2000005, null, new Date(), new Date());
            Param param = new Param(hashMap);
            System.out.println(param);
            Content content = new Content(30002, 4000001, 0, null);
            content.setValues(param);
            System.out.println(content);
            envelope0131_1.setContent(content);
            System.out.println(envelope0131_1);


            kafkaProducer.send(new ProducerRecord<String, String>(KafkaTopic.kafkaTopic_1,envelope0131_1.getDataStationTypeId() + "_" + envelope0131_1.getDataStationId() + "_" + content.getDeviceTypeId() + "_"
                    + content.getDeviceId() + "_" + content.getDeviceIndex() + " "  + " " + envelope0131_1.getCTime().getTime() + " " + envelope0131_1.getUTime().getTime()));
            System.out.println(1);
            Thread.sleep(1000);


        }
        closeKafka();
    }
}

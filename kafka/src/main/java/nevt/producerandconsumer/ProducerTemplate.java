package nevt.producerandconsumer;

import configuration.KafkaConfig;
import org.apache.kafka.clients.producer.KafkaProducer;

import javax.annotation.Resource;

/**
 * @Auther: gzq
 * @Date: 2021/2/1 - 02 - 01 - 21:36
 * @Description: nevt.producerandconsumer
 */
public abstract class ProducerTemplate implements ProducerDemo{
    @Resource
    private KafkaConfig kafkaConfig;
    private KafkaProducer<String, String> producer;

    @Override
    public KafkaProducer<String, String> createProducer() {
        return null;
    }

    @Override
    public void closeKafka() {

    }

    @Override
    public void run() {

    }
}

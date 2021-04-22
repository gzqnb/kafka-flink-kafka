package nevt.producerandconsumer;

import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @Auther: gzq
 * @Date: 2021/2/1 - 02 - 01 - 21:32
 * @Description: nevt.producerandconsumer
 */
public interface ProducerDemo {
    public KafkaProducer<String, String> createProducer();
    public void closeKafka();
    public void run();
}

package nevt.start;

import configuration.KafkaConfig;
import nevt.producerandconsumer.Consumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Auther: gzq
 * @Date: 2021/1/27 - 01 - 27 - 16:20
 * @Description: nevt.start
 */
@SpringBootApplication(scanBasePackages = {"nevt", "configuration"})
public class BootStrapConsumer {
    public static void main(String[] args)  {
        ConfigurableApplicationContext context = SpringApplication.run(BootStrapConsumer.class, args);
        KafkaConfig kafkaConfig = context.getBean(KafkaConfig.class);
//        Properties propsOfConsumer = kafkaConfig.createPropsOfConsumer(KafkaGroupId.group2);
//        Properties propsOfConsumer1 = kafkaConfig.createPropsOfConsumer(KafkaGroupId.group2);
//        System.out.println("------------dfdfdfdf"+(propsOfConsumer == propsOfConsumer1));
        Consumer consumer = context.getBean(Consumer.class);
        consumer.run();
    }
}

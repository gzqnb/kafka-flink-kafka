package nevt.start;

import nevt.test.Kafka_Flink_TiaoBian;
import nevt.test.Kafka_Flink_window;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Auther: gzq
 * @Date: 2021/1/27 - 01 - 27 - 13:24
 * @Description: nevt.start
 */
@SpringBootApplication(scanBasePackages = {"nevt","configuration"})
public class BootStrap {
    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context = SpringApplication.run(BootStrap.class, args);
        Kafka_Flink_TiaoBian kafka_flink = context.getBean(Kafka_Flink_TiaoBian.class);
        kafka_flink.run();


    }
}

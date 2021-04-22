package nevt.start;

import nevt.producerandconsumer.Producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



/**
 * @Auther: gzq
 * @Date: 2021/1/27 - 01 - 27 - 11:08
 * @Description: PACKAGE_NAME
 */
@SpringBootApplication(scanBasePackages = {"nevt","configuration"})
public class BootStrapProducer {


    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context = SpringApplication.run(BootStrapProducer.class, args);
        Producer producer = context.getBean(Producer.class);
        producer.run();


    }
}

package nevt.test;

import akka.japi.tuple.Tuple3;
import beans.flink.TemperatureFlink;
import configuration.KafkaConfig;
import constants.KafkaGroupId;
import constants.KafkaTopic;
import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer011;
import org.apache.flink.util.Collector;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Properties;

/**
 * @Auther: gzq
 * @Date: 2021/1/31 - 01 - 31 - 22:56
 * @Description: nevt.test
 */
@Component
public class Kafka_Flink_TiaoBian {
    @Resource
    private KafkaConfig kafkaConfig;
    private StreamExecutionEnvironment env;

    public DataStream<String> createFlinkKafka(String topic) throws Exception {
        System.out.println("create flink Environment");
        env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(2);
        //设置时间语义
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        Properties props = kafkaConfig.createPropsOfConsumer(KafkaGroupId.group1,false);


        // 从kafka读取数据
        DataStream<String> dataStream = env.addSource(new FlinkKafkaConsumer011<String>(topic, new SimpleStringSchema(), props));

        return dataStream;

    }

    public void runFlink() throws Exception {
        System.out.println("flink taskManager start");
        env.execute();
    }

    public void run() throws Exception {
        DataStream<String> inputDataStream = createFlinkKafka(KafkaTopic.kafkaTopic_1);
        DataStream<TemperatureFlink> dataStream = processData_Map(inputDataStream);
//        设置事件时间
        SingleOutputStreamOperator<TemperatureFlink> dataStream_Event = dataStream.assignTimestampsAndWatermarks(

                new AscendingTimestampExtractor<TemperatureFlink>() {
                    @Override
                    public long extractAscendingTimestamp(TemperatureFlink element) {
                        return element.getCTime();
                    }
                }
        );
        KeyedStream<TemperatureFlink, String> keyedStream = dataStream_Event.keyBy(TemperatureFlink::getId);
        DataStream<Tuple3<String, Integer, Integer>> resultStream = keyedStream.flatMap(new tempWarning(10));
//        DataStream<String> sinkDataStream = resultStream.map(line ->
//                new TemperatureFlink(line.getId(), line.getCTime(),line.getUTime(),line.getTemp(),line.getPres()).toString()
//
//        );
        resultStream.print("temperature-Warning");
//        sinkToKafka(sinkDataStream);
        runFlink();

    }

    public static class tempWarning extends RichFlatMapFunction<TemperatureFlink, Tuple3<String, Integer, Integer>> {
        private int threshold;

        public tempWarning(int threshold) {
            this.threshold = threshold;

        }

        private ValueState<Integer> lastTempState;

        @Override
        public void open(Configuration parameters) throws Exception {
            lastTempState = getRuntimeContext().getState(new ValueStateDescriptor<Integer>("last_Temp", Integer.class));
        }

        @Override
        public void flatMap(TemperatureFlink value, Collector<Tuple3<String, Integer, Integer>> out) throws Exception {

            Integer lastTemp = lastTempState.value();
            if (lastTemp != null) {
                int diff = Math.abs(value.getTemp() - lastTemp);
                if(diff>=threshold){
                    out.collect(new Tuple3<String, Integer, Integer>(value.getId(),lastTemp,value.getTemp()));
                }

            }
            lastTempState.update(value.getTemp());
        }

        @Override
        public void close() throws Exception {
            lastTempState.clear();
        }
    }


    public DataStream<TemperatureFlink> processData_Map(DataStream<String> inputDataStream) {
        DataStream<TemperatureFlink> dataStream = inputDataStream.map(line -> {
                    String[] fields = line.split(" ");
                    return new TemperatureFlink(fields[0], new Long(fields[3]), new Long(fields[4]), new Integer(fields[1]), new Integer(fields[2]));
                }

        );
        return dataStream;
    }

    public void sinkToKafka(DataStream<String> resultDataStream) {
        resultDataStream.addSink(new FlinkKafkaProducer011<String>("cdata01:9092", KafkaTopic.kafkaTopic_2, new SimpleStringSchema()));
    }

}

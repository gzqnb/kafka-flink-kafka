package beans.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.lang.reflect.Method;

/**
 * @Auther: gzq
 * @Date: 2021/1/31 - 01 - 31 - 15:45
 * @Description: beans
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private int deviceTypeId;
    private int deviceId;
    private int deviceIndex;
    private Object values;


    public void setValues(Object values) {
        this.values = values;
    }

    @SneakyThrows
    @Override
    public String toString() {
        return "Content{" +
                "deviceTypeId=" + deviceTypeId +
                ", deviceId=" + deviceId +
                ", deviceIndex=" + deviceIndex +
                ", values=[" + values + ']' +
                '}';
    }
}

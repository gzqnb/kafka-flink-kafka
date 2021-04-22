package beans.flink;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: gzq
 * @Date: 2021/1/31 - 01 - 31 - 20:56
 * @Description: beans.flink
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureFlink {
    private String id;
    private long cTime;
    private long uTime;
    private Integer temp;
    private Integer pres;
}

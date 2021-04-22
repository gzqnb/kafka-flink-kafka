package beans.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Auther: gzq
 * @Date: 2021/1/31 - 01 - 31 - 15:40
 * @Description: beans
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Envelope0131 {
    private int dataStationTypeId;
    private int dataStationId;
    private Object content;
    private Date cTime;
    private Date uTime;
}

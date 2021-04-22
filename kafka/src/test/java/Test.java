import beans.kafka.Content;
import beans.kafka.Envelope0131;
import beans.kafka.Param;
import constants.ParamName;

import java.util.Date;
import java.util.HashMap;

/**
 * @Auther: gzq
 * @Date: 2021/1/31 - 01 - 31 - 16:27
 * @Description: PACKAGE_NAME
 */
public class Test {
    public static void main(String[] args) {
        Envelope0131 envelope0131_1 = new Envelope0131(10003, 2000005, null, new Date(), new Date());
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put(ParamName.SMAStatus,0);
        hashMap.put(ParamName.SMAFillDur,16);
        hashMap.put(ParamName.SMAFillMass,9);
        hashMap.put(ParamName.SMAFillFlow,5);
        hashMap.put(ParamName.SMAReserved9,130);
        hashMap.put(ParamName.SMAReserved7,127);
        hashMap.put(ParamName.SMAReserved8,129);
        hashMap.put(ParamName.SMAReserved5,125);
        hashMap.put(ParamName.SMAPressure,52);
        hashMap.put(ParamName.SMAReserved6,127);
        hashMap.put(ParamName.SMAReserved3,123);
        hashMap.put(ParamName.SMAReserved4,125);
        hashMap.put(ParamName.SMAReserved2,122);
        hashMap.put(ParamName.SMAFault,1);
        hashMap.put(ParamName.SMAWarning3,0);
        hashMap.put(ParamName.SMAFillVeh,10);
        hashMap.put(ParamName.SMAWarning2,1);
        hashMap.put(ParamName.SMAReserved10,131);
        hashMap.put(ParamName.SMAWarning1,1);
        hashMap.put(ParamName.SMATemp,13);
        Param param = new Param(hashMap);
        System.out.println(param);
        Content content = new Content(30002, 4000001, 0, null);
        content.setValues(param);
        System.out.println(content);
        envelope0131_1.setContent(content);
        System.out.println(envelope0131_1);
        //唯一id                        当前温度   之前温度       ctime                utime
        //10003_2000005_30002_4000001_0  22           20       1612097643500      1612097643500
        System.out.println(envelope0131_1.getDataStationTypeId() + "_" + envelope0131_1.getDataStationId() + "_" + content.getDeviceTypeId() + "_"
                + content.getDeviceId() + "_" + content.getDeviceIndex() + " " + param.getValue("SMAFillDur")+" "+envelope0131_1.getCTime().getTime()+" "+envelope0131_1.getUTime().getTime());

    }
}

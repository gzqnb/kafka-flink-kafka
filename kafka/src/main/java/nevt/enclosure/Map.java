package nevt.enclosure;

import constants.ParamName;

import java.util.HashMap;
import java.util.Random;

/**
 * @Auther: gzq
 * @Date: 2021/2/1 - 02 - 01 - 20:36
 * @Description: nevt.enclosure
 */
public class Map {
    public static HashMap<String, Integer> getMap(HashMap<String, Integer> hashMap){
        Random random = new Random();
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
        hashMap.put(ParamName.SMATemp,20 + (int) (random.nextGaussian() * 20));
        return hashMap;
    }
}

package beans.kafka;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: gzq
 * @Date: 2021/1/31 - 01 - 31 - 16:10
 * @Description: beans
 */
@Data
@NoArgsConstructor
public class Param {
    private HashMap<String, Integer> paramName_Value;

    public Param(HashMap<String, Integer> paramName_Value) {
        this.paramName_Value = paramName_Value;
    }

    public int getValue(@NotNull String paraName) {
        boolean flag = false;
        int value = 0;
        for (Map.Entry<String, Integer> entry : paramName_Value.entrySet()) {
            if (paraName.equals(entry.getKey())){
                value = entry.getValue();
                flag = true;
                break;
            }
        }
        if(flag){
            return value;
        }else
            throw new RuntimeException("没有该数据");
    }


    @Override
    public String toString() {
        String str = "";
        for (Map.Entry<String, Integer> entry : paramName_Value.entrySet()) {
            String paraName = entry.getKey();
            int value = entry.getValue();
            str += "Param{" +
                    "paraName=" + paraName + "," + "value=" + value +
                    '}' + " ";
        }
        return str;
    }
}

package yinlei.com.rxandroid.rx_login;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 在此写用途
 *
 * @version V1.0 <描述当前版本功能>
 * @FileName: yinlei.com.rxandroid.rx_login.JsonUtils.java
 * @author: myName
 * @date: 2016-07-23 11:20
 */

public class JsonUtils {

    public static boolean parse(String json) {
        boolean flag = false;
        try {
            int result = new JSONObject(json).getJSONObject("result").getInt("resultCode");
            if (result == 1) {
                flag = true;
            } else {
                flag = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return flag;
    }
}

package com.test.basemoudle.utils;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by panliuting on 16/3/18.
 */
public class JsonUtils {

    private static volatile Gson mGson;

    private static Gson getGson() {
        if (mGson == null) {
            synchronized (JsonUtils.class) {
                if (mGson == null) {
                    mGson = new Gson();
                }
            }
        }
        return mGson;
    }

    /**
     * 转成okhttp3的 RequestBody
     */
    public static RequestBody toRequestBody(JSONObject data) {
        RequestBody body =
                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        data.toString());
        return body;
    }

    public static RequestBody toRequestBody(String date) {
        RequestBody body =
                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        date);
        return body;
    }

    public static RequestBody toRequestBody(JSONArray data) {
        RequestBody body =
                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        data.toString());
        return body;
    }

    public static RequestBody toRequestBody(Object data) {
        //String string = getGson().toJson(data);
        RequestBody body =
                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),
                        getGson().toJson(data));
        return body;
    }

    public static String toJson(Object data) {
        return getGson().toJson(data);
    }

    private static String toJson(Map map) {
        if (map.isEmpty()) {
            return "{}";
        }
        Gson gson = getGson();
        String json = gson.toJson(map);
        return json;
    }

    /**
     * html参数转map
     */
    public static Map<String, String> getHeader(String url) {
        Map<String, String> map = new HashMap<String, String>();
        int start = url.indexOf("?");
        if (start >= 0) {
            String str = url.substring(start + 1);
            System.out.println(str);
            String[] paramsArr = str.split("&");
            for (String param : paramsArr) {
                String[] temp = param.split("=");
                map.put(temp[0], temp[1]);
            }
        }
        return map;
    }
}

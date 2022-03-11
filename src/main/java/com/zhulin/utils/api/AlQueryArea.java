package com.zhulin.utils.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhulin.utils.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Program:hzWeather
 * @author：ZHULIN
 * @Description:阿里获取城市天气信息接口
 * @Create：14:21 2022/3/6
 */
public class AlQueryArea {

    /**
     * 获取城市天气信息
     * @param city
     * @return
     */
    public static JsonNode getWeatherByArea(String city){
        String host = "https://jmweather.market.alicloudapi.com";
        String path = "/weather/query/by-area";
        String method = "POST";
        String appcode = "63119ba31dfd4aa586bed741ee3eae56";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("area", city);
        JsonNode root = null;
        try {

            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);

            String json = EntityUtils.toString(response.getEntity(), "UTF-8");
            ObjectMapper mapper = new ObjectMapper();
            root = mapper.readTree(json);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return root;
    }
}

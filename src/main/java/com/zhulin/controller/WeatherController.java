package com.zhulin.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhulin.utils.AddressIp;
import com.zhulin.utils.api.AlQueryArea;
import com.zhulin.utils.api.AlQueryIp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Program:hzWeather
 * @author：ZHULIN
 * @Description:TODO
 * @Create：14:24 2022/3/6
 */
@RestController
public class WeatherController {

    /**
     * 自动获取定位  获取天气信息
     * @param request
     * @return
     */
    @GetMapping("/getWeatherInfo")
    public JsonNode getWeatherInfo(HttpServletRequest request){
        ObjectMapper mapper=new ObjectMapper();

        //获取所在地ip地址
        String addressIp= AddressIp.getAddressIp(request);
        //根据ip地址查询城市
        JsonNode areas= AlQueryIp.getCityByIp(addressIp);
        //处理json数据
        JsonNode areaData=areas.path("data");
        Map<String,String> areaMap=mapper.convertValue(areaData, Map.class);
        //获取城市名
        String city=areaMap.get("city");
        JsonNode weathers=AlQueryArea.getWeatherByArea(city);
        return weathers;
    }

    /**
     * 根据城市名获取天气信息
     * @param city
     * @return
     */
    @PostMapping("/getWeatherInfoByCity")
    public JsonNode getWeatherInfoByCity(String city){
        JsonNode weathers=AlQueryArea.getWeatherByArea(city);
        return weathers;
    }
}

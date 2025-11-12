package com.cy.store.service.impl;

import com.cy.store.service.IGeoCodingService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class GeoCodingServiceImpl implements IGeoCodingService {
    private static final String AMAP_KEY = "368b024dd353e7b5e0f3fd405d7871c6";
    private static final String GEOCODE_URL = "https://restapi.amap.com/v3/geocode/geo";
    private static final String REVERSE_GEOCODE_URL = "https://restapi.amap.com/v3/geocode/regeo";

    private final RestTemplate restTemplate = new RestTemplate();


    @Override
    public String getLocation(String address) {
        try {
            String url = String.format("%s?address=%s&key=%s",
                    GEOCODE_URL, URLEncoder.encode(address, "UTF-8"), AMAP_KEY);
            String response = restTemplate.getForObject(url, String.class);
            if (response == null) return "解析失败";

            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            if (json.has("geocodes")) {
                var geocodes = json.getAsJsonArray("geocodes");
                if (geocodes.size() > 0) {
                    return geocodes.get(0).getAsJsonObject().get("location").getAsString();
                }
            }
            return "未找到经纬度";
        } catch (Exception e) {
            e.printStackTrace();
            return "解析异常：" + e.getMessage();
        }
    }

    @Override
    public String getAddress(double lng, double lat) {
        try {
            String url = String.format("%s?location=%f,%f&key=%s&radius=1000&extensions=base",
                    REVERSE_GEOCODE_URL, lng, lat, AMAP_KEY);
            String response = restTemplate.getForObject(url, String.class);
            if (response == null) return "解析失败";

            // 使用 Gson 解析 JSON
            JsonObject json = JsonParser.parseString(response).getAsJsonObject();
            if (json.has("regeocode")) {
                JsonObject regeocode = json.getAsJsonObject("regeocode");
                if (regeocode.has("formatted_address")) {
                    return regeocode.get("formatted_address").getAsString();
                }
            }
            return "未找到地址";
        } catch (Exception e) {
            e.printStackTrace();
            return "解析异常：" + e.getMessage();
        }
    }
}

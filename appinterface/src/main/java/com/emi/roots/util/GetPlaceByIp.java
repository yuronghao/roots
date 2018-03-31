package com.emi.roots.util;


import com.alibaba.fastjson.JSONException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class GetPlaceByIp {
    private static final String url="http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=";
    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }
    public static String getUserCity(String Ip) throws IOException, JSONException {
        InputStream is = new URL(url+Ip).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            System.out.println(jsonText);
            if(jsonText!=null){
                Map<String,Object> map=Object2Json.getKeyJsonMap(jsonText,new TypeToken<Map<String,Object>>(){}.getType());
                if(map.get("content")!=null){
                    Map m1=(Map)map.get("content");
                    if(m1!=null){
                        Map m2=(Map)m1.get("address_detail");
                        if(m2!=null){
                            return String.valueOf(m2.get("city"));
                        }
                    }
                }
            }
            return "";
        } catch(Exception e){
            e.printStackTrace();
            return "";
        }finally {
            is.close();
            // System.out.println("同时 从这里也能看出 即便return了，仍然会执行finally的！");
        }
    }
    public static void main(String[] args) throws IOException, JSONException {
        //这里调用百度的ip定位api服务 详见 http://api.map.baidu.com/lbsapi/cloud/ip-location-api.htm
        String json = getUserCity("http://api.map.baidu.com/location/ip?ak=F454f8a5efe5e577997931cc01de3974&ip=223.104.210.108");
        System.out.println(json);
        //System.out.println(((JSONObject) json.get("content")).get("address"));
    }
}

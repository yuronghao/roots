package com.emi.roots.util;

import com.alibaba.fastjson.JSONObject;
import com.emi.roots.entity.AzWeixinOptions;
import com.emi.roots.entity.WeixinXCXInfo;
import com.emi.roots.entity.WeixinXCXLoginResult;
import com.emi.roots.wechat.bean.UserInfo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class XcxUtil {


    private static final String GET_XCXLOGIN="https://api.weixin.qq.com/sns/jscode2session";
    public static WeixinXCXLoginResult getXCXLoginInfoByAuth(WeixinXCXInfo weixinXCXInfo){
        String jsonString=null;
        try {
            jsonString = HttpClient.get(GET_XCXLOGIN+"?appid="+weixinXCXInfo.getAppid()+"&secret="+weixinXCXInfo.getSecret()+"&js_code="+weixinXCXInfo.getJs_code()+"&grant_type=authorization_code");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if(jsonString!=null){
            return JSONObject.parseObject(jsonString, WeixinXCXLoginResult.class);
        }
        return null;
    }

}

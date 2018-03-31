/*
 * 2015-4-27 
 * BaseService.java 
 * author:赵永飞
 */
package com.emi.roots.base.service;


import com.emi.roots.mapper.WeChatConfMapper;
import com.emi.roots.util.Object2Json;
import com.emi.roots.wechat.bean.WeChatConf;
import com.emi.roots.wechat.util.WeChat;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.Random;

/**
 * @author zhaoyf
 *
 */
@Service("baseService")
public class BaseService {
	Logger logger = Logger.getLogger(BaseService.class);
	@Autowired
	SqlSessionFactory sqlSessionFactory;
	@Autowired
	WeChatConfMapper weChatConfMapper;

	
	public Map<String,Object> getJsonObjectMap(String json){
		if(json!=null&&!"".equals(json)){
			try{
				Map<String,Object> map= Object2Json.getKeyJsonMap(json,new TypeToken<Map<String,Object>>(){}.getType());
				return map;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	
	public JSONObject getStringtoJson(String json){
		if(json!=null&&!"".equals(json)){
			try{
				JSONObject j=JSONObject.fromObject(json);
				return j;
			}catch(Exception e){
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
	
	public static  DecimalFormat ordernodf=new DecimalFormat("0000");
	
	/**
	 * 产生随机的三位数
	 * @return
	 */
	public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}


	public void updateAccesstokenAndJSDKTicket() {
		WeChatConf wechatconf=weChatConfMapper.getWechatconf();
		String sss=null;
		String apiticket = null;//卡券api_ticket
		String accesstoken=null;
		try {
			accesstoken = WeChat.getAccessToken(wechatconf);

			sss = WeChat.getJsApiTicket(accesstoken);
			apiticket = WeChat.getApiTicket(accesstoken);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(sss!=null){
			weChatConfMapper.updateJSDKTicket(sss, wechatconf.getId());
		}
		if(apiticket!=null){
			weChatConfMapper.updateApiTicket(apiticket, wechatconf.getId());
		}
		if(accesstoken!=null){
			weChatConfMapper.updateAccesstoken(accesstoken, wechatconf.getId());
		}

	}


//	public Immessageconf getImmessageconf(){
//		return immessageconfMapper.getImmessageconf();
//	}


}

package com.emi.roots.wechat.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emi.roots.entity.AzWeixinOptions;
import com.emi.roots.util.*;
import com.emi.roots.wechat.bean.*;
import com.emi.roots.wechat.inf.MessageProcessingHandler;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class WeChat {
	public static String JSDKURL="http://www.emi365.com/sf/mobile/accessRecord.do";
	
	private static final String DEFAULT_HANDLER = "com.emi.ss.wechat.inf.DefaultMessageProcessingHandlerImpl";
	private static final String ACCESSTOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	 
	public static String DEVICENO="SEREVICENET";
	public static String privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJXLBpFVB7EkyJgeEUBRGG1/Ad4ERl50ndGN2OxD2RH1fPYjvGBzs3wJo3KjbyKJ/74GqsuWQtYf9DYcY4wHeYwS8iS9mzRLnm/VU27fJ/Rf5tNOXEvz6A2UlbvXYXrbNmLQedisNPtnt7X0JDfhI1iwYBKJy5gjtPdOW8TJYGY/AgMBAAECgYBkb/BkbDYvIfHahXadwIJ3kMyIuCcS2HClfM3xhYC8GPWD+OwrZAj552CP0mQHLa6sFL5aqdMAzgYqbhiVYMGvENXe4SbDoe9g4TDhmnjwvtNPlfmjqxwYz5TMysScNd6F8/5SI/41EpejA3EwDlVqK40vcGda28q2MzcclQoXOQJBANzhQLJ7aMAJYPHG5WxmCDcurdgYjAlejdyyPhRaSM3rBJYGevNvARX2bRldSmXck730cJAqSkWmjHTw0RqYE9MCQQCtnDzxpn43XY65r8oiliQQaf9joHfdcTY/AE8YLLOhfagkZJFmZUC6869tgLCVCPqMt/egm7u17mM3z2xwBJxlAkBtyomeLjmjOwY1J+hiOVVcGJfREmKJRba+PNsewsHtmSRubkovNr4TbKpOMVEJ0NqfUSGih2LjvCWU+x71WSKBAkB/xMR1c/rf9FLeBanV2nmgOuJiB+8vYepyY1/SgtOQvlHIttrraQlCjaVGCbfrdzgt6BaudO6o7lDG+jjsF/ihAkEAl0arOG22JCu+Vnv/smtLecpS/yFcejGOXsZ6bqtxpYzbpvy5YDrfrpjthlCIiJv7rdEVKJyNcZSiBKXVDHCm2A==";
	public static int userid=161;


	public static String WXDEVICENO="SEREVICENET2";
	public static String wxprivateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANQyof2LXWxZLeeAMu6Kxjl9iISu9vlwK8c9hlAtb7aMXt/MDH3+HlDNiMPoMx4WPJ4dryXAHWBX1+zUn8mUxN4qJ+D+eUR3m8EjDq60NKObdsaVoi8kW6MoJzqNTq9n7IXwjPOSR7QzKIUaBietPOtlWsO+Fhq92W3gwrF4I1QvAgMBAAECgYEA0JdBRALGgGadyE7uQi1qtELhe0SfBQ6uCjpBmPlBMpqD/M0RjpzJ4jZY9fOApFfJmMinTp2ugvF3EZjlxfuXFN1nnJS+F4v3efzOmncEC/faf+l3xMZCN7a/MP9QFmEebc91f499jrXhiJJw+vOb6gCT+jrqknJUUB5R5j8sZDkCQQD9BW6RUF1hqxyVBUpzlH2XjlSL1QtFWEJmDum9sCqLK288RvAE7uojq31nGxZ9ooAo6LBFP1QOsfAmYn9wW6ljAkEA1rIqRyDh7XSbVaq5ZjH6CtQQqn4W8cj/+l0L4nJJ3xNfhPYBsEFxW2XQTyCoyybTD1gCVW8LVLR9StlL+HyJxQJBAPt2I44eDcCcm5prGLRH1o4CMXqLcFkpv0vI9HrGE3Ie571DTxXIEEEFFWucWzzffHmr0EvyBWtZYq/zYm5bWrsCQELzWlXF/k5DP7FuRNSGnzfDO9M+8L7DdHfJjkw2Yazr6KyY3pvtPIFI9bXHNHvthoHRayDjOQBYLH4r1xINHBUCQH6SMdWMbq7SnhHJDbipRK3negPV7TjGGDNvaCrF96QhimEgObYLg9d5dJvC7wSe0xBZsmZ1QgBYlX+TpPoCXpQ=";
	public static int wxuserid=163;

	
	public static String GET_ACCESSTOKEN_URL="htpp://www.emi365.cn/emipay/pos/getAccessToken.do";
	public static String accesstoken="";
	public static Map<String,String> paytypeCodeMap=new HashMap<String,String>();
	private static Class<?> messageProcessingHandlerClazz = null;
	
	public static int WECHATCONF_ORGID=1;//
	static{
		paytypeCodeMap.put("1","wechatonline");
		paytypeCodeMap.put("2","alipayonline");
		paytypeCodeMap.put("3","wechath5");
		paytypeCodeMap.put("4","balance");
	}
	
    /**
     * 创建菜单
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
	public static boolean createMenu(String accessToken,String params) throws InterruptedException, ExecutionException, IOException {
        String jsonStr = HttpClient.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken, params);
        Map<String, Object> map = JSON.parseObject(jsonStr,Map.class);
        System.out.println("==============ret==="+map);
        return "0".equals(map.get("errcode").toString());
    }
	

	
//    public static void main(String[] args)throws Exception{
////    	 String jsonStr = HttpClient.get(ACCESSTOKEN_URL.concat("&appid=") + "wx867892cc26ffb064" + "&secret=" + "c0a0bdfda9f063e1dfb70af9532cc30c");
////    	
//    	String accessToken=getAccessToken("wx2e8f60478ad58397", "973a32b5c8d6b3fd554a9bf718bb0191");
//    	
//    	String menu=getAuthUrlUserInfo("wx2e8f60478ad58397","http://www.emi365.com/ss/mobile/superJump.do");
//    	
//    	String p=" { "+
//				    " \"button\":[ "+
//				   "  {	"+
//				        "  \"type\":\"view\","+
//				         " \"name\":\"微商城3\","+
//				         " \"url\":\""+menu+"\""+
//				     " },"+
//				     " {"+
//				          " \"name\":\"测试\","+
//				          " \"sub_button\":["+
//				          " {"+	
//				             "  \"type\":\"view\","+
//				             "  \"name\":\"搜索\","+
//				             "  \"url\":\"http://www.baidu.com/\""+
//				          "  },"+
//				          "  {"+
//				             "  \"type\":\"view\","+
//				             "  \"name\":\"视频\","+
//				             "  \"url\":\"http://v.qq.com/\""+
//				          "  },"+
//				          " {"+
//				             "  \"type\":\"click\","+
//				              " \"name\":\"赞一下我们\","+
//				             "  \"key\":\"V1001_GOOD\" "+
//				           " }]"+
//				     "  }]"+
//				" }";
//    	
//    	createMenu("z8qUQE-IIb-KC3iYnYFi3nqg_gwRvV3UIF50XGGqp4q8Lt5Bt2d3qEkUipDplC1PL3ZCcuLPgVgba_M-plyybq3-feHFPxgLS1767SP5cu-BOhMm0FL6zdM_AkPP_BlbAJEjAHAPYT",p);
//    
//    }
    
	public static  String getAuthUrl(String appid,String url){
		   String reteurl="";
			try {
				reteurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+java.net.URLEncoder.encode(url, "UTF-8")+"&response_type=code&scope=snsapi_base&state=a#wechat_redirect";
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return reteurl;
	}
	public static  String getAuthUrlUserInfo(String appid,String url){
		   String reteurl="";
			try {
				reteurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+java.net.URLEncoder.encode(url, "UTF-8")+"&response_type=code&scope=snsapi_userinfo&state=a#wechat_redirect";
			} catch (UnsupportedEncodingException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return reteurl;
	   }
	/**
	 * 获取accesstoken
	 * @return
	 */
	public String getAccessToken(){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("deviceno", DEVICENO);
		Map<String,Object> mm=postUrl(GET_ACCESSTOKEN_URL,param);
		  if(mm!=null){
				String successflag=String.valueOf(mm.get("success"));
				if(successflag!=null&&"1".equals(successflag)){
						return String.valueOf(mm.get("accesstoken"));
				}
			}
		return "";
	}
	public static String getJsApiTicket(String accesstoken){
	   	try {
				String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token="+accesstoken;
				String jsonStr = HttpClient.get(url);
				System.out.println("=================================getJsApiTicket="+jsonStr);
			    Map<String, Object> map = JSONObject.parseObject(jsonStr);
			    return map.get("ticket").toString();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return null;
			}
	   }
	
	 public static String getAccessToken(WeChatConf wechatConf) throws Exception {
	        String jsonStr = HttpClient.get(ACCESSTOKEN_URL.concat("&appid=") + wechatConf.getWxappid() + "&secret=" + wechatConf.getWxsecret());
	        Map<String, Object> map = JSONObject.parseObject(jsonStr);
	        if(map.get("access_token")!=null){
	        	return map.get("access_token").toString();
	        }
	        return null;
	    }
	
    public static String getAccessToken(String appid,String secret) throws Exception {
        String jsonStr = HttpClient.get(ACCESSTOKEN_URL.concat("&appid=") + appid + "&secret=" + secret);
        Map<String, Object> map = JSONObject.parseObject(jsonStr);
        return map.get("access_token").toString();
    }
	
	public static Map<String,Object> postUrl(String url,Map<String,Object> param){
		String ret=null;
		try {
			 ret=HttpClient.post(url, param);
			 if(ret!=null){
				 Map mm= Object2Json.getKeyJsonMap(ret, new TypeToken<Map<String,Object>>(){}.getType());
				 return mm;
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static Map<String,Object> postUrl(String url,String param){
		String ret=null;
		try {
			ret=HttpClient.post(url, param);
			System.out.println("postUrl==="+ret);
			if(ret!=null){
				Map mm=Object2Json.getKeyJsonMap(ret, new TypeToken<Map<String,Object>>(){}.getType());
				return mm;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}




	public static void main(String[] args){
//		System.out.println(getAuthUrlUserInfo("wx9a5ebe967595f6eb","http://www.emi365.com/sf/mobile/introducej.do"));
		System.out.println(getAuthUrlUserInfo("wx7188d8bf75bf45a7","http://www.sws-net.cn/emishoptest/mobile/toRegisterWx.do"));
//		try {
////			getUserInfoByAuth()
//			WeChatConf wechatConf = new WeChatConf();
//			wechatConf.setWxsecret("e9eccbed31268ccfed6f0517a994d0e1");
//			wechatConf.setWxappid("wxff3f15873a883fee");
//			UserInfo userInfo=getUserInfoByAuth(wechatConf, "011SD80o1q2Fsk0ehwXn1xJ60o1SD804");
//			System.out.println(JSON.toJSONString(userInfo));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6bfc9a7399afe2ea&redirect_uri=http%3A%2F%2Fwww.emi365.com%2Fhgweb%2Fmobile%2FsuperJump.do%3Forgid%3D2&response_type=code&scope=snsapi_userinfo&state=a#wechat_redirect
	}


	
	private static final String GET_AUTH_ACCESSTOKEN="https://api.weixin.qq.com/sns/oauth2/access_token";
	private static final String GET_AUTH_USERINFO="https://api.weixin.qq.com/sns/userinfo";
	public static UserInfo getUserInfoByAuth(AzWeixinOptions azWeixinOptions, String code){
		String acctesstoken=null;
		try {
			acctesstoken = HttpClient.get(GET_AUTH_ACCESSTOKEN+"?appid="+azWeixinOptions.getApp_id()+"&secret="+azWeixinOptions.getApp_secret()+"&code="+code+"&grant_type=authorization_code");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		if(acctesstoken!=null){
			Map<String, Object> map = JSONObject.parseObject(acctesstoken);
			String tmpaccesstoken=null;
			if(map.get("access_token")!=null){
				tmpaccesstoken= map.get("access_token").toString();
			}
			String tmpopenid=null;
			if(map.get("openid")!=null){
				tmpopenid=map.get("openid").toString();
			}
			if(tmpaccesstoken!=null&&tmpopenid!=null){
				String ret=null;
				try {
					ret=HttpClient.get(GET_AUTH_USERINFO+"?access_token="+tmpaccesstoken+"&openid="+tmpopenid+"&lang=zh_CN");
				} catch (KeyManagementException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchProviderException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ExecutionException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(ret!=null){
					UserInfo userinfo=null;
					JSONObject obj = JSONObject.parseObject(ret);
					if(obj.get("errcode") != null){
						try {
							throw new Exception(obj.getString("errmsg"));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
					 userinfo= JSONObject.toJavaObject(obj, UserInfo.class);
					 return userinfo;
					}
				}

			}
			
		}
		return null;
	}
   
	public static String GET_ORDERNO_URL="http://www.emi365.cn/emipay/pos/addOnlineOrder.do";

//	public static Map<String,Object> initOrderTradeNo(Activitysignup activitysignup){
//		if(activitysignup!=null){
//			String codevalue=activitysignup.getPaytype();
//			if(codevalue!=null){
//				String code=paytypeCodeMap.get(codevalue);
//				Map<String,Object> retMap=new HashMap<String,Object>();
//				retMap.put("deviceno", DEVICENO);
//				Map<String,Object> jsonMap=new HashMap<String,Object>();
//				jsonMap.put("price", activitysignup.getPrice());
//				jsonMap.put("paycode", code);
//				jsonMap.put("userid", userid);
//				String json= Object2Json.bean2Json2(jsonMap);
//				String sign =null;
//				 try {
//					 sign = RSAUtil.sign(json, privateKey);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				retMap.put("json",json);
//				retMap.put("sign", sign);
//				Map<String,Object> rm=postUrl(GET_ORDERNO_URL,retMap);
//				if(rm!=null&&"1".equals(String.valueOf(rm.get("success")))){
//					String orderno=String.valueOf(rm.get("orderno"));
//					if(orderno!=null){
//						activitysignup.setTradeno(orderno);
//						jsonMap.put("orderno", orderno);
//						json= Object2Json.bean2Json2(jsonMap);
//						sign =null;
//						 try {
//							 sign = RSAUtil.sign(json, privateKey);
//						} catch (Exception e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						retMap.put("json",json);
//						retMap.put("sign", sign);
//						return retMap;
//					}
//				}
//
//			}
//		}
//		return null;
//	}
	
	public static Map<String,Object> initOrderTradeNo(String paytype, BigDecimal topayprice,WeChatConf conf){
			if(paytype!=null){
				String code=paytypeCodeMap.get(paytype);
				Map<String,Object> retMap=new HashMap<String,Object>();
				retMap.put("deviceno", DEVICENO);
				Map<String,Object> jsonMap=new HashMap<String,Object>();
				jsonMap.put("price", topayprice);
				jsonMap.put("paycode", code);
				jsonMap.put("userid", userid);
                jsonMap.put("ticket",conf.getJsdkticket());
				String json= Object2Json.bean2Json2(jsonMap);
				String sign =null;
				 try {
					 sign = RSAUtil.sign(json, privateKey);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
				retMap.put("json",json);
				retMap.put("sign", sign);
				Map<String,Object> rm=postUrl(GET_ORDERNO_URL,retMap);
				if(rm!=null&& ( "1".equals(String.valueOf(rm.get("success"))) || "1.0".equals(String.valueOf(rm.get("success"))))){
					String orderno=String.valueOf(rm.get("orderno"));
					if(orderno!=null){
						jsonMap.put("orderno", orderno);
						json= Object2Json.bean2Json2(jsonMap);
						sign =null;
						 try {
							 sign = RSAUtil.sign(json, privateKey);
						} catch (Exception e) {
							e.printStackTrace();
						}
						retMap.put("tradeno",orderno);
						retMap.put("json",json);
						retMap.put("sign", sign);
						return retMap;
					}
				}
				
			}
		return null;
	}


	public static Map<String,Object> initOrderTradeNoWechat(String paytype, BigDecimal topayprice,WeChatConf conf){
		if(paytype!=null){
			String code=paytypeCodeMap.get(paytype);
			Map<String,Object> retMap=new HashMap<String,Object>();
			retMap.put("deviceno", WXDEVICENO);
			Map<String,Object> jsonMap=new HashMap<String,Object>();
			jsonMap.put("price", topayprice);
			jsonMap.put("paycode", code);
			jsonMap.put("userid", wxuserid);
			jsonMap.put("ticket",conf.getJsdkticket());
			String json= Object2Json.bean2Json2(jsonMap);
			String sign =null;
			try {
				sign = RSAUtil.sign(json, wxprivateKey);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retMap.put("json",json);
			retMap.put("sign", sign);
			Map<String,Object> rm=postUrl(GET_ORDERNO_URL,retMap);
			if(rm!=null&& ( "1".equals(String.valueOf(rm.get("success"))) || "1.0".equals(String.valueOf(rm.get("success"))))){
				String orderno=String.valueOf(rm.get("orderno"));
				if(orderno!=null){
					jsonMap.put("orderno", orderno);
					json= Object2Json.bean2Json2(jsonMap);
					sign =null;
					try {
						sign = RSAUtil.sign(json, wxprivateKey);
					} catch (Exception e) {
						e.printStackTrace();
					}
					retMap.put("tradeno",orderno);
					retMap.put("json",json);
					retMap.put("sign", sign);
					return retMap;
				}
			}

		}
		return null;
	}
	
	public static String REFUND_PASSWORD_VERTIFY="http://www.emi365.cn/emipay/refundPasswordVertify.do";
	/**
	 * 退款密码验证
	 * @param
	 * @param password
	 * @return
	 */
//	public static boolean refundPasswordVertify(Activitysignup activitysignup,String password){
//		Map<String,Object> retMap=new HashMap<String,Object>();
//		retMap.put("deviceno", DEVICENO);
//		Map<String,Object> jsonMap=new HashMap<String,Object>();
//		jsonMap.put("orderno", activitysignup.getTradeno());
//		jsonMap.put("userid", userid);
//		jsonMap.put("passowrd", password);
//		String json= Object2Json.bean2Json2(jsonMap);
//		String sign =null;
//		 try {
//			 sign = RSAUtil.sign(json, privateKey);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		retMap.put("json",json);
//		retMap.put("sign", sign);
//		Map<String,Object> rm=postUrl(REFUND_PASSWORD_VERTIFY,retMap);
//		if(rm!=null){
//			if("1".equals(String.valueOf(rm.get("success")))){
//				return true;
//			}
//		}
//		return false;
//
//	}
	public static String REFUND_URL="http://www.emi365.cn/emipay/refundPrice.do";


//	public static boolean refundPrice(Orderdetail orderdetail,Order order){
//		Map<String,Object> retMap=new HashMap<String,Object>();
//		retMap.put("deviceno", DEVICENO);
//		Map<String,Object> jsonMap=new HashMap<String,Object>();
//		jsonMap.put("orderno", order.getTradeno());
//		jsonMap.put("userid", userid);
//		jsonMap.put("refundprice", orderdetail.getRefundprice());
//		String json= Object2Json.bean2Json2(jsonMap);
//		String sign =null;
//		 try {
//			 sign = RSAUtil.sign(json, privateKey);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		retMap.put("json",json);
//		retMap.put("sign", sign);
//		Map<String,Object> rm=postUrl(REFUND_URL,retMap);
//		if(rm!=null){
//			if("1".equals(String.valueOf(rm.get("success")))){
//				return true;
//			}
//		}
//		return false;
//	}
	
    public static Boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        return Common.checkSignature(token, signature, timestamp, nonce);
    }
	
    
    /**
     * 根据接收到用户消息进行处理
     *
     * @param responseInputString 微信发送过来的xml消息体
     * @return
     */
    public static ResponseObject processing(String responseInputString, WeChatConf weChatConf) {
        InMessage inMessage = parsingInMessage(responseInputString);
        OutMessage oms = null;
        // 加载处理器
        if (messageProcessingHandlerClazz == null) {
            // 获取自定消息处理器，如果自定义处理器则使用默认处理器。
          //  String handler = PropertyHolder.getProperty("wechat.MessageProcessingHandlerImpl");
           String handler="com.emi.ss.wechat.inf.DefaultMessageProcessingHandlerImpl";
        	handler = handler == null ? DEFAULT_HANDLER : handler;
            try {
                messageProcessingHandlerClazz = Thread.currentThread().getContextClassLoader().loadClass(handler);
            } catch (Exception e) {
                throw new RuntimeException("messageProcessingHandler Load Error！");
            }
        }
        String xml = "";
        try {
            MessageProcessingHandler messageProcessingHandler = (MessageProcessingHandler) messageProcessingHandlerClazz.newInstance();
            //取得消息类型
            String type = inMessage.getMsgType();
            Method getOutMessage = messageProcessingHandler.getClass().getMethod("getOutMessage");
            Method alMt = messageProcessingHandler.getClass().getMethod("allType", InMessage.class,WeChatConf.class);
            Method mt = messageProcessingHandler.getClass().getMethod(type + "TypeMsg", InMessage.class,WeChatConf.class);
            messageProcessingHandler.setAccesstoken(weChatConf.getAccesstoken());
            alMt.invoke(messageProcessingHandler, inMessage,weChatConf);
            if (mt != null) {
                mt.invoke(messageProcessingHandler, inMessage,weChatConf);
            }

            Object obj = getOutMessage.invoke(messageProcessingHandler);
            if (obj != null) {
                oms = (OutMessage) obj;
            }
            //调用事后处理
            try {
                Method aftMt = messageProcessingHandler.getClass().getMethod("afterProcess", InMessage.class, OutMessage.class);
                aftMt.invoke(messageProcessingHandler, inMessage, oms);
            } catch (Exception e) {
            }

            obj = getOutMessage.invoke(messageProcessingHandler);
            if (obj != null) {
                oms = (OutMessage) obj;
                setMsgInfo(oms, inMessage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ResponseObject ret = new ResponseObject();
        if (oms != null)
        {
            // 把发送发送对象转换为xml输出
            // XMLFactory factory = new XMLFactory(oms.getClass());

            if(oms instanceof TemplateOutMessage){
                ret.setType("templateresponse");
                ret.setObject(oms);
            }else{
                XmlCataFactory factory = new XmlCataFactory(oms.getClass());
                xml = factory.marshal(oms);
                ret.setObject(xml);
                ret.setType(null);
            }
        }else{
        	return null;
        }
        return ret;
    }
    
    /**
     * 消息体转换
     *
     * @param responseInputString
     * @return
     */
    public static InMessage parsingInMessage(String responseInputString) {
        //转换微信post过来的xml内容

    	XMLFactory factory = new XMLFactory(InMessage.class);
        InMessage msg = (InMessage) factory.unmarshal(responseInputString);
        System.out.println("msgtype:"+msg.getMsgType());
        System.out.println("Event:" + msg.getEvent());
        System.out.println("FromUserName:" + msg.getFromUserName());
        System.out.println("msgid:" + msg.getMsgID());
        return msg;
    }
    
    /**
     * 设置发送消息体
     *
     * @param oms
     * @param msg
     * @throws Exception
     */
    private static void setMsgInfo(OutMessage oms, InMessage msg) throws Exception {
        if (oms != null) {
            Class<?> outMsg = oms.getClass().getSuperclass();
            Field CreateTime = outMsg.getDeclaredField("CreateTime");
            Field ToUserName = outMsg.getDeclaredField("ToUserName");
            Field FromUserName = outMsg.getDeclaredField("FromUserName");

            ToUserName.setAccessible(true);
            CreateTime.setAccessible(true);
            FromUserName.setAccessible(true);

            CreateTime.set(oms, new Date().getTime());
            ToUserName.set(oms, msg.getFromUserName());
            FromUserName.set(oms, msg.getToUserName());
        }
    }

	public static String getApiTicket(String accesstoken) {
		try {
			String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=wx_card&access_token="+accesstoken;
			String jsonStr = HttpClient.get(url);
			System.out.println("=================================getApiTicket="+jsonStr);
		    Map<String, Object> map = JSONObject.parseObject(jsonStr);
		    return map.get("ticket").toString();
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
	}

	public static String QUERY_STATE_URL="http://www.emi365.cn/emipay/pos/queryPayState.do";

	/**
	 * 2支付成功 1支付失败 0查询失败
	 * @return
	 */
	public static int  queryPayState(String tradeno){
		Map<String,Object> retMap=new HashMap<String,Object>();
		retMap.put("deviceno", DEVICENO);
		Map<String,Object> jsonMap=new HashMap<String,Object>();
		jsonMap.put("orderno", tradeno);//支付的tradeno，纯积分支付无值
		jsonMap.put("userid", userid);
		String json= Object2Json.bean2Json2(jsonMap);
		String sign =null;
		try {
			sign = RSAUtil.sign(json, privateKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		retMap.put("json",json);
		retMap.put("sign", sign);
		Map<String,Object> rm=postUrl(QUERY_STATE_URL,Object2Json.bean2Json2(retMap));
		if(rm!=null){
			if("1.0".equals(String.valueOf(rm.get("success")))||"1".equals(String.valueOf(rm.get("success")))){
				if(rm.get("paystate")!=null){
					if("1.0".equals(String.valueOf(rm.get("paystate")))||"1".equals(String.valueOf(rm.get("paystate")))){
						return 2;
					}else{
						return 1;
					}

				}
			}
		}
		return 0;
	}


	public static Map<String,Object> signByTicket(String ticket,String url){
		return Sign.sign(ticket, url);
	}




    
    
    
}

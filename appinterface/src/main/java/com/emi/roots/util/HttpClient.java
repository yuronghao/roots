package com.emi.roots.util;


import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * https 请求 微信为https的请求
 * Created by zhaoyf on 2014/12/23.
 */
public class HttpClient {
	private static final String DEFAULT_CHARSET = "UTF-8";
    /**
     * @return 返回类型:
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.prepareGet(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addQueryParameter(key, params.get(key));
            }
        }

        if (headers != null && !headers.isEmpty()) {
            Set<String> keys = headers.keySet();
            for (String key : keys) {
                builder.addHeader(key, params.get(key));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }

    /**
     * @return 返回类型:
     * @throws IOException
     * @throws UnsupportedEncodingException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: get 请求
     */
    public static String get(String url) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, null);
    }

    /**
     * @return 返回类型:
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws UnsupportedEncodingException
     * @description 功能描述: get 请求
     */
    public static String get(String url, Map<String, String> params) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, UnsupportedEncodingException, IOException, ExecutionException, InterruptedException {
        return get(url, params, null);
    }

    /**
     * @return 返回类型:
     * @throws IOException
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @description 功能描述: POST 请求
     */
    public static String post(String url, Map<String, Object> params) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        if (params != null && !params.isEmpty()) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                builder.addParameter(key, String.valueOf(params.get(key)));
            }
        }
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }





    public static String post(String url, String s) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient http = new AsyncHttpClient();
        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
        builder.setBodyEncoding(DEFAULT_CHARSET);
        builder.setBody(s);
        Future<Response> f = builder.execute();
        String body = f.get().getResponseBody(DEFAULT_CHARSET);
        http.close();
        return body;
    }


//    public static String post(String url, String s,Immessageconf immessageconf) throws IOException, ExecutionException, InterruptedException {
//        AsyncHttpClient http = new AsyncHttpClient();
//        AsyncHttpClient.BoundRequestBuilder builder = http.preparePost(url);
//        builder.setBodyEncoding(DEFAULT_CHARSET);
//        builder.setBody(s);
//        builder.addHeader("Authorization","Bearer "+immessageconf.getAccesstoken());
//        Future<Response> f = builder.execute();
//        String body = f.get().getResponseBody(DEFAULT_CHARSET);
//        http.close();
//        return body;
//    }


//    public static String delete(String url, String s,Immessageconf immessageconf) throws IOException, ExecutionException, InterruptedException {
//        AsyncHttpClient http = new AsyncHttpClient();
//        AsyncHttpClient.BoundRequestBuilder builder = http.prepareDelete(url);
//        builder.setBodyEncoding(DEFAULT_CHARSET);
//        builder.setBody(s);
//        builder.addHeader("Authorization","Bearer "+immessageconf.getAccesstoken());
//        Future<Response> f = builder.execute();
//        String body = f.get().getResponseBody(DEFAULT_CHARSET);
//        http.close();
//        return body;
//    }
//
    
    
    

    
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    public static String getThree(){
		Random rad=new Random();
		return rad.nextInt(1000)+"";
	}
//    public static void main(String[] args){
//    	try {
//    		//System.out.println(MD5.MD5Encode("dino2016").toUpperCase());
//    		//http://localhost:8080/yy/appinterface/memberindex.do//www.emi365.com.cn
//    		//FzfP/4J2wyt//gWf7l6p4F+nCxAL+WaY6MA5pkyp4tUt8c+taHYoLVjzv//ONmqcvu5A64fADjwt8Kz6nYiXsuC845UvePm58uYBYDCf+8kkCTEloNtCqkL+JS/TC8QA13kB0eqJYRaywRLZwJ7pN/7aiRm+/W3I+YB0npXj8dg=
//    		/*Map<String,String> mm=new HashMap<String,String>();
//    		mm.put("json", "{\"price\":\"0.01\",\"paycode\":\"wechatonline\",\"consumerauthcode\":\"\",\"userid\":\"91\",\"orderno\":\"\"}");
//    		mm.put("deviceno", "HIFGGO9U");
//    		mm.put("openid", "oAbzrwz5d4XQXgW2aTVz20TSHbhw");
//    		mm.put("sign", "gH68nLanLsiZ9VLfq6No38bTBRLF2WO3UlEfnf1lL/H+Eot9fOLXgQ6c8VizwvdEcNWwxiiCLmRQ y1hAr5VnaOWGmvjWCODfGNyH2LrLwmJF45XgRrYoK5QDMFAhxLH6klx6/Lxgc0Q8mAMCQyhOedwx Ba3IcvdAdb+O0Vp/X5A=");
//    		String ss=HttpClient.post("http://139.224.34.242:8080/webmain/pos/addOnlineOrder.do", mm);*/
//    		///String ss=HttpClient.post("http://139.224.34.242:8080/webmain/pos/queryPayState.do", "{\"json\":\"{\\\"orderno\\\":\\\"NO201606150021\\\",\\\"userid\\\":\\\"91\\\"}\",\"deviceno\":\"C849UPNC\",\"sign\":\"en6gPuPgUrz66FLW1ibjZrpSqbbzljmzMqYkxq9tRY0gSzXgDwf3yOz/FB7nPH6m/mclSYqsoz940K50FCjDPISg3N3o2DUHDhy8dIThkm78/PQACTEQHrUImlEWCnNtr7hWnm7ev/kUSpEivQofqCQfdg3k5SiA/7nXcU7doYA=\"}");
//    		
//    		
///*    		mm.put("json", "{\"price\":0.01,\"paycode\":\"wechatonline\",\"consumerauthcode\":\"\",\"userid\":91,\"orderno\":\"\",\"openid\":\"oAbzrwz5d4XQXgW2aTVz20TSHbhw\"}");
//    		mm.put("deviceno", "HIFGGO9U");
//    		mm.put("openid", "oAbzrwz5d4XQXgW2aTVz20TSHbhw");
//    		mm.put("sign", "WHDKTrn4II3FujligFQZJeKZNXgh3V9fVqSpQSp8G9uoaCFfvAKglmv6CqGjXf9c9TU3bp8PcE/g80nETIJqegWr/OAMeZcEjuF71Sez1vb5szokKDqH+nexqV4v+WQYgNXuBU5OUy1SLUJSL6ibp8Vy37+Gl5ph3l0lLEL5VyQ=");
//*/    		
//    		//String ss=HttpClient.post("http://139.224.34.242:8080/webmain/pos/addOnlineOrder.do", "{\"json\":\"{\\\"price\\\":0.01,\\\"paycode\\\":\\\"wechatonline\\\",\\\"consumerauthcode\\\":\\\"\\\",\\\"userid\\\":91,\\\"orderno\\\":\\\"\\\",\\\"openid\\\":\\\"oAbzrwz5d4XQXgW2aTVz20TSHbhw\\\"}\",\"openid\":\"oAbzrwz5d4XQXgW2aTVz20TSHbhw\",\"deviceno\":\"C849UPNC\",\"sign\":\"WHDKTrn4II3FujligFQZJeKZNXgh3V9fVqSpQSp8G9uoaCFfvAKglmv6CqGjXf9c9TU3bp8PcE/g80nETIJqegWr/OAMeZcEjuF71Sez1vb5szokKDqH+nexqV4v+WQYgNXuBU5OUy1SLUJSL6ibp8Vy37+Gl5ph3l0lLEL5VyQ=\"}");
//			//Map<String,Object> map=Object2Json.getKeyJsonMap(ss,new TypeToken<Map<String,Object>>(){}.getType());
//			//String ss=HttpClient.post("http://www.emi365.com.cn/emall/mposinterface/getGoods.do", "{\"userid\":\"292\",\"deviceno\":\"00:0e:c6:fd:96:8d\",\"navid\":129}");
//			//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx43b0f4e0bc3299d0&redirect_uri=http%3A%2F%2Fpay.fyitgroup.com%3A8080%2Fwebmain%2Fpos%2FgetOpenid.do&response_type=code&scope=snsapi_base&state=a#wechat_redirect
//			//String param="{\"sign\":\"QssUfrFLKo63+cEicxmg11iS+yIQ6xfVho6j65g13jcd\\/\\/zdBdzlyQ3eHQLW8zbFp90XphxdCGJQ0W4OMirVHotx3u6oWhFW12017KUj3EK2hMW7IYfFDycPhRKmtpqt9yg6XP7VF34O8adZrwN0e2qVHj2Y1efLQtN16\\/si+IQ=\",\"deviceno\":\"b4:29:3d:00:19:c5\",\"json\":\"{\\\"userid\\\":73}\"}";
//    		//String ss=HttpClient.post("http://139.224.34.242:8080/webmain/pos/getAccountHistory.do", param);
//    		//String param="{\"json\":\"{\\\"userid\\\":95,\\\"orderno\\\":\\\"NO201606270010\\\",\\\"deviceno\\\":\\\"00:1d:fa:7c:b4:c1\\\"}\",\"sign\":\"CtxZU\\/nISdL7HLmbF9C0HxEPWXdFYMJ66s8\\/1QUN+Pg2dKqsOY9kLHLwtp0+Cd0EagCoi1SYDMoT7PPjLSZRn7qDpb5HYvowYy687uBgv2nOAobynxLB4w3NwKh7x4Xz7pEcapFgkUeyCkG56bgw43+yAAK95wdXHO\\/LiaCyKZI=\",\"deviceno\":\"00:1d:fa:7c:b4:c1\"}";
//    		//String param="{\"json\":\"{\\\"username\\\":\\\"teststore\\\",\\\"password\\\":\\\"96E79218965EB72C92A549DD5A330112\\\",\\\"deviceno\\\":\\\"00:1d:fa:7c:b4:c1\\\"}\",\"sign\":\"bfqkUuXOa6KzD6s9TU\\/EehipiY27d7boAJv7Biz1I2fP2iPeO4uakbhpIYiW+rS579KFtqWlyQ24KmpRBq+WO4neOz5IBeE9C5JhV\\/yDeFKkzfy7cvzsFxTC048IyEwxyNXTdVZWVjBXOoauNq5\\/OFNpUH6sbC8Kqk50mHnElgg=\",\"deviceno\":\"00:1d:fa:7c:b4:c1\"}";
//    		//String ss=HttpClient.post("http://localhost:8080/webmain/pos/login.do", param);
//    		//System.out.println("------------>>>"+ss);
//    		//System.out.println(getThree());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }

    public static void main(String[] args) {
    	String url="https://a1.easemob.com/henrychu/testchat/users";
    	try{
            String params="{\"username\":\"service27\",\"password\":\"123456\"}";
            String res=post(url,params);
            System.out.println(res);
    	}catch(Exception e){
    		e.printStackTrace();
    	}

	}
    
    
}
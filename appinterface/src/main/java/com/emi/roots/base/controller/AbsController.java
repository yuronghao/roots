/**
 * 2015-4-12 
 * AbsController.java 
 * author:赵永飞
 */
package com.emi.roots.base.controller;


import com.emi.page.PageInfo;
import com.emi.page.PagerHelper;
import com.emi.roots.util.Object2Json;
import com.emi.roots.util.PropertyHolder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaoyf
 * 
 */
@Controller
public abstract class AbsController {
	Logger logger = Logger.getLogger(AbsController.class);
	@Autowired  
	public HttpServletRequest request;

	public static int THIS_PAGE_SIEZ=10;
	
	
	
	/**
	 * @author yurh
	 * @description  MD5加密
	 * @param str
	 * @return
	 * @throws Exception
	 * @update 2016年5月19日 下午3:36:41
	 */
	public static String md5Encode(String str) throws Exception{
		
		MessageDigest md5 = null;
		 try {
	            md5 = MessageDigest.getInstance("MD5");
	        } catch (Exception e) {
	            System.out.println(e.toString());
	            e.printStackTrace();
	            return "";
	        }
		 byte[] byteArray = str.getBytes("UTF-8");
	        byte[] md5Bytes = md5.digest(byteArray);
	        StringBuffer hexValue = new StringBuffer();
	        for (int i = 0; i < md5Bytes.length; i++) {
	            int val = ((int) md5Bytes[i]) & 0xff;
	            if (val < 16) {
	                hexValue.append("0");
	            }
	            hexValue.append(Integer.toHexString(val));
	        }
	        String result =  hexValue.toString();
	      return   result.toUpperCase();//大写
	}
	
	
	

	
	
	
	/*@Autowired  
	private  HttpServletResponse  response;*/
	/**
	 * 统一给前端的响应
	 * 
	 * @param msg
	 */
	public void responseMsg(HttpServletResponse response, String msg) {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out =response.getWriter();
			out.write(msg);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	
	/**
	 * 获取openid
	 * @return
	 */
	public String getOpenid(){
		String openid=request.getParameter("openid");
		if(openid!=null){
			request.setAttribute("openid", openid);
			return openid;
		}
		return null;
	}
	
//	public User getUser(){
//		User user = (User) getSession().getAttribute(PropertyHolder.getProperty("USERBEAN-KEY"));
//		if(user==null) return null;
//		return user;
//	}


//	public Member getMember(){
//		Member member = (Member) getSession().getAttribute(PropertyHolder.getProperty("MEMBER-KEY"));
//		if(member==null) return null;
//		return member;
//	}
//	public void setMemberSession(Member member){
//		getSession().setAttribute(PropertyHolder.getProperty("MEMBER-KEY"), member);
//	}


	public void printError(HttpServletResponse  response,String msg){
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out =response.getWriter();
			if(msg!=null&&!msg.equals("")){
				Map m=new HashMap();
				m.put("success", 0);
				m.put("msg", msg);
				Gson gson = new Gson();
				out.write(gson.toJson(m));
			}else{
				Map m=new HashMap();
				m.put("success", 0);
				m.put("msg", "网络不稳定");
				Gson gson = new Gson();
				out.write(gson.toJson(m));
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
		
	}


	/**
	 * 获取HttpServletResponse对象
	 * 
	 * @return HttpServletResponse
	 */
	public HttpServletResponse getResponse() throws ClassCastException {
		HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder
				.getRequestAttributes()).getResponse();
		return resp;
	}

	/**
	 * 获取HttpServletRequest对象
	 * 
	 * @return HttpServletRequest
	 *//*
	public HttpServletRequest getRequest() {
		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}
*/
	/**
	 * 获取HttpSession对象
	 * 
	 * @return HttpSession
	 */
	public HttpSession getSession() {
		return request.getSession();
	}
	/**
	 * 按菜单路径查询操作列表
	 * @param url
	 * @return
	 */
//	public List<FunctionOperation> getOperationList(String url){
//		RFunction rfunction=(RFunction)getSession().getAttribute(PropertyHolder.getProperty("USERBEAN-FUNCTION"));
//		if(rfunction!=null){
//			Function function=rfunction.getFunction();
//			if(function!=null&&function.getUrl()!=null&&function.getUrl().equals(url)){
//				return rfunction.getOperationList();
//			}else{
//				return findOperation(rfunction,url);
//			}
//		}
//		return new ArrayList<FunctionOperation>();
//	}
	/**
	 * 递归算法
	 * @param
	 * @param
	 * @return
	 */
//	private List<FunctionOperation> findOperation(RFunction rfunction,String url){
//		List<FunctionOperation> retList=new ArrayList<FunctionOperation>();
//		if(rfunction!=null){
//			for(RFunction bean:rfunction.getFunctionList()){
//				Function function=bean.getFunction();
//				if(function!=null&&function.getUrl()!=null&&function.getUrl().equals(url)){
//					retList= bean.getOperationList();
//					return retList;
//				}
//				retList=findOperation(bean,url);
//				if(retList.size()>0)break;
//			}
//		}
//		return retList;
//	}
//

	public PageInfo getPageInfo(){
		PageInfo page = PagerHelper.getPageInfo(request, THIS_PAGE_SIEZ);
		return page;
	}
	public PageInfo getPageInfo(int pagesize){
		PageInfo page = PagerHelper.getPageInfo(request, pagesize);
		return page;
	}
	@SuppressWarnings("rawtypes")
//	public void dataList(List  dataList,String urloperation,String param,PageInfo page){
//		//操作数据
//		List<FunctionOperation> operation=this.getOperationList(urloperation);
//		request.setAttribute("firstPage",Object2Json.bean2Json2(dataList, page));
//		logger.info(Object2Json.bean2Json2(operation));
//		request.setAttribute("operation",Object2Json.bean2Json2(operation));
//		if(param!=null){
//			request.setAttribute("queryparam",param);
//		}
//	}
	/**
	 * 获取session用户
	 * @return
	 */
	/*public User getUser(){
		User user = (User) getSession().getAttribute(
				PropertyHolder.getProperty("USERBEAN-KEY"));
		if(user==null)return null;
		return user;
	}*/
	
	
	/**
	 * 统一给前端的响应
	 * @param msg
	 */
	public void responseMsg(String msg){
		PrintWriter out = null;
		try{
			out = getResponse().getWriter();
			out.write(msg);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	public Map<String,Object> getJsonObjectMap(){
		StringBuffer sb = new StringBuffer();
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(this.request.getInputStream(),"utf-8"));
			String temp;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		///System.out.println("==result=="+result);
		logger.info("result:"+result);
		Map<String,Object> map= Object2Json.getKeyJsonMap(result,new TypeToken<Map<String,Object>>(){}.getType());
		return map;
	}
	
	/**
	 * 获取访问用户IP
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for");    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("WL-Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getRemoteAddr();    
	    }    
	    return ip;    
	}
	/**
	 * 获取访问用户IP
	 * @param
	 * @return
	 */
	public String getIpAddr() { 
	    String ip = request.getHeader("x-forwarded-for");    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getHeader("WL-Proxy-Client-IP");    
	    }    
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {    
	        ip = request.getRemoteAddr();    
	    }    
	    return ip;    
	}
	/**
	 * 获取请求定制路径
	 * @return
	 */
	public String getRequeUrI(){
		String url=request.getScheme()+"://";   
		url+=request.getHeader("host");   
		url+=request.getRequestURI();   
		if(request.getQueryString()!=null)   
		    url+="?"+request.getQueryString(); 
		return url;
	}
	
	
	
	public JSONObject getJsonObject(){
		return getJsonObject(true);
	}
	
	public JSONArray getJsonArray(){
		return getJsonArray(true);
	}
	
	/**
	 * @category 流转jsonObject
	 * @author zhuxiaochen
	 * @param printJson
	 * @return
	 */
	public JSONObject getJsonObject(boolean printJson){
		StringBuffer sb = new StringBuffer();
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
			String temp;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			if(printJson){
//				System.out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	
	/**
	 * @category 流转jsonArray
	 * @author zhuxiaochen
	 * @param printJson
	 * @return
	 */
	public JSONArray getJsonArray(boolean printJson){
		StringBuffer sb = new StringBuffer();
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
			String temp;
			while((temp=br.readLine())!=null){
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			if(printJson){
				System.out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonA = JSONArray.fromObject(result);
		return jsonA;
	}



	
	
}

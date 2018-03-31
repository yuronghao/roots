package com.emi.roots.util;

import com.emi.page.PageInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Object2Json {
	private static Gson gson = null;
	private static Gson gsonForMap =null;
	static
	{
//		gson = new GsonBuilder().disableHtmlEscaping().create();
		 GsonBuilder gb1 = new GsonBuilder();
		 gb1.setDateFormat("yyyy-MM-dd HH:mm:ss");
		 gson=gb1.create();
		 GsonBuilder gb = new GsonBuilder();
		 gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
	     gsonForMap = gb.create();
	}
	public static String bean2Json2(Object obj) {
		return gson.toJson(obj);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String bean2JsonApp(Object obj,PageInfo page,String dateType) {
		Map tobj=new HashMap();
		if(obj instanceof Map){		//用instanceof判断
			 tobj=(Map)obj;
		}
		tobj.put("page", page);
		gson=new GsonBuilder().setDateFormat(dateType).create(); 
		 return gson.toJson(tobj);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String bean2JsonApp2(Object obj,String dateType) {
		Map tobj=new HashMap();
		if(obj instanceof Map){		//用instanceof判断
			 tobj=(Map)obj;
		}
		gson=new GsonBuilder().setDateFormat(dateType).create(); 
		 return gson.toJson(tobj);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String bean2Json2(Object obj,PageInfo page) {
		HashMap temp = new HashMap ();
		temp.put("dataList", obj);
		temp.put("page", page);
		gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
		 return gson.toJson(temp);
	}
	public static Object getKeyJson(String json,Type type){
		return gsonForMap.fromJson(json,type);
	}
	public static void main(String[] args){
		/*String ss="{\"userid\":8,\"sdate\":\"2016-05-13\",\"edate\":\"2016-05-13\",\"currentpage\":0}";
		Map mm=gsonForMap.fromJson(ss,new TypeToken<Map<String,Object>>(){}.getType());
		System.out.println(mm.get("userid"));*/
		getKeyJsonMap(null,null);
	/*	UserInfo  a = new UserInfo();
		ArrayList t = new ArrayList();
		t.add(a);
		a.setDn("1111");
		a.setOrgsIds("fffffff");
		PageInfo p = new PageInfo();
		p.setCurrentPage(111);*/
		
		//Object u = getKeyJson("{\"id\":3279,\"name\":\"陈翔宇\",\"type\":1,\"sex\":\"保密\",\"remote_id\":1000083632,\"province_add\":\"JS\",\"nickname\":\"Snake\",\"profile_url\":\"http://127.0.0.1:9000/avatar/3279/middle\",\"login_name\":\"JS_chenxy\",\"dn\":\"13813933298\",\"clazz\":[{\"id\":1008,\"name\":\"新产品研发部\",\"remote_id\":1008,\"grade_name\":\"技术部\",\"school\":{\"id\":1000,\"name\":\"信通科技\",\"remote_id\":1000}}]}",new TypeToken<UserInfo>(){}.getType());
//		System.out.println(u);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String bean2Json3(Object obj,PageInfo page,Object elseData) {
		HashMap temp = new HashMap ();
		temp.put("dataList", obj);
		temp.put("page", page);
		temp.put("elseData", elseData);
		 return gson.toJson(temp);
	}
	public static Map<String,Object> getKeyJsonMap(String json,Type type){
		return gsonForMap.fromJson(json,type);
	}
}
package com.emi.page;

import com.emi.roots.util.StringUtil;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;


public class PagerHelper{
	/*public static Pager getPager(HttpServletRequest httpServletRequest,int totalRows) {//定义pager对象，用于传到页面
	Pager pager = new Pager(totalRows);//从Request对象中获取当前页号
	String currentPage = httpServletRequest.getParameter("currentPage");//若是当前页号为空，默示为初次查询该页//若是不为空，则刷新pager对象，输入当前页号等信息
	if (currentPage != null) {
		//pager.refresh(Integer.parseInt(currentPage));
	}//获取当前履行的办法，首页，前一页，后一页，尾页。
	String pagerMethod = httpServletRequest.getParameter("pageMethod");
	if (pagerMethod != null) {
		if (pagerMethod.equals("first")) {pager.first();
	} else if (pagerMethod.equals("previous")) {
	  pager.previous();
	} else if (pagerMethod.equals("next")) {
	  pager.next();
	} else if (pagerMethod.equals("last")) {
	  pager.last();}
	}
		return pager;
	}*/
	
	public static PageInfo getPageInfo(HttpServletRequest httpServletRequest, int pagesize) {//定义pager对象，用于传到页面
		PageInfo page = new PageInfo();//从Request对象中获取当前页号
		page.setShowCount(pagesize);
		String currentPage = httpServletRequest.getParameter("currentPage");//若是当前页号为空，默示为初次查询该页//若是不为空，则刷新pager对象，输入当前页号等信息
		String totalPage=httpServletRequest.getParameter("totalPage");
		if (currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));
		}//获取当前履行的办法，首页，前一页，后一页，尾页。
		if (currentPage != null) {
			page.setTotalPage(Integer.parseInt(totalPage));
		}
		String pagerMethod = httpServletRequest.getParameter("pageMethod");
		if (pagerMethod != null) {
			if (pagerMethod.equals("first")) {
				page.first();
			} else if (pagerMethod.equals("previous")) {
				page.previous();
			} else if (pagerMethod.equals("next")) {
				page.next();
			} else if (pagerMethod.equals("last")) {
				page.last();
			}else if (pagerMethod.equals("go")) {
				page.go();
			}
			else  {
				page.first();
			} 
		}
		else  {
			page.first();
		} 
			return page;
		}
	
	public static PageInfo getPageInfo(int pagesize, String currentPage) {//定义pager对象，用于传到页面
		PageInfo page = new PageInfo();//从Request对象中获取当前页号
		page.setShowCount(pagesize);
		if (currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage)-1);
		}//获取当前履行的办法，首页，前一页，后一页，尾页。
			page.next();
			return page;
		}
	
	
	public static PageInfo getPageInfo(int pagesize, JSONObject jobj) {//定义pager对象，用于传到页面
		String j= StringUtil.parseString(jobj.get("json"));
		JSONObject dataMap=JSONObject.fromObject(j);
		
		String pageIndex=dataMap.getString("pageIndex");//页数
		
		PageInfo page=new PageInfo();
		if(pageIndex==null)
		{
			page.setCurrentPage(1);
			page.setCurrentResult(0);
		}else{
			page.setCurrentPage(Integer.parseInt(pageIndex));
			page.setCurrentResult((Integer.parseInt(pageIndex) - 1)*pagesize);
		}
		page.setShowCount(pagesize);
		
		return page;
	}
	
	
	
	
}




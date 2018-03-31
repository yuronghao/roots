/**
 * 2015-5-18 
 * SysUtilServlet.java 
 * author:赵永飞
 */
package com.emi.roots.util;

import com.emi.roots.base.service.BaseService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


/**
 * @author zhaoyf
 *
 */
public class SysUtilServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4310031636923679132L;
	Logger logger = Logger.getLogger(SysUtilServlet.class);

	public void init() throws ServletException {
		BaseService baseService = SpringContextUtils.getBean("baseService");
//		baseService.initAttriBute();
//		logger.info("载入所有属性成功");
////		baseService.loadArea();
//		logger.info("载入地区数据成功");
////		baseService.initGoodsNav();
//		logger.info("初始化商品类目");
////		baseService.initEnumerate();
//		logger.info("初始化枚举表");


		new Thread(new TokenThread()).start();
		logger.info("微信accesstoken定时更新");

	}
}

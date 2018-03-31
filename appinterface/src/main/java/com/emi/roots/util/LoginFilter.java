package com.emi.roots.util;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class LoginFilter implements Filter {
	Vector<String> nonfilter = new Vector<String>();
	Vector<String> canfilter = new Vector<String>();
	private static String contextPath = null;
	Logger logger = Logger.getLogger(LoginFilter.class);
	private static boolean hasContextPath = false;

	private String forwardPage;
	
	private String webname;

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		String[] notFilter = config.getInitParameter("ignorePages").split(",");
		for (int i = 0; i < notFilter.length; i++) {
			nonfilter.add(notFilter[i]);
		}
		forwardPage = config.getInitParameter("loginPage");
		String[] canFilter = config.getInitParameter("ignoreExtensions").split(
				",");
		for (int i = 0; i < canFilter.length; i++) {
			canfilter.add(canFilter[i]);
		}
		webname=config.getInitParameter("webname");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest srequest, ServletResponse sresponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) srequest;
		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		
		
		logger.info("uri=>" + uri);
		if (hasContextPath(request)) {
			uri = uri.substring(request.getContextPath().length());
		} else {
			if (null != uri && uri.indexOf(webname) == 0) {
				uri = uri.replace(webname, "/");
			}
		}
		
		if(uri.endsWith("ws")){//websocket请求
			chain.doFilter(srequest, sresponse);
		}else if (!ifLogTime(uri)) {
			if (infiltermap(uri)) {
				if (session != null) {
					if (session.getAttribute(PropertyHolder.getProperty("USERBEAN-KEY")) != null) {



					} else {
						logger.info("Session用户，跳至登陆页面！");
						RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
						rd.forward(srequest, sresponse);
						return;
					}
				} else {
					logger.info("无Session，跳至登陆页面！");
					RequestDispatcher rd = request.getRequestDispatcher(forwardPage);
					rd.forward(srequest, sresponse);
					return;
				}
			}
		}
		
		
		chain.doFilter(srequest, sresponse);
	}

	public boolean hasContextPath(HttpServletRequest request) {
		if (contextPath == null) {
			contextPath = request.getContextPath();
			hasContextPath = contextPath.length() > 0;
		}
		return hasContextPath;
	}

	private boolean infiltermap(String uri) {
		if ("/".equals(uri) || "".equals(uri)) {
			return false;
		}
		for(String tmp:nonfilter){
			if(tmp.equals(uri))return false;
			if(tmp.endsWith("*")){
				String rep=tmp.replace("*", "");
				if(uri.startsWith(rep)&&(rep.endsWith("/")||rep.endsWith("."))){
					return false;
				}
			}
		}
		return true;
		//return !nonfilter.contains(uri);
	}

	/*private boolean endwith(String uri) {
		if ("/".equals(uri) || "".equals(uri)) {
			return false;
		}
		return !canfilter.contains(uri);
	}
*/
	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {

	}

	private static boolean ifLogTime(String uri) {
		if (null != uri && !uri.endsWith(".png") && !uri.endsWith(".js")
				&& !uri.endsWith(".jpg") && !uri.endsWith(".gif")
				&& !uri.endsWith(".css")) {
			return false;
		}
		return true;

	}
}


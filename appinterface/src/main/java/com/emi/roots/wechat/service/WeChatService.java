/**
 * 2015-4-27 
 * WeChatService.java 
 * author:赵永飞
 */
package com.emi.roots.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author zhaoyf
 *
 */
public interface WeChatService {
	public boolean checkSignature(HttpServletRequest request);
	/**
	 * 处理用户请求
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String processPostRequest(HttpServletRequest request)
			throws Exception;
	public void reponseToUser(HttpServletResponse response, String responseStr);

	void updateUnionIdByopenid();
}

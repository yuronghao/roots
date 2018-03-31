/**
 * 2015-4-27 
 * WeChatServiceImpl.java 
 * author:赵永飞
 */
package com.emi.roots.wechat.service;


import com.emi.roots.base.service.BaseService;
import com.emi.roots.mapper.SysMapper;
import com.emi.roots.wechat.bean.ResponseObject;
import com.emi.roots.wechat.bean.WeChatConf;
import com.emi.roots.wechat.util.Common;
import com.emi.roots.wechat.util.WeChat;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * @author zhaoyf
 *
 */
@Service("weChatService")
public class WeChatServiceImpl extends BaseService implements WeChatService {
	Logger logger = Logger.getLogger(WeChatServiceImpl.class);
	@Autowired
	private SysMapper sysMapper;
	/*@Autowired
	 private MallMapper mallMapper;*/
	/**
	 * 签名验证
	 * 
	 * @param request
	 * @return
	 */
	public boolean checkSignature(HttpServletRequest request) {
		System.out.println("=============222===="+request);
		if (request == null)
			return false;
		String signature = request.getParameter("signature");// 微信加密签名
		String timestamp = request.getParameter("timestamp");// 时间戳
		String nonce = request.getParameter("nonce");// 随机数
		String echostr = request.getParameter("echostr");// 随机数
		
		WeChatConf weChatConf=sysMapper.getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
		String token = weChatConf.getWxtoken();
		System.out.println(signature + "=timestamp=" + timestamp + "=nonce=" + nonce + "=echostr="
				+ echostr + "=token=" + token);
		// LOG.info("signature:{}; timestamp:{};  nonce:{}; echostr:{}; token:{}",
		// signature, timestamp, nonce,echostr, token);
		// 校验规则：
		// 1. 将token、timestamp、nonce三个参数进行字典序排序
		// 2. 将三个参数字符串拼接成一个字符串进行sha1加密
		// 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (token != null && signature != null && nonce != null
				&& timestamp != null) {
			if (WeChat.checkSignature(token, signature, timestamp, nonce)) {// 验证通过
				return true;
			}
		}
		// 验证失败
		return false;
	}
	/**
	 * 处理用户请求
	 */
	public String processPostRequest(HttpServletRequest request)
			throws Exception{

		if (request == null)
			return "";
		ServletInputStream inputStream = request.getInputStream();
		String xmlstr = Common.streamtoStr(inputStream);
		inputStream.close();
		String nonce = request.getParameter("nonce");// 随机数
		String msg_signature = request.getParameter("msg_signature");
		String timestamp = request.getParameter("timestamp");
		System.out.println(nonce+"=nonce======msg_signature========"+msg_signature);
		//if (msg_signature == null || timestamp == null)//测试账户是明文暂时取消这一行20161006
		//	return null;
		logger.info("请求密文:{}"+xmlstr);
		WeChatConf weChatConf=sysMapper.getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
		System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"+weChatConf.getAccesstoken()+"==="+weChatConf.getEncodingaeskey()+"==="+weChatConf.getWxappid());
		/*String plaintext = Common.decryption(xmlstr, nonce, msg_signature,//测试账户是明文暂时取消这一行20161006
				timestamp,weChatConf);*/
		String plaintext=xmlstr;
		ResponseObject reObject = WeChat.processing(plaintext, weChatConf);
		String ciphertext = "";
		if (reObject == null)
			return ciphertext;
		if (reObject.getType() == null) {
			/*ciphertext = Common.encryption(
					String.valueOf(reObject.getObject()), nonce,weChatConf);*/
			ciphertext=String.valueOf(reObject.getObject());
			logger.info("响应密文：{}"+ciphertext);
		}
		return ciphertext;
	
	}
	/**
	 * 给用户的响应
	 * 
	 * @param response
	 * @param responseStr
	 */
	@SuppressWarnings("unused")
	public void reponseToUser(HttpServletResponse response, String responseStr) {
		if (response != null && responseStr != null) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.write(responseStr);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
				if (out != null) {
					out.close();
				}
			}
		}
	}




	public void updateUnionIdByopenid(){
//		WeChatConf weChatConf=sysMapper.getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
		WeChatConf weChatConf = new WeChatConf();
		weChatConf.setWxappid("wx4fc91f327c7fea6a");
		weChatConf.setWxsecret("e8976cf7e707bd29e35ee11e18f5dca9");

//		List<Member> memberList = sysMapper.getMemberList();
		try {
			String accesstoken = WeChat.getAccessToken(weChatConf);
//			for(int i = 0 ;i<memberList.size();i++){
//				Member member = memberList.get(i);
////				UserInfo userInfo = WeChat.getUserInfo(accesstoken,member.getOpenid());
////				sysMapper.updateMemberListUnionidByMemberid(member.getId(),userInfo.getUnionid());
//			}
//			System.out.println("oVw2M0teqWT4dI7SUtt4UGSCcR6c");
//			System.out.println(userInfo.getUnionid());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}






}

package com.emi.roots.util;

import com.emi.roots.base.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by zhaoyf on 2014/12/30.
 */
public class TokenThread implements Runnable{
	 public  static final Logger LOG = LoggerFactory.getLogger(TokenThread.class);
	// 第三方用户唯一凭证
    public static String appid = "";
    // 第三方用户唯一凭证密钥
    public static String appsecret = "";
    public static AccessToken accessToken = null;

    public void run() {
        while (true) {
            try {
                	BaseService baseService = SpringContextUtils.getBean("baseService");
                	baseService.updateAccesstokenAndJSDKTicket();
                    LOG.info("获取access_token,JSDKTicket成功，有效时长{}秒 ", 7200);
                    // 休眠7000秒
                    Thread.sleep(7000 * 1000);
            } catch (InterruptedException e) {
                try {
                    Thread.sleep(60 * 1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
    }
}

package com.emi.roots.util;

public class PayUtil {
	public static int WECHATPYID=1;//微信支付
	public static int ALIPAYID=2;//支付宝支付
	public static int WECHAT5=3;//H5支付
	public static int BALANCE=4;//余额支付

	
	public static int PAY_SUCCESS=2;//支付成功
	public static int PAY_FAILURE=3;//支付失败

	public static String PAYACTIVITY="activity";//活动支付
	public static String PAYRECHARGE="recharge";//充值支付

	public static String PLEDGEWECHAT ="pledgewechat"; //申请vip/私董定金微信支付-公众号
	public static String PLEDGEAPP ="pledgeapp"; //申请vip/私董定金微信支付-app支付
}

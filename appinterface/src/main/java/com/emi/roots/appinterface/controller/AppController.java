package com.emi.roots.appinterface.controller;


import com.emi.roots.appinterface.service.AppService;
import com.emi.roots.base.controller.AbsController;
import com.emi.roots.util.*;
import com.emi.roots.wechat.bean.UserInfo;
import com.emi.roots.wechat.bean.WeChatConf;
import com.emi.roots.wechat.service.WechatBaseService;
import com.emi.roots.wechat.util.WeChat;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by yuronghao on 2017/8/14.
 */
@Controller
@RequestMapping("/mobile")
public class AppController extends AbsController {
    public static final Logger LOG = LoggerFactory.getLogger(AppController.class);


    @Autowired
    private AppService appService;

    @Autowired
    private WechatBaseService wechatBaseService;


    /**
     * 登录
     */
    @RequestMapping("/login")
    public void login(HttpServletResponse response){
        LOG.info("普通登陆");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.login(jobj);
                this.responseMsg(response,ret);
            }
        }catch (Exception e){
            e.printStackTrace();
            retmap.put("success", 0);
            retmap.put("msg","系统异常");
            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
            this.responseMsg(response,jsonObject.toString());

        }

    }


//    /**
//     * 关注服务
//     * @param response
//     */
//    @RequestMapping("/followMemservice")
//    public void followMemservice(HttpServletResponse response){
//
//        LOG.info("关注服务");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.followMemservice(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获取新闻播报
//     * @param response
//     */
//    @RequestMapping("/getNewsCast")
//    public void getNewsCast(HttpServletResponse response){
//        LOG.info("获取新闻播报");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getNewsCast(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//           this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//
//
//    /**
//     * 获取行业资讯列表
//     * @param response
//     */
//    @RequestMapping("/getIndustryNews")
//    public void getIndustryNews(HttpServletResponse response){
//        LOG.info("获取行业资讯列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getIndustryNews(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 首页广告图
//     * @param response
//     */
//    @RequestMapping("/getAdvertImg")
//    public void getAdvertImg(HttpServletResponse response){
//        LOG.info("首页广告图");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getAdvertImg(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 首页轮播图接口
//     * @param response
//     */
//    @RequestMapping("/getCarouselImgs")
//    public void getCarouselImgs(HttpServletResponse response){
//        LOG.info("首页轮播图接口");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getCarouselImgs(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得最近3条条快讯
//     * @param response
//     */
//    @RequestMapping("/getFlashnews")
//    public void getFlashnews(HttpServletResponse response){
//        LOG.info("获得最近3条条快讯");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getFlashnews(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 忘记密码
//     * @param response
//     */
//    @RequestMapping("/forgetPassword")
//    public void forgetPassword(HttpServletResponse response){
//        LOG.info("忘记密码");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.forgetPassword(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获取注册验证码
//     * @param response
//     */
//    @RequestMapping("/getPhonevalidatecode")
//    public void getPhonevalidatecode(HttpServletResponse response){
//        LOG.info("获取注册验证码");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String securityCode = Makemsgcode.Makemsgcode();
//                String ret=appService.getPhonevalidatecode(jobj,securityCode);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 注册
//     * @param response
//     */
//    @RequestMapping("/regist")
//    public void regist(HttpServletResponse response, HttpServletRequest request){
//        LOG.info("注册");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.regist(jobj,request);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 最强智库列表（捞取企业家导师）
//     * @param response
//     */
//    @RequestMapping("/getStrongest")
//    public void getStrongest(HttpServletResponse response){
//        LOG.info("最强智库列表（捞取企业家导师）");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getStrongest(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     *获得行业列表
//     * @param response
//     */
//    @RequestMapping("/getIndustyList")
//    public void getIndustyList(HttpServletResponse response){
//        LOG.info("获得行业列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getIndustyList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 点击服务评论获得对评论的回复
//     * @param response
//     */
//    @RequestMapping("/getMemserviceCommentDetail")
//    public void getMemserviceCommentDetail(HttpServletResponse response){
//        LOG.info("点击服务评论获得对评论的回复");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemserviceCommentDetail(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 对服务进行评论
//     * @param response
//     */
//    @RequestMapping("/replyMemservice")
//    public void replyMemservice(HttpServletResponse response){
//        LOG.info("对服务进行评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.replyMemservice(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得所有的人脉
//     * @param response
//     */
//    @RequestMapping("/getMemberList")
//    public void getMemberList(HttpServletResponse response){
//        LOG.info("获得所有的人脉");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemberList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 发布服务（后台要做条数限制控制，每月默认4条）
//     * @param response
//     */
//    @RequestMapping("/publishMemservice")
//    public void publishMemservice(HttpServletResponse response){
//        LOG.info("发布服务（后台要做条数限制控制，每月默认4条）");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
////                System.err.println(JSON.toJSONString(jobj));
//                String ret=appService.publishMemservice(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof UncategorizedSQLException) {
//                retmap.put("success", 0);
//                retmap.put("msg", "不允许输入特殊字符");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 获得职位列表
//     * @param response
//     */
//    @RequestMapping("/getPositionList")
//    public void getPositionList(HttpServletResponse response){
//        LOG.info("获得职位列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getPositionList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 完善账户
//     * @param response
//     */
//    @RequestMapping("/perfectAccount")
//    public void perfectAccount(HttpServletResponse response){
//        LOG.info("完善账户");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.perfectAccount(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得服务的详情
//     * @param response
//     */
//    @RequestMapping("/getMemserviceDetail")
//    public void getMemserviceDetail(HttpServletResponse response){
//        LOG.info("获得服务的详情");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemserviceDetail(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得最近的服务列表
//     * @param response
//     */
//    @RequestMapping("/getMemserviceList")
//    public void getMemserviceList(HttpServletResponse response){
//        LOG.info("获得最近的服务列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemserviceList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得快讯列表
//     * @param response
//     */
//    @RequestMapping("/getFlashnewsList")
//    public void getFlashnewsList(HttpServletResponse response){
//        LOG.info("获得快讯列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getFlashnewsList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得附近私董
//     * @param response
//     */
//    @RequestMapping("/getNearbyMember")
//    public void getNearbyMember(HttpServletResponse response){
//        LOG.info("获得附近私董");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getNearbyMember(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//
//    }
//
//
//    /**
//     * 取消关注服务接口
//     * @param response
//     */
//    @RequestMapping("/cancelFollowMemservice")
//    public void cancelFollowMemservice(HttpServletResponse response){
//        LOG.info("取消关注服务接口");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.cancelFollowMemservice(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 取消关注私董
//     * @param response
//     */
//    @RequestMapping("/cancelFollowMember")
//    public void cancelFollowMember(HttpServletResponse response){
//        LOG.info("取消关注私董");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.cancelFollowMember(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得当前私董的所有评论
//     * @param response
//     */
//    @RequestMapping("/getAllMemmouth")
//    public void getAllMemmouth(HttpServletResponse response){
//        LOG.info("获得当前私董的所有评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getAllMemmouth(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得预置的印象
//     * @param response
//     */
//    @RequestMapping("/getImpressionbaseList")
//    public void getImpressionbaseList(HttpServletResponse response){
//        LOG.info("获得预置的印象");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getImpressionbaseList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 增加印象
//     * @param response
//     */
//    @RequestMapping("/addMemimpression")
//    public void addMemimpression(HttpServletResponse response){
//        LOG.info("增加印象");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.addMemimpression(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof UncategorizedSQLException) {
//                retmap.put("success", 0);
//                retmap.put("msg", "不允许输入特殊字符");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 评论会员
//     * @param response
//     */
//    @RequestMapping("/commentMember")
//    public void commentMember(HttpServletResponse response){
//        LOG.info("评论会员");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.commentMember(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 会员点赞接口
//     * @param response
//     */
//    @RequestMapping("/agreeMember")
//    public void agreeMember(HttpServletResponse response){
//        LOG.info("会员点赞接口");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.agreeMember(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已点赞");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 获得私懂详细信息
//     * @param response
//     */
//    @RequestMapping("/getMemberDetail")
//    public void getMemberDetail(HttpServletResponse response){
//        LOG.info("获得私懂详细信息");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemberDetail(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 关注私董
//     * @param response
//     */
//    @RequestMapping("/followMember")
//    public void followMember(HttpServletResponse response){
//        LOG.info("关注私董");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.followMember(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已关注");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//        }
//
//    }
//
//
//    /**
//     * 获取活动列表
//     * @param response
//     */
//    @RequestMapping("/getActivityList")
//    public void getActivityList(HttpServletResponse response){
//        LOG.info("获取活动列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getActivityList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得我已加入活动列表
//     * @param response
//     */
//    @RequestMapping("/getMyJoinedActivityList")
//    public void getMyJoinedActivityList(HttpServletResponse response){
//        LOG.info("获得我已加入活动列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyJoinedActivityList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 增加话题评论
//     * @param response
//     */
//    @RequestMapping("/addTopicComment")
//    public void addTopicComment(HttpServletResponse response){
//        LOG.info("增加话题评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.addTopicComment(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得对话题评论的回复
//     * @param response
//     */
//    @RequestMapping("/replyCommentList")
//    public void replyCommentList(HttpServletResponse response){
//        LOG.info("获得对话题评论的回复");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.replyCommentList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得话题列表（需要根据权限获得）
//     * @param response
//     */
//    @RequestMapping("/getTopicList")
//    public void getTopicList(HttpServletResponse response){
//        LOG.info("获得话题列表（需要根据权限获得）");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getTopicList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得话题评论
//     * @param response
//     */
//    @RequestMapping("/getTopicCommentList")
//    public void getTopicCommentList(HttpServletResponse response){
//        LOG.info("获得话题评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getTopicCommentList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得话题详情
//     * @param response
//     */
//    @RequestMapping("/getTopicDetail")
//    public void getTopicDetail(HttpServletResponse response){
//        LOG.info("获得话题详情");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getTopicDetail(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 取消关注话题
//     * @param response
//     */
//    @RequestMapping("/cancelFollowTopic")
//    public void cancelFollowTopic(HttpServletResponse response){
//        LOG.info("取消关注话题");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.cancelFollowTopic(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 点赞话题评论
//     * @param response
//     */
//    @RequestMapping("/agreeTopiccomment")
//    public void agreeTopiccomment(HttpServletResponse response){
//        LOG.info("点赞话题评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.agreeTopiccomment(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已点赞");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//        }
//
//    }
//
//
//    /**
//     * 发布话题
//     * @param response
//     */
//    @RequestMapping("/publicTopic")
//    public void publicTopic(HttpServletResponse response){
//        LOG.info("发布话题");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.publicTopic(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof UncategorizedSQLException) {
//                retmap.put("success", 0);
//                retmap.put("msg", "不允许输入特殊字符");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else {
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 关注话题
//     * @param response
//     */
//    @RequestMapping("/followTopic")
//    public void followTopic(HttpServletResponse response){
//        LOG.info("关注话题");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.followTopic(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已关注");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 获得话题分类
//     * @param response
//     */
//    @RequestMapping("/getTopictypeList")
//    public void getTopictypeList(HttpServletResponse response){
//        LOG.info("获得话题分类");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getTopictypeList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得我关注的话题
//     * @param response
//     */
//    @RequestMapping("/getMyFollowedTopic")
//    public void getMyFollowedTopic(HttpServletResponse response){
//        LOG.info("获得我关注的话题");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyFollowedTopic(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 我的介绍
//     * @param response
//     */
//    @RequestMapping("/getMyIntroduce")
//    public void getMyIntroduce(HttpServletResponse response){
//        LOG.info("我的介绍");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyIntroduce(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    @RequestMapping("/getMySimpleIntroduce")
//    public void getMySimpleIntroduce(HttpServletResponse response){
//        LOG.info("我的简介绍");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMySimpleIntroduce(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 修改我的介绍(需要根据公司地址获得经度纬度)
//     * @param response
//     */
//    @RequestMapping("/updateMyIntroduce")
//    public void updateMyIntroduce(HttpServletResponse response){
//        LOG.info("修改我的介绍(需要根据公司地址获得经度纬度)");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.updateMyIntroduce(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof UncategorizedSQLException) {
//                retmap.put("success", 0);
//                retmap.put("msg", "不允许输入特殊字符");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else {
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 修改我的介绍(需要根据公司地址获得经度纬度)
//     * @param response
//     */
//    @RequestMapping("/updatePassword")
//    public void updatePassword(HttpServletResponse response){
//        LOG.info("修改我的密码");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.updatePassword(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 我发布的话题
//     * @param response
//     */
//    @RequestMapping("/getMyPublishedTopic")
//    public void getMyPublishedTopic(HttpServletResponse response){
//        LOG.info("我发布的话题");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyPublishedTopic(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得我关注的新闻
//     * @param response
//     */
//    @RequestMapping("/getMyFollowedNewsList")
//    public void getMyFollowedNewsList(HttpServletResponse response){
//        LOG.info("获得我关注的新闻");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyFollowedNewsList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得我已关注的活动
//     * @param response
//     */
//    @RequestMapping("/getMyFollowedActivityList")
//    public void getMyFollowedActivityList(HttpServletResponse response){
//        LOG.info("获得我已关注的活动");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyFollowedActivityList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得我已关注的服务
//     * @param response
//     */
//    @RequestMapping("/getMyFollowedServiceList")
//    public void getMyFollowedServiceList(HttpServletResponse response){
//        LOG.info("获得我已关注的服务");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyFollowedServiceList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得我已发布的服务
//     * @param response
//     */
//    @RequestMapping("/getMyPublishedServiceList")
//    public void getMyPublishedServiceList(HttpServletResponse response){
//        LOG.info("获得我已发布的服务");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyPublishedServiceList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得我推荐的好友
//     * @param response
//     */
//    @RequestMapping("/getMyRecommendedMemberList")
//    public void getMyRecommendedMemberList(HttpServletResponse response){
//        LOG.info("获得我推荐的好友");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyRecommendedMemberList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 我已关注的私董
//     * @param response
//     */
//    @RequestMapping("/getMyFollowedMember")
//    public void getMyFollowedMember(HttpServletResponse response){
//        LOG.info("我已关注的私董");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyFollowedMember(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得城市列表
//     * @param response
//     */
//    @RequestMapping("/getCityList")
//    public void getCityList(HttpServletResponse response){
//        LOG.info("获得城市列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getCityList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 添加评价
//     * @param response
//     */
//    @RequestMapping("/addMemberMouth")
//    public void addMemberMouth(HttpServletResponse response){
//        LOG.info("添加评价");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.addMemberMouth(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof UncategorizedSQLException) {
//                retmap.put("success", 0);
//                retmap.put("msg", "不允许输入特殊字符");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 上传图片
//     * @param response
//     */
//    @RequestMapping("/uploadImg")
//    public void uploadImg( HttpServletResponse response){
//        System.err.println("------------------公共-图片上传----------------");
//        LOG.info("公共-图片上传");
//        Map<String,Object> map = new HashMap<String,Object>();
//        try{
//            String path = request.getSession().getServletContext().getRealPath("/upload");
//            ServletInputStream servletInputStream = request.getInputStream();
//
//            String uuid = UUID.randomUUID().toString();
//            String kzS=".jpg";
//            String newfileName = uuid+kzS;//文件名
//
//            Date nowDate = new Date();
//            String year  = StringUtil.dateToString(nowDate, "yyyy");
//            String month = StringUtil.dateToString(nowDate, "MM");
//            String day= StringUtil.dateToString(nowDate, "dd");
//            String imgurlnew= "/"+year+"/"+month+"/"+day+"/";
//            String folder = path+imgurlnew;
//            String fileUrl= folder+newfileName;
//            String imgurl="/upload"+imgurlnew+newfileName;
//            File imgFolder = new File(folder);
//            if(!imgFolder.exists()){
//                imgFolder.mkdirs();
//            }
//
//            File imgFile = new File(fileUrl);
//            if(!imgFile.exists()){
//                try {
//                    imgFile.createNewFile();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            FileOutputStream fileOutputStream = null;
//            try{
//                fileOutputStream = new FileOutputStream(imgFile);
//                byte[] data = new byte[1024];
//                int len = 0;
//                while((len = servletInputStream.read(data)) != -1){
//                    fileOutputStream.write(data,0,len);
//                }
////                String commpresspath = request.getSession().getServletContext().getRealPath("/upload/");
////                String compressFile=commpresspath+"/compress"+newfileName;
////                //File newFile = new File(path, newfileName);
////                ImageUtil.compressImg(path+File.separator+newfileName,compressFile);
//            }catch(Exception e){
//                e.printStackTrace();
//            }finally{
//                if(servletInputStream != null){
//                    servletInputStream.close();
//                }
//                if(fileOutputStream != null){
//                    fileOutputStream.close();
//                }
//            }
//            map.put("success", 1);
////            String fileUrl= request.getContextPath()+"/upload/"+newfileName;
//            map.put("imgurl", imgurl);
//            this.responseMsg(response, Object2Json.bean2Json2(map));
//
//        }catch(Exception e){
//            e.printStackTrace();
//            map.put("success", 0);
//            this.responseMsg(response, Object2Json.bean2Json2(map));
//        }
//
//    }
//
//
//    /**
//     * 设置资料是否公开
//     * @param response
//     */
//    @RequestMapping("/setIspublic")
//    public void setIspublic(HttpServletResponse response){
//        LOG.info("设置资料是否公开");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.setIspublic(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获取服务分类
//     * @param response
//     */
//    @RequestMapping("/getMemservicetypeList")
//    public void getMemservicetypeList(HttpServletResponse response){
//        LOG.info("获取服务分类");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemservicetypeList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获取服务标签接口
//     * @param response
//     */
//    @RequestMapping("/getMemserviceflagList")
//    public void getMemserviceflagList(HttpServletResponse response){
//        LOG.info("获取服务标签接口");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemserviceflagList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 服务可发布数量和发布数量接口
//     * @param response
//     */
//    @RequestMapping("/getMemserviceAuthNumAndUseNum")
//    public void getMemserviceAuthNumAndUseNum(HttpServletResponse response){
//        LOG.info("服务可发布数量和发布数量接口");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemserviceAuthNumAndUseNum(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     *获得对服务的评论列表
//     * @param response
//     */
//    @RequestMapping("/getMemserviceCommentList")
//    public void getMemserviceCommentList(HttpServletResponse response){
//        LOG.info("获得对服务的评论列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMemserviceCommentList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 根据id删除相册
//     * @param response
//     */
//    @RequestMapping("/delMemimgsById")
//    public void delMemimgsById(HttpServletResponse response){
//        LOG.info("根据id删除相册");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.delMemimgsById(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 修改我的相册（从我的介绍接口中单独出来）
//     * @param response
//     */
//    @RequestMapping("/updateMemimgsByMemberid")
//    public void updateMemimgsByMemberid(HttpServletResponse response){
//        LOG.info("修改我的相册（从我的介绍接口中单独出来）");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.updateMemimgsByMemberid(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获取私董通讯录
//     * @param response
//     */
//    @RequestMapping("/getCommumicationBookPrivate")
//    public void getCommumicationBookPrivate(HttpServletResponse response){
//        LOG.info("获取私董通讯录");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getCommumicationBookPrivate(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获取塞维斯通讯录
//     * @param response
//     */
//    @RequestMapping("/getCommumicationBookService")
//    public void getCommumicationBookService(HttpServletResponse response){
//        LOG.info("获取塞维斯员工通讯录");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getCommumicationBookService(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 微信登录
//     * @param response
//     */
//    @RequestMapping("/wxLogin")
//    public void wxLogin(HttpServletResponse response){
//        LOG.info("微信登录");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.wxLogin(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 微信登录完善手机号
//     * @param response
//     */
//    @RequestMapping("/wxPerfectDn")
//    public void wxPerfectDn(HttpServletResponse response,HttpServletRequest request){
//        LOG.info("微信登录完善手机号");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.wxPerfectDn(jobj,request);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","绑定失败，请联系管理员");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//
//    @RequestMapping("/getImFriends")
//    public void getImFriends(HttpServletResponse response,HttpServletRequest request){
//        LOG.info("搜索聊天好友");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getImFriends(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    @RequestMapping("/agreeFriends")
//    public void agreeFriends(HttpServletResponse response,HttpServletRequest request){
//        LOG.info("同意添加好友");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.agreeFriends(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已添加为好友");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","同意失败，联系管理员");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    @RequestMapping("/deleteFriends")
//    public void deleteFriends(HttpServletResponse response,HttpServletRequest request){
//        LOG.info("删除好友");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.deleteFriends(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg"," 解除失败，联系管理员");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    @RequestMapping("/getFriendsList")
//    public void getFriendsList(HttpServletResponse response,HttpServletRequest request){
//        LOG.info("好友列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getFriendsList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//
//    @RequestMapping("/broadcastList")
//    public void broadcastList(HttpServletResponse response,HttpServletRequest request){
//        LOG.info("直播列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.broadcastList(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//
//    @RequestMapping("/getMyAccount")
//    public void getMyAccount(HttpServletResponse response,HttpServletRequest request){
//        LOG.info("我的账户");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getMyAccount(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//
//    @RequestMapping("/getWxInfor")
//    public void getWxInfor(HttpServletResponse response){
//        LOG.info("获取微信信息");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            JSONObject jobj=getJsonObject();
//            if(jobj!=null){
//                String ret=appService.getWxInfor(jobj);
//                this.responseMsg(response,ret);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    //=====================================================songyh   start=======================================================================================
//
//    /**
//     * 获得活动详情
//     * @param response
//     */
//    @RequestMapping("/getActivityDetail")
//    public void getActivityDetail(HttpServletResponse response){
//        LOG.info("获得活动详情");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getActivityDetail(request);
//            this.responseMsg(response,ret);
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//
//    /**
//     * 获得当前活动的报名结果列表
//     * @param response
//     */
//    @RequestMapping("/getAllJoinedActivityList")
//    public void getAllJoinedActivityList(HttpServletResponse response){
//        LOG.info("获得当前活动的报名结果列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.getAllJoinedActivityList(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//    /**
//     * 当前活动评论列表
//     * @param response
//     */
//    @RequestMapping("/getActivityListcontent")
//    public void getActivityListcontent(HttpServletResponse response){
//        LOG.info("当前活动评论列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.getActivityListcontent(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//    }
//
//
//
//    /**
//     * 关注活动
//     * @param response
//     */
//    @RequestMapping("/followActivity")
//    public void followActivity(HttpServletResponse response){
//        LOG.info("关注活动");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.followActivity(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已关注");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 取消关注活动
//     * @param response
//     */
//    @RequestMapping("/cancelFollowActivity")
//    public void cancelFollowActivity(HttpServletResponse response){
//        LOG.info("取消关注活动");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.cancelFollowActivity(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得确认报名信息
//     * @param response
//     */
//    @RequestMapping("/getSureDetail")
//    public void getSureDetail(HttpServletResponse response){
//        LOG.info("获得确认报名信息：支付页和确认报名共用");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getSureDetail(request);
//            this.responseMsg(response,ret);
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 加入活动接口
//     * @param response
//     */
//    @RequestMapping("/joinActivity")
//    public void joinActivity(HttpServletResponse response){
//        LOG.info("加入活动接口");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.joinActivity(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已报名");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObjects.toString());
//            }
//
//        }
//
//    }
//
//
//    /**
//     * 去支付
//     *
//     * @param
//     * @param response
//     *            将response的json数据以form表单格式post至http://www.emi365.cn/emipay/pos/
//     *            addOnlineOrder.do
//     */
//    @RequestMapping("/activityPay")
//    public void activityPay(HttpServletResponse response) {
//
//        LOG.info("活动支付");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String resstr = appService.activityPay(request);
//            this.responseMsg(response,resstr);
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObjects.toString());
//
//        }
//
//
//    }
//
//
//    @RequestMapping("/rechargePay")
//    public void rechargePay(HttpServletResponse response) {
//
//        LOG.info("充值支付");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String resstr = appService.rechargePay(request);
//            this.responseMsg(response,resstr);
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObjects.toString());
//
//        }
//
//    }
//
//
//    /**
//    * @Desc 100元押金支付
//    * @author yurh
//    * @create 修改于2017-11-20 13:55:07
//    **/
//    @RequestMapping("/prePay")
//    public void prePay(HttpServletResponse response) {
//
//        LOG.info("100元押金支付");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String resstr = appService.prePay(request);
//            this.responseMsg(response,resstr);
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObjects.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 支付同步地址
//     */
//    @RequestMapping("/paynotify")
//    public String paynotify(HttpServletResponse response) throws Exception {
//        String tradeno = request.getParameter("orderno");
//        String success = request.getParameter("success");//支付 成功、失败
//        String payuser = request.getParameter("payuser");
//
//        System.out.println("同步入口");
//        System.out.println(tradeno);
//        System.out.println(success);
//        System.out.println(payuser);
//
//        WeChatConf conf = wechatBaseService.getWechatconf();
//
//
//        if(success !=null && "2".equals(success)){//当success初始不是1时，手动去查询支付状态
//            int ri= WeChat.queryPayState(tradeno);//2支付成功 1支付失败 0查询失败
//            if(ri==2){
//                success="1";
//            }
//        }
//
//        if (success != null && "1".equals(success)){ // 支付成功
//            JSONObject jsonObject=appService.synNotiyPay(tradeno, success, payuser, "2", "0",conf);
//            if(jsonObject.containsKey("resultUrl")){
//
//                System.out.println("跳转："+jsonObject.getString("resultUrl"));
////                response.sendRedirect(jsonObject.getString("resultUrl"));
//                return "redirect:"+jsonObject.getString("resultUrl");
//            }
//        }else{
////            response.sendRedirect(contextPath+"/front/pagesmanage/pay/payresult.html");
//            return "redirect:/front/pagesmanage/pay/payresult.html";
//        }
//        return "redirect:/front/pagesmanage/pay/payresult.html";
//    }
//
//    /**
//     * 支付异步地址
//     */
//    @RequestMapping("/asynnotify")
//    public void asynnotify(HttpServletResponse response) {
//        String tradeno = request.getParameter("tradeno");
//        String success = request.getParameter("success");//支付 成功、失败
//        String payuser = request.getParameter("payuser");
//        String state = request.getParameter("state");
//        String refundprice = request.getParameter("refundprice");
//        WeChatConf conf = wechatBaseService.getWechatconf();
//        if (success != null && "1".equals(success)) {// 支付成功
//            appService.synNotiyPay(tradeno, success, payuser, state,refundprice,conf);
//        }
//        this.responseMsg(response, "success");
//    }
//
//
//    @RequestMapping("/h5login")
//    public void h5login(HttpServletResponse response){
//        LOG.info("h5普通登陆");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//                String ret=appService.h5login(request);
//                this.responseMsg(response,ret);
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 获得当前新闻的评论列表
//     * @param response
//     */
//    @RequestMapping("/getNewscommentList")
//    public void getNewscommentList(HttpServletResponse response){
//        LOG.info("获得当前新闻的评论列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.getNewscommentList(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 关注新闻
//     * @param response
//     */
//    @RequestMapping("/followNews")
//    public void followNews(HttpServletResponse response){
//        LOG.info("关注新闻");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.followNews(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已关注");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObjects.toString());
//            }
//        }
//
//    }
//
//
//    /**
//     * 取消关注新闻
//     * @param response
//     */
//    @RequestMapping("/cancelFollowNews")
//    public void cancelFollowNews(HttpServletResponse response){
//        LOG.info("取消关注新闻");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.cancelFollowNews(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//    /**
//     * 点赞评论
//     * @param response
//     */
//    @RequestMapping("/agreelFollowComment")
//    public void agreelFollowComment(HttpServletResponse response){
//        LOG.info("点赞评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.agreelFollowComment(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已点赞");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObjects.toString());
//            }
//        }
//
//    }
//
//    /**
//     * 新闻详情
//     * @param response
//     */
//    @RequestMapping("/getnewsDetail")
//    public void getnewsDetail(HttpServletResponse response){
//        LOG.info("新闻详情");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.getnewsDetail(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//    }
//
//    /**
//     * 评论
//     * @param response
//     */
//    @RequestMapping("/getcomment")
//    public void getcomment(HttpServletResponse response){
//        LOG.info("评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getcomment(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//    /**
//     * 活动评论
//     * @param response
//     */
//    @RequestMapping("/getactivitycomment")
//    public void getactivitycomment(HttpServletResponse response){
//        LOG.info("活动评论");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getactivitycomment(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//
//
//
//    /**
//     * 取消点赞
//     * @param response
//     */
//    @RequestMapping("/followCommentcancel")
//    public void followCommentcancel(HttpServletResponse response) {
//        LOG.info("取消点赞");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.followCommentcancel(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//    /**
//     * 申请加入vip 或者 私董
//     * @param response
//     */
//    @RequestMapping("/addapplication")
//    public void addapplication(HttpServletResponse response) {
//        LOG.info("申请加入vip 或者 私董");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.addapplication(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已申请");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObjects.toString());
//            }
//        }
//
//    }
//
//    /**
//     * 申请加入
//     * @param response
//     */
//    @RequestMapping("/insertmemapply")
//    public void insertmemapply(HttpServletResponse response) {
//        LOG.info("申请加入");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.insertmemapply(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已申请");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObjects.toString());
//            }
//        }
//    }
//
//    /**
//     * 个人主页
//     * @param response
//     */
//    @RequestMapping("/personalsharepage")
//    public void personalsharepage(HttpServletResponse response) {
//        LOG.info("个人主页");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.personalsharepage(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//    /**
//     * 获取验证码
//     * @param response
//     */
//    @RequestMapping("/getphonenumcode")
//    public void getphonenumcode(HttpServletResponse response) {
//        LOG.info("获取验证码");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getphonenumcode(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//    /**
//     * 查看推荐人
//     * @param response
//     */
//    @RequestMapping("/getmemberbyid")
//    public void getmemberbyid(HttpServletResponse response) {
//        LOG.info("查看推荐人");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getmemberbyid(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//    /**
//     * 验证码过期
//     * @param response
//     */
//    @RequestMapping("/updatedatecode")
//    public void updatedatecode(HttpServletResponse response) {
//        LOG.info("验证码过期");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.updatedatecode(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//
//    /**
//     * 通过分享注册
//     * @param response
//     */
//    @RequestMapping("/getregister")
//    public void getregister(HttpServletResponse response) {
//        LOG.info("通过分享注册");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getregister(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已注册");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//
//
//
//    }
//
//
//
//    /**
//    * @Desc  跳转到注册页面
//    * @author yurh
//    * @create 2017-11-22 16:03:51
//    **/
//    @RequestMapping("/toRegisterWx")
//    public String toRegisterWx(){
//        String code = request.getParameter("code");
//        if(code != null && !"".equals(code)){
//            WeChatConf conf = appService.getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
//            UserInfo userinfo = WeChat.getUserInfoByAuth(conf, code);
//            if(userinfo != null ){
//                request.setAttribute("unionid",userinfo.getUnionid());
//                request.setAttribute("openname",userinfo.getNickname());
//                request.setAttribute("openheadimg",userinfo.getHeadimgurl());
//                request.setAttribute("openidbyservicegzh",userinfo.getOpenid());
//
////                Member m = appService.getMemberByOpenidByservicegzh(userinfo.getOpenid());
////                if(m!=null){
////                    Map<String, Object> signMp=new HashMap<String, Object>();
//////                    signMp.put("memberid",m.getId());
////                    String conctm=StringUtil.getUrlParamsByMap(signMp);
////                    String sign= SignUtils.sign(conctm,SignUtils.PRIVATEKEY,true);
//////                    return "redirect:"+ "/front/pagesmanage/mine/personalsharepage.html?memberid="+m.getId()+"&sign="+sign;
////                }
//            }
//        }
//        return "/front/pagesmanage/weixin/wxregister.jsp";
//    }
//
//
//    /**
//     * 微信-》注册
//     * @param response
//     */
//    @RequestMapping("/getwxregister")
//    public void getwxregister(HttpServletResponse response) {
//        LOG.info("验证码过期");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.getwxregister(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已注册");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//    }
//
//
//
//    /**
//    * @Desc 跳转到申请私董或会员界面
//    * @author yurh
//    * @create 2017-11-13 11:29:24
//    **/
//    @RequestMapping("/jumpApplySV")
//    public String  jumpApplySV(HttpServletResponse response) {
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String memberid = request.getParameter("memberid");//会员id
//            System.out.println(memberid);
//            request.setAttribute("memberid",memberid);
//            return "/front/pagesmanage/weixin/sidonghui.jsp";
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return  null;
//
//    }
//
//
//
//
//    /**
//    * @Desc 跳转完善个人资料
//    * @author yurh
//    * @create 2017-11-13 15:01:09
//    **/
//    @RequestMapping("/jumpPrefectInfo")
//    public String   jumpPrefectInfo(HttpServletResponse response) {
//        String memberid = request.getParameter("memberid");//会员id
//        String typef = request.getParameter("typef");
//
////        Member member=appService.getMemberById(memberid);
////        request.setAttribute("memberid",memberid);
////        request.setAttribute("typef",typef);
////        request.setAttribute("member",member);
//
//        return "/front/pagesmanage/weixin/signvip.jsp";
//    }
//
//
//
//
//    /**
//    * @Desc 完善资料申请加入私董/vip会员
//    * @author yurh
//    * @create 2017-11-13 17:41:14
//    **/
//    @RequestMapping("/wxinsertmemapply")
//    public void wxinsertmemapply(HttpServletResponse response) {
//        LOG.info("完善资料申请加入私董/vip会员");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.wxinsertmemapply(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已申请");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObjects.toString());
//            }
//        }
//    }
//
//
//
//    /**
//    * @Desc 跳转支付页面
//    * @author yurh
//    * @create 2017-11-14 09:17:44
//    **/
//    @RequestMapping("/toPayPage")
//    public String  toPayPage(HttpServletResponse response) {
//        LOG.info("跳转支付页面");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        String memberid = request.getParameter("memberid");
//        request.setAttribute("memberid",memberid);
//        String memapplyid = request.getParameter("memapplyid");
//        request.setAttribute("memapplyid",memapplyid);
//        return "/front/pagesmanage/weixin/payearnest.jsp";
//    }
//
//
//
//
//    @RequestMapping("/gettovoteprogam")
//    public void gettovoteprogam(HttpServletResponse response){
//        LOG.info("待投票的节目");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.gettovoteprogam(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//        }
//    }
//
//
//    @RequestMapping("/sendvote")
//    public void sendvote(HttpServletResponse response){
//        LOG.info("投票");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.sendvote(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException){
//                retmap.put("success", 0);
//                retmap.put("msg", "您已投票");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//
//        }
//    }
//
//
//
//    @RequestMapping("/iswxlogin")
//    public void iswxlogin(HttpServletResponse response){
//        LOG.info("是否微信登陆");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            retmap.put("success", 1);
//            retmap.put("iswxlogin",1);
//            this.responseMsg(response,JSONObject.fromObject(retmap).toString());
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//    }
//
//    //获得下载地址
//    @RequestMapping("/getdownUrl")
//    public void getdownUrl(HttpServletResponse response,HttpServletRequest request) throws Exception{
//
//        Enumeration names = request.getHeaderNames();
//        String url = "";
//        while (names.hasMoreElements())
//        {
//            String name = (String) names.nextElement();
//            if (request.getHeader(name).contains("iPhone"))
//            {
//                url = "https://itunes.apple.com/cn/app/%E6%9C%8D%E5%8A%A1%E5%8A%9B%E7%A7%81%E8%91%A3%E4%BC%9A/id1284436720?mt=8";
//                break;
//            }
//        }
//        if ("".equals(url))
//        {
//            url = "https://www.pgyer.com/rkJh";
//        }
//
//        response.sendRedirect(url);
//    }
//
//    //全部课程
//    @RequestMapping("/getcourseList")
//    public void getcourseList(HttpServletResponse response){
//        LOG.info("所有课程列表");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.getcourseList(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//    //活动签到
//    @RequestMapping("/getsignList")
//    public void getsignList(HttpServletResponse response){
//        LOG.info("活动签到");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.getsignList(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//    }
//
//    //签到
//    @RequestMapping("/addsign")
//    public void addsign(HttpServletResponse response){
//        LOG.info("签到");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//            String ret=appService.addsign(request);
//            this.responseMsg(response,ret);
//        }catch (Exception e){
//
//            if(e instanceof DuplicateKeyException) {
//                retmap.put("success", 0);
//                retmap.put("msg", "您已签到");
//                this.responseMsg(response, Object2Json.bean2Json2(retmap));
//            }else{
//                e.printStackTrace();
//                retmap.put("success", 0);
//                retmap.put("msg","系统异常");
//                JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//                this.responseMsg(response,jsonObject.toString());
//            }
//        }
//    }
//
//
//
//    /**
//     * app端申请私董 vip
//     */
//    @RequestMapping("/privateactivityPay")
//    public void privateactivityPay(HttpServletResponse response) {
//
//        LOG.info("app端申请私董 vip");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String resstr = appService.privateactivityPay(request);
//            this.responseMsg(response,resstr);
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObjects  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObjects.toString());
//
//        }
//
//
//    }
//
//    //个人主页二维码跳转主页
//    @RequestMapping("/homepage")
//    public String homepage(HttpServletResponse response) {
//
//            String resstr = appService.homepage(request);
//            return "redirect:"+resstr;
//
//    }
//
//    @RequestMapping("/personalhomepage")
//    public String personalhomepage(HttpServletResponse response) {
//
//        String resstr = appService.personalhomepage(request);
//        return "redirect:"+resstr;
//    }
//
//
//    /**
//     * @Desc 我的名片夹
//     **/
//    @RequestMapping("/businesscardclip")
//    public String  businesscardclip(HttpServletResponse response) {
//        String code = request.getParameter("code");
//        if(code != null && !"".equals(code)){
//            WeChatConf conf = appService.getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
//            UserInfo userinfo = WeChat.getUserInfoByAuth(conf, code);
////            if(userinfo != null ){
////                Member m = appService.getMemberByOpenidByservicegzh(userinfo.getOpenid());
////                if(m!=null){
////                    //名片夹
////                    List<Member> mlist = appService.getbusinesscardclip(m.getId());
////                    request.setAttribute("m",mlist);
////                    request.setAttribute("memberid",m.getId());
////                    return "/front/pagesmanage/weixin/businesscardclip.jsp";
////                }else{
////                    request.setAttribute("unionid",userinfo.getUnionid());
////                    request.setAttribute("openname",userinfo.getNickname());
////                    request.setAttribute("openheadimg",userinfo.getHeadimgurl());
////                    request.setAttribute("openidbyservicegzh",userinfo.getOpenid());
////                }
////            }
//        }
//
//        return "/front/pagesmanage/weixin/wxregister.jsp";
//    }
//
//
//    /**
//     * 名片
//     * @param response
//     */
//    @RequestMapping("/getbusinesscardclip")
//    public void getbusinesscardclip(HttpServletResponse response){
//        LOG.info("名片");
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        try{
//
//            String ret=appService.getbusinesscardclips(request);
//            this.responseMsg(response,ret);
//
//        }catch (Exception e){
//            e.printStackTrace();
//            retmap.put("success", 0);
//            retmap.put("msg","系统异常");
//            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
//            this.responseMsg(response,jsonObject.toString());
//
//        }
//
//    }
//
//
//    /**
//     * 收藏
//     * @param response
//     */
//    @RequestMapping("/joinkeepe")
//    public void joinkeepe(HttpServletResponse response) {
//        try {
//            String memberid = request.getParameter("memberid");
//            String ownmemberid = request.getParameter("ownmemberid");
//
//            String res = appService.joinkeepe(memberid,ownmemberid);
//            this.responseMsg(response, res);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            printError(response, "收藏失败");
//        }
//    }

    //=====================================================songyh   end=========================================================================================













}

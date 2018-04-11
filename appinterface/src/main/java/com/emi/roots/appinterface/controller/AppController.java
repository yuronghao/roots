package com.emi.roots.appinterface.controller;


import com.alibaba.fastjson.JSON;
import com.emi.roots.appinterface.service.AppService;
import com.emi.roots.base.controller.AbsController;
import com.emi.roots.entity.FileEntity;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    /**
     * 微信登录
     * @param response
     */
    @RequestMapping("/wxLogin")
    public void wxLogin(HttpServletResponse response){
        LOG.info("微信登录");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.wxLogin(jobj);
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




    /**
    * @Desc 小程序登录
    * @author yurh
    * @create 2018-04-11 16:46:25
    **/
    @RequestMapping("/xcxLogin")
    public void xcxLogin(HttpServletResponse response){
        LOG.info("微信小程序登录");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.xcxLogin(jobj);
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


    /**
     * 微信登录完善手机号
     * @param response
     */
    @RequestMapping("/wxPerfectDn")
    public void wxPerfectDn(HttpServletResponse response,HttpServletRequest request){
        LOG.info("微信登录完善手机号");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.wxPerfectDn(jobj,request);
                this.responseMsg(response,ret);
            }
        }catch (Exception e){
            e.printStackTrace();
            retmap.put("success", 0);
            retmap.put("msg","绑定失败，请联系管理员");
            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
            this.responseMsg(response,jsonObject.toString());

        }

    }



    /**
     * 小程序登录完善手机号
     * @param response
     */
    @RequestMapping("/xcxPerfectDn")
    public void xcxPerfectDn(HttpServletResponse response,HttpServletRequest request){
        LOG.info("小程序登录完善手机号");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.xcxPerfectDn(jobj,request);
                this.responseMsg(response,ret);
            }
        }catch (Exception e){
            e.printStackTrace();
            retmap.put("success", 0);
            retmap.put("msg","绑定失败，请联系管理员");
            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
            this.responseMsg(response,jsonObject.toString());

        }

    }



    /**
    * @Desc  上传视频
    * @author yurh
    * @create 2018-04-08 10:43:36
    **/
    @RequestMapping("/uploadVideo")
    public void uploadVideo(@RequestParam(value = "file", required = false) MultipartFile multipartFile,HttpServletRequest request,ModelMap map,HttpServletResponse response){
        LOG.info("上传视频");

        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            FileEntity entity = new FileEntity();
            FileUploadTool fileUploadTool = new FileUploadTool();
            entity = fileUploadTool.createFile(multipartFile, request);
            if(entity != null){
                retmap.put("success", 1);
                retmap.put("msg","上传成功");
                this.responseMsg(response, JSON.toJSONString(retmap));
            }

        }catch (Exception e){
            e.printStackTrace();
            retmap.put("success", 0);
            retmap.put("msg","上传失败");
            JSONObject jsonObject  = JSONObject.fromObject(retmap, config);
            this.responseMsg(response,jsonObject.toString());

        }

    }


    /**
     * 获取轮播图
     * @param response
     */
    @RequestMapping("/getCarouselImgs")
    public void getCarouselImgs(HttpServletResponse response){
        LOG.info("首页轮播图接口");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.getCarouselImgs(jobj);
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


    /**
     * 获取验证码
     * @param response
     */
    @RequestMapping("/getphonenumcode")
    public void getphonenumcode(HttpServletResponse response) {
        LOG.info("获取验证码");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.getphonenumcode(jobj,request);
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


    /**
     * 上传图片
     * @param response
     */
    @RequestMapping("/uploadImg")
    public void uploadImg( HttpServletResponse response){
        System.err.println("------------------公共-图片上传----------------");
        LOG.info("公共-图片上传");
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            String path = request.getSession().getServletContext().getRealPath("/upload");
            ServletInputStream servletInputStream = request.getInputStream();

            String uuid = UUID.randomUUID().toString();
            String kzS=".jpg";
            String newfileName = uuid+kzS;//文件名

            Date nowDate = new Date();
            String year  = StringUtil.dateToString(nowDate, "yyyy");
            String month = StringUtil.dateToString(nowDate, "MM");
            String day= StringUtil.dateToString(nowDate, "dd");
            String imgurlnew= "/"+year+"/"+month+"/"+day+"/";
            String folder = path+imgurlnew;
            String fileUrl= folder+newfileName;
            String imgurl="/upload"+imgurlnew+newfileName;
            File imgFolder = new File(folder);
            if(!imgFolder.exists()){
                imgFolder.mkdirs();
            }

            File imgFile = new File(fileUrl);
            if(!imgFile.exists()){
                try {
                    imgFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            FileOutputStream fileOutputStream = null;
            try{
                fileOutputStream = new FileOutputStream(imgFile);
                byte[] data = new byte[1024];
                int len = 0;
                while((len = servletInputStream.read(data)) != -1){
                    fileOutputStream.write(data,0,len);
                }
//                String commpresspath = request.getSession().getServletContext().getRealPath("/upload/");
//                String compressFile=commpresspath+"/compress"+newfileName;
//                //File newFile = new File(path, newfileName);
//                ImageUtil.compressImg(path+File.separator+newfileName,compressFile);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                if(servletInputStream != null){
                    servletInputStream.close();
                }
                if(fileOutputStream != null){
                    fileOutputStream.close();
                }
            }
            map.put("success", 1);
//            String fileUrl= request.getContextPath()+"/upload/"+newfileName;
            map.put("imgurl", imgurl);
            this.responseMsg(response, Object2Json.bean2Json2(map));

        }catch(Exception e){
            e.printStackTrace();
            map.put("success", 0);
            this.responseMsg(response, Object2Json.bean2Json2(map));
        }

    }

    /**
     * 注册
     * @param response
     */
    @RequestMapping("/regist")
    public void regist(HttpServletResponse response, HttpServletRequest request){
        LOG.info("注册");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.regist(jobj,request);
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


    /**
    * @Desc 修改用户信息
    * @author yurh
    * @create 2018-04-08 13:24:25
    **/
    @RequestMapping("/updateUserinfo")
    public void updateUserinfo(HttpServletResponse response, HttpServletRequest request){
        LOG.info("修改用户信息");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.updateUserinfo(jobj,request);
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


    /**
    * @Desc 关注我的人员
    * @author yurh
    * @create 2018-04-08 13:59:48
    **/
    @RequestMapping("/followMeUsers")
    public void followMeUsers(HttpServletResponse response, HttpServletRequest request){
        LOG.info("关注我的人员");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.followMeUsers(jobj,request);
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


    /**
    * @Desc 我关注的人员
    * @author yurh
    * @create 2018-04-08 14:31:51
    **/
    @RequestMapping("/attentionUsers")
    public void attentionUsers(HttpServletResponse response, HttpServletRequest request){
        LOG.info("我关注的人员");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.attentionUsers(jobj,request);
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



    /**
    * @Desc  活动详情
    * @author yurh
    * @create 2018-04-08 14:38:55
    **/
    @RequestMapping("/getActivityDetails")
    public void getActivityDetails(HttpServletResponse response, HttpServletRequest request){
        LOG.info("活动详情");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.getActivityDetails(jobj,request);
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


    /**
    * @Desc 比赛详情
    * @author yurh
    * @create 2018-04-09 14:43:14
    **/
    @RequestMapping("/getCompetitionDetails")
    public void getCompetitionDetails(HttpServletResponse response, HttpServletRequest request){
        LOG.info("比赛详情");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.getCompetitionDetails(jobj,request);
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


    /**
     * @Desc 比赛评论列表
     * @author yurh
     * @create 2018-04-09 16:13:06
     **/
    @RequestMapping("/getCommentList")
    public void getCommentList(HttpServletResponse response, HttpServletRequest request){
        LOG.info("比赛评论列表");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.getCommentList(jobj,request);
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


    /**
    * @Desc  报名参加比赛
    * @author yurh
    * @create 2018-04-10 10:13:49
    **/
    @RequestMapping("/applyCompetition")
    public void applyCompetition(HttpServletResponse response, HttpServletRequest request){
        LOG.info("报名参加比赛");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.applyCompetition(jobj,request);
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



    /**
    * @Desc 取消点赞活动比赛
    * @author yurh
    * @create 2018-04-10 11:19:04
    **/
    @RequestMapping("/cancelLike")
    public void cancelLike(HttpServletResponse response, HttpServletRequest request){
        LOG.info("取消点赞活动比赛");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.cancelLike(jobj,request);
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


    /**
    * @Desc 点赞比赛活动
    * @author yurh
    * @create 2018-04-10 13:49:49
    **/
    @RequestMapping("/likePost")
    public void likePost(HttpServletResponse response, HttpServletRequest request){
        LOG.info("点赞比赛活动");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.likePost(jobj,request);
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




    /**
    * @Desc 取消点赞评论
    * @author yurh
    * @create 2018-04-10 14:19:10
    **/
    @RequestMapping("/cancelLikeComment")
    public void cancelLikeComment(HttpServletResponse response, HttpServletRequest request){
        LOG.info("取消点赞评论");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.cancelLikeComment(jobj,request);
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


    /**
    * @Desc 点赞评论
    * @author yurh
    * @create 2018-04-10 14:53:35
    **/
    @RequestMapping("/likeComment")
    public void likeComment(HttpServletResponse response, HttpServletRequest request){
        LOG.info("点赞评论");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.likeComment(jobj,request);
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



    /**
    * @Desc 比赛活动列表
    * @author yurh
    * @create 2018-04-10 15:51:05
    **/
    @RequestMapping("/getPostsList")
    public void getPostsList(HttpServletResponse response, HttpServletRequest request){
        LOG.info("比赛活动列表");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.getPostsList(jobj,request);
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



    /**
    * @Desc 报名参加活动
    * @author yurh
    * @create 2018-04-10 17:15:06
    **/
    @RequestMapping("/applyActivity")
    public void applyActivity(HttpServletResponse response, HttpServletRequest request){
        LOG.info("报名参加活动");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.applyActivity(jobj,request);
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


    /**
    * @Desc 发布评论
    * @author yurh
    * @create 2018-04-10 17:23:35
    **/
    @RequestMapping("/publishComment")
    public void publishComment(HttpServletResponse response, HttpServletRequest request){
        LOG.info("发布评论");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.publishComment(jobj,request);
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



    /**
    * @Desc 发布话题
    * @author yurh
    * @create 2018-04-11 09:12:56
    **/
    @RequestMapping("/publishTopic")
    public void publishTopic(HttpServletResponse response, HttpServletRequest request){
        LOG.info("发布话题");
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        try{
            JSONObject jobj=getJsonObject();
            if(jobj!=null){
                String ret=appService.publishTopic(jobj,request);
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











}

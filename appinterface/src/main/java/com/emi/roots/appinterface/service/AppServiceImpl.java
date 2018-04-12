package com.emi.roots.appinterface.service;

import com.alibaba.fastjson.JSON;
import com.emi.page.PageInfo;
import com.emi.page.PagerHelper;
import com.emi.roots.entity.*;
import com.emi.roots.mapper.*;
import com.emi.roots.util.*;
import com.emi.roots.wechat.bean.UserInfo;
import com.emi.roots.wechat.util.Constants;
import com.emi.roots.wechat.util.WeChat;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by yuronghao on 2017/8/14.
 */
@Service("appService")
@Transactional
public class AppServiceImpl implements AppService{


    protected static final Logger LOG = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private AzUsersMapper azUsersMapper;

    @Autowired
    private AzOauthUserMapper azOauthUserMapper;

    @Autowired
    private AzSlideMapper azSlideMapper;

    @Autowired
    private AzPostsMapper azPostsMapper;

    @Autowired
    private AzSignUpMapper azSignUpMapper;

    @Autowired
    private AzLikeMapper azLikeMapper;

    @Autowired
    private AzReplyMapper azReplyMapper;
    @Autowired
    private AzForumMapper azForumMapper;
    @Autowired
    private AzForumPostMapper azForumPostMapper;



    private String vertifySgin(JSONObject jobj){

        String publicKey= SignUtils.PUBlICKEY;
        boolean bool=false;
        if(jobj!=null){
            String json= StringUtil.parseString(jobj.get("json"));
            String sign=StringUtil.parseString(jobj.get("sign"));
            if(json!=null&&sign!=null){
                try {
                    bool=SignUtils.verify(json, sign, publicKey,true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            LOG.info("json:"+json);
            LOG.info("sign:"+sign);
        }

        if(!bool){
            Map<String,Object> retMap = new HashMap<String, Object>();
            retMap.put("success", 0);
            retMap.put("msg", "对不起，参数签名错误");
            return Object2Json.bean2Json2(retMap);
        }
        return null;

    }


    public JSONObject getStringtoJson(String json){
        if(json!=null&&!"".equals(json)){
            try{
                JSONObject j= JSONObject.fromObject(json);
                return j;
            }catch(Exception e){
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }




    /**
     * 登陆
     *
     * @return
     */
    @Transactional
    public String login(JSONObject jobj) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String phoneNum=StringUtil.parseString(dataMap.get("phoneNum"));
        String password=StringUtil.parseString(dataMap.get("password"));
//        String id=StringUtil.parseString(dataMap.get("id"));


        AzUsers user = azUsersMapper.login(phoneNum);
        Map<String, Object> retmap = new HashMap<String, Object>();
        if (user != null) {
            if (password != null && password.equals(user.getUser_pass())) {
                retmap.put("id", user.getId());
                retmap.put("avatar", user.getAvatar());
                retmap.put("user_nicename", user.getUser_nicename());
                retmap.put("user_login", user.getUser_login());
                retmap.put("user_email", user.getUser_email());
//                retmap.put("unionid", user.getName());
//                retmap.put("openname", user.getName());
//                retmap.put("openheadimg", user);
                retmap.put("user_url", user.getUser_url());
                retmap.put("sex", user.getSex());
                retmap.put("birthday", user.getBirthday());
                retmap.put("mobile", user.getMobile());
                retmap.put("follow_num", user.getFollow_num());

                retmap.put("success", 1);
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();
            } else {
                retmap.put("msg", "密码错误！");
                retmap.put("success", 0);
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();
            }
        } else {
            retmap.put("success", 0);
            retmap.put("msg", "用户名不存在");
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();
        }

    }

    public String getCarouselImgs(JSONObject jobj) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String imgtype=StringUtil.parseString(dataMap.get("imgtype"));
        List<AzSlide> azSlideList = azSlideMapper.getAzslideCarouseList(imgtype);
        Map<String, Object> retmap = new HashMap<String, Object>();

            retmap.put("success", 1);
            retmap.put("data", azSlideList);
            config.setExcludes(new String[] {});
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();
    }
//
//    @Override
    @Transactional(rollbackFor = Exception.class)
    public String regist(JSONObject jobj,HttpServletRequest request) throws Exception{
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String phoneNum=StringUtil.parseString(dataMap.get("phoneNum"));
        String verificationCode=StringUtil.parseString(dataMap.get("verificationCode"));
        String password=StringUtil.parseString(dataMap.get("password"));

        Map<String, Object> retmap = new HashMap<String, Object>();

        AzUsers member = azUsersMapper.getAzUsersByPhone(phoneNum);
        if(member != null ){
            retmap.put("success", 0);
            retmap.put("msg", "手机号已注册");
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();

        }

        AzValidatecode validatecode = azUsersMapper.getvalidateCode(verificationCode,phoneNum);//当前有效的验证码

        if(validatecode != null){
            if(verificationCode.equals(validatecode.getCode())){

                Date date = new Date();
                long currenttime = date.getTime();
                long recordtime = validatecode.getCtime().getTime();
                long datetime = (currenttime - recordtime) / (1000);
                if(datetime > 180){
                    retmap.put("success", 0);
                    retmap.put("msg", "此验证码过3分钟有效期");
                    jsonObject = JSONObject.fromObject(retmap, config);
                    return jsonObject.toString();
                }
                String ip = this.getIpAddr(request);
                String city = GetPlaceByIp.getUserCity(ip);
                String citycode = "";
                Map map = null;




                AzUsers azUsers1 = new AzUsers();
                azUsers1.setUser_login(phoneNum);
                azUsers1.setUser_pass(password);
                azUsers1.setUser_nicename(phoneNum.substring(phoneNum.length() - 4,phoneNum.length()).concat("rose"));
                azUsers1.setLast_login_ip(ip);
                azUsers1.setLast_login_time(new Date());
                azUsers1.setCreate_time(new Date());
                azUsers1.setMobile(phoneNum);

                azUsersMapper.insertSelective(azUsers1);

                retmap.put("success", 1);
                retmap.put("id", azUsers1.getId());
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();

            }else{
                //验证码不相同
                retmap.put("success", 0);
                retmap.put("msg", "验证码错误");
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();
            }

        }else{
            retmap.put("success", 0);
            retmap.put("msg", "验证码错误");
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();

        }

    }
//
//
//
    public String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
//
//
    @Transactional(rollbackFor = Exception.class)
    public String wxLogin(JSONObject jobj) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String code=StringUtil.parseString(dataMap.get("code"));
//        WeChatConf weChatConf = new WeChatConf();
        AzWeixinOptions weixinOptions = new AzWeixinOptions();
        weixinOptions.setApp_id(Constants.WX_APPID);//舞蹈圈开放平台相关数据
        weixinOptions.setApp_secret(Constants.WX_APPSECRET);
        UserInfo userInfo = WeChat.getUserInfoByAuth(weixinOptions,code);
        if(userInfo != null && !"".equals(userInfo.getOpenid())){
            AzOauthUser azOauthUser = azUsersMapper.getAzOauthUserByOpenid(userInfo.getOpenid());
            String headimgurl =  userInfo.getHeadimgurl();
            if(headimgurl.equals("/0")){
                headimgurl="";
            }
            String nickname =  userInfo.getNickname();
            if(azOauthUser != null){
                //用户已存在，更新资料(昵称和头像)
                if(StringUtil.isNullObject(azOauthUser.getName()) || StringUtil.isNullObject(azOauthUser.getHead_img())){
                    azUsersMapper.updateAzOauthUserWXinfo(azOauthUser.getId(),headimgurl,nickname,azOauthUser);
                }

                Map<String, Object> retmap = new HashMap<String, Object>();
                retmap.put("success", 1);
                retmap.put("data", azOauthUser);
                config.setExcludes(new String[] {  });
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();

            }else{
                //用户不存在，只返回未绑定信息，在绑定信息的时候加入
                AzOauthUser newmember = new AzOauthUser();
                newmember.setOpenid(userInfo.getOpenid());
                newmember.setName(userInfo.getNickname());
                newmember.setHead_img(headimgurl);
                Map<String, Object> retmap = new HashMap<String, Object>();

                retmap.put("success", 1);
                retmap.put("data", newmember);
                config.setExcludes(new String[] {  });
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();

            }
        }

        Map<String, Object> retmap = new HashMap<String, Object>();
        retmap.put("success", 0);
        retmap.put("msg", "没有用户");
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }
//
    @Transactional(rollbackFor = Exception.class)
    public String wxPerfectDn(JSONObject jobj,HttpServletRequest request) throws Exception {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String phoneNum=StringUtil.parseString(dataMap.get("phoneNum"));
        String verificationCode=StringUtil.parseString(dataMap.get("verificationCode"));
        String password=StringUtil.parseString(dataMap.get("password"));
        String openid = String.valueOf(dataMap.get("openid"));
        String openname = String.valueOf(dataMap.get("openname"));
        String openheadimg = String.valueOf(dataMap.get("openheadimg"));
        Map<String, Object> retmap = new HashMap<String, Object>();//返回信息
        AzValidatecode validatecode = azUsersMapper.getvalidateCode(verificationCode,phoneNum);//当前有效的验证码

        if(validatecode != null){
            if(verificationCode.equals(validatecode.getCode())){

                Date date = new Date();
                long currenttime = date.getTime();
                long recordtime = validatecode.getCtime().getTime();
                long datetime = (currenttime - recordtime) / (1000);
                if(datetime > 180){
                    retmap.put("success", 0);
                    retmap.put("msg", "此验证码过3分钟有效期");
                    jsonObject = JSONObject.fromObject(retmap, config);
                    return jsonObject.toString();
                }

                String ip = this.getIpAddr(request);


                AzUsers azUsers = azUsersMapper.getAzUsersByPhone(phoneNum);//绑定手机号码
                if(azUsers != null ){
                    //更新微信信息
                    if(!StringUtil.isNullObject(azUsers.getOpenid())){//如果输入的绑定的手机号已经存在了openid
                        retmap.put("success", 0);
                        retmap.put("msg", "该号码已被绑定");
                        jsonObject = JSONObject.fromObject(retmap, config);
                        return jsonObject.toString();
                    }else{
                        //没哟绑定，新增az_oauth_user表信息
                        AzOauthUser  azOauthUser = new AzOauthUser();
                        azOauthUser.setHead_img(openheadimg);
                        azOauthUser.setName(openname);
                        azOauthUser.setOpenid(openid);
                        azOauthUser.setFrom("微信");
                        azOauthUser.setUid(azUsers.getId());
                        azOauthUser.setLast_login_ip(ip);
                        azOauthUser.setLast_login_time(new Date());
                        azOauthUser.setCreate_time(new Date());
                        azOauthUser.setLogin_times(1);
                        azOauthUserMapper.insertSelective(azOauthUser);
                        retmap.put("success", 1);
                        retmap.put("data", azOauthUser);
                        jsonObject = JSONObject.fromObject(retmap, config);
                        return jsonObject.toString();
                    }

                }else{
                    //没有信息，新增用户信息 AzUsers 和Azoauthuser 表
                    AzUsers azUsers1 = new AzUsers();
                    azUsers1.setUser_login(phoneNum);
                    azUsers1.setUser_pass(password);
                    azUsers1.setUser_nicename(phoneNum.substring(phoneNum.length() - 4,phoneNum.length()).concat("rose"));
//                    azUsers1.setUser_email();
//                    azUsers1.setUser_url();
//                    azUsers1.setAvatar();
//                    azUsers1.setSex();
//                    azUsers1.setBirthday();
//                    azUsers1.setSignature();
                    azUsers1.setLast_login_ip(ip);
                    azUsers1.setLast_login_time(new Date());
                    azUsers1.setCreate_time(new Date());
//                    azUsers1.setUser_type();
                    azUsers1.setMobile(phoneNum);

                    azUsersMapper.insertSelective(azUsers1);


                    AzOauthUser  azOauthUser = new AzOauthUser();
                    azOauthUser.setHead_img(openheadimg);
                    azOauthUser.setName(openname);
                    azOauthUser.setOpenid(openid);
                    azOauthUser.setFrom("微信");
                    azOauthUser.setUid(azUsers1.getId());
                    azOauthUser.setLast_login_ip(ip);
                    azOauthUser.setLast_login_time(new Date());
                    azOauthUser.setCreate_time(new Date());
                    azOauthUser.setLogin_times(1);
                    azOauthUserMapper.insertSelective(azOauthUser);



                    retmap.put("success", 1);
                    retmap.put("data", azOauthUser);
                    jsonObject = JSONObject.fromObject(retmap, config);
                    return jsonObject.toString();

                }

            }else{
                //验证码不相同
                retmap.put("success", 0);
                retmap.put("msg", "验证码错误");
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();
            }

        }else{
            retmap.put("success", 0);
            retmap.put("msg", "验证码错误");
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();

        }
    }
//
    public String getphonenumcode(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();


        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String phonenum=StringUtil.parseString(dataMap.get("phoneNum"));

        AzUsers m = azUsersMapper.getAzUsersByPhone(phonenum);
        if(m==null){ //获取验证码

            AzValidatecode vaa =new AzValidatecode();
            vaa.setDn(phonenum);
            azUsersMapper.updateCheckCode(vaa);

             //插入新的验证码
            String securityCode = Makemsgcode.Makemsgcode();
            AzValidatecode fjcheckcode=new AzValidatecode();
            fjcheckcode.setCode(securityCode);
            fjcheckcode.setCtime(new Timestamp(new Date().getTime()));
            fjcheckcode.setDn(phonenum);
            fjcheckcode.setState(0);
            azUsersMapper.insertCheckCode(fjcheckcode);

            if(true){
                System.out.println(DateUtil.dateToString(new Date(0), "yyyy-MM-dd HH:mm:ss")+" ==> 验证码生成成功:"+securityCode);
            }else{
                System.out.println(DateUtil.dateToString(new Date(0), "yyyy-MM-dd HH:mm:ss")+" ==> 验证码生成失败,请检查缓存设置");
            }
            //发送短信方法
            //String efSec = OtoPropertites.get("cache.effectiveTime");
            //String minute = (Integer.parseInt(efSec)/60)+"";
            NewSendTemplateSMS.sendRegMsg(phonenum, new String[]{securityCode,"60"});
            retmap.put("success", 1);
            retmap.put("msg","");
        }else{
            retmap.put("success",0);
            retmap.put("msg","手机号已注册");
            return Object2Json.bean2Json2(retmap);
        }


        config.setExcludes(new String[] {});
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }

    /**
    * @Desc 修改用户信息
    * @author yurh
    * @create 2018-04-08 13:25:38
    **/
    public String updateUserinfo(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String id=StringUtil.parseString(dataMap.get("id"));
        String avatar=StringUtil.parseString(dataMap.get("avatar"));
        String user_nicename=StringUtil.parseString(dataMap.get("user_nicename"));
        String mobile=StringUtil.parseString(dataMap.get("mobile"));

        azUsersMapper.updateUserinfo(id,avatar,user_nicename,mobile);
        retmap.put("success",1);
        retmap.put("msg","修改成功");
        return Object2Json.bean2Json2(retmap);

    }

    /**
    * @Desc 关注我的人员
    * @author yurh
    * @create 2018-04-08 14:21:43
    **/
    public String followMeUsers(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String memberid=StringUtil.parseString(dataMap.get("memberid"));

        List<AzUsers> azUsersList = azUsersMapper.getfollowMeUsers(memberid);

        retmap.put("success",1);
        retmap.put("msg","成功");
        retmap.put("data",azUsersList);

        return Object2Json.bean2Json2(retmap);
    }

    public String attentionUsers(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String memberid=StringUtil.parseString(dataMap.get("memberid"));


        List<AzUsers> azUsersList = azUsersMapper.attentionUsers(memberid);

        retmap.put("success",1);
        retmap.put("msg","成功");
        retmap.put("data",azUsersList);
        return Object2Json.bean2Json2(retmap);
    }

    /**
    * @Desc 活动详情
    * @author yurh
    * @create 2018-04-08 14:39:33
    **/
    public String getActivityDetails(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));




        AzPosts azPosts = azPostsMapper.getActivityDetails(matchid,userid);
        String meterialids = azPosts.getMaterial_id();
        String goodsids = azPosts.getCommodity_id();
        if(meterialids != null && !"".equals(meterialids)){
            //精选图片
            List<AzSofAttachment> sofAttachmentList = azPostsMapper.getMeterialsByids(meterialids);
            azPosts.setAzSofAttachmentList(sofAttachmentList);
        }

        if(goodsids != null && !"".equals(goodsids)){
            //商品
            List<AzGoods> azGoodsList = azPostsMapper.getgoodsByids(goodsids);
            azPosts.setAzGoodsList(azGoodsList);
        }

        retmap.put("success",1);
        retmap.put("msg","成功");
        retmap.put("data",azPosts);
        return Object2Json.bean2Json2(retmap);
    }

    /**
    * @Desc 比赛详情
    * @author yurh
    * @create 2018-04-09 14:45:02
    **/
    public String getCompetitionDetails(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));

        AzPosts azPosts = azPostsMapper.getActivityDetails(matchid,userid);
        if(azPosts != null){
            List<AzUsers> azUsersList = azPostsMapper.getjoinmember(azPosts.getId());
            azPosts.setAzUsersList(azUsersList);

            String meterialids = azPosts.getMaterial_id();
            if(meterialids != null && !"".equals(meterialids)){
                //精选图片
                List<AzSofAttachment> sofAttachmentList = azPostsMapper.getMeterialsByids(meterialids);
                azPosts.setAzSofAttachmentList(sofAttachmentList);
            }
        }

        retmap.put("success",1);
        retmap.put("msg","成功");
        retmap.put("data",azPosts);
        return Object2Json.bean2Json2(retmap);

    }

    /**
    * @Desc 比赛评论列表
    * @author yurh
    * @create 2018-04-09 16:26:22
    **/
    public String getCommentList(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));

        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
        List<AzReply> azReplyList = azPostsMapper.getFenPageCommentList(pageInfo,matchid,userid);
        retmap.put("success", 1);
        retmap.put("data", azReplyList);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }


    /**
    * @Desc 报名参加比赛
    * @author yurh
    * @create 2018-04-10 10:21:03
    **/
    public String applyCompetition(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String data=StringUtil.parseString(dataMap.get("data"));

        com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(data);

        //az_sign_up 表
        AzSignUp azSignUp = new AzSignUp();
        azSignUp.setUid(Integer.parseInt(userid));
        azSignUp.setObject_id(Integer.parseInt(matchid));
        azSignUp.setOpus(jsonArray.toJSONString());
        azSignUp.setCreate_time(new Date());
        azSignUpMapper.insertSelective(azSignUp);
        retmap.put("success", 1);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }


    /**
    * @Desc 取消点赞活动比赛
    * @author yurh
    * @create 2018-04-10 11:19:30
    **/
    public String cancelLike(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String data=StringUtil.parseString(dataMap.get("data"));

        //删除点赞表中的数据 az_like
        String tablename = "posts";
        azSignUpMapper.deleteAzLike(matchid,userid,tablename);
        azSignUpMapper.updatePostLikeNumsub(matchid);
        retmap.put("success", 1);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }

    /**
    * @Desc 点赞比赛活动
    * @author yurh
    * @create 2018-04-10 13:51:50
    **/
    public String likePost(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String data=StringUtil.parseString(dataMap.get("data"));
        //新增点赞表中的数据az_like
        AzLike azLike = new AzLike();
        azLike.setObject_id(Integer.parseInt(matchid));
        azLike.setUid(Integer.parseInt(userid));
        azLike.setCreate_time(new Date());
        azLike.setTablename("posts");
        azLikeMapper.insertSelective(azLike);
        azSignUpMapper.updatePostLikeNumadd(matchid);

        retmap.put("success", 1);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }

    /**
    * @Desc 取消点赞评论
    * @author yurh
    * @create 2018-04-10 14:19:57
    **/
    public String cancelLikeComment(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("replyid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String data=StringUtil.parseString(dataMap.get("data"));

        String tablename = "reply";
        azSignUpMapper.deleteAzLike(matchid,userid, tablename);
        azSignUpMapper.updateCommentLikenumsub(matchid);

        retmap.put("success", 1);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }


    /**
    * @Desc 点赞评论
    * @author yurh
    * @create 2018-04-10 14:54:07
    **/
    public String likeComment(JSONObject jobj, HttpServletRequest request) {

        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("replyid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String data=StringUtil.parseString(dataMap.get("data"));


        //新增点赞表中的数据az_like
        AzLike azLike = new AzLike();
        azLike.setObject_id(Integer.parseInt(matchid));
        azLike.setUid(Integer.parseInt(userid));
        azLike.setCreate_time(new Date());
        azLike.setTablename("reply");
        azLikeMapper.insertSelective(azLike);
        azSignUpMapper.updatePostLikeNumadd(matchid);

        retmap.put("success", 1);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }

    /**
    * @Desc 比赛活动列表
    * @author yurh
    * @create 2018-04-10 15:51:56
    **/
    public String getPostsList(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String searchtext=StringUtil.parseString(dataMap.get("searchtext"));//关键字检索
        String type=StringUtil.parseString(dataMap.get("type"));//0:活动 1：比赛
        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));


        StringBuffer sb = new StringBuffer();
        if(searchtext != null && !"".equals(searchtext)){
            sb.append(" and (ap.post_title like '%"+searchtext+"%' or ap.post_content like '%"+searchtext+"%') ");
        }
        /**
         * 区分 活动/比赛 通过az_terms id 为1:表示比赛  2:活动
         */
        List<AzPosts> azPostsList = new ArrayList<AzPosts>();
        if (type != null && !"".equals(type)) {
            int temptype = Integer.parseInt(type);
            if(temptype == 0){
                //活动
                PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
                int termid = 2;
                azPostsList = azPostsMapper.getPostsListHD(pageInfo,sb.toString(),termid);

                for(int i= 0;i<azPostsList.size();i++){
                    AzPosts azPosts = azPostsList.get(i);
                    if(azPosts.getMaterial_id() != null && !"".equals(azPosts.getMaterial_id())){
                        List<AzSofAttachment> sofAttachmentList = azPostsMapper.getMeterialsByids(azPosts.getMaterial_id());
                        azPosts.setAzSofAttachmentList(sofAttachmentList);
                    }

                }

            }else if(temptype ==1){
                //比赛
                PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
                int termid = 1;
                azPostsList = azPostsMapper.getPostsListHD(pageInfo,sb.toString(),termid);
                for(int i= 0;i<azPostsList.size();i++){
                    AzPosts azPosts = azPostsList.get(i);
                    if(azPosts.getMaterial_id() != null && !"".equals(azPosts.getMaterial_id())){
                        List<AzSofAttachment> sofAttachmentList = azPostsMapper.getMeterialsByids(azPosts.getMaterial_id());
                        azPosts.setAzSofAttachmentList(sofAttachmentList);
                    }
                }
            }
        }

        retmap.put("success", 1);
        retmap.put("data", azPostsList);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }

    /**
    * @Desc 报名参加活动
    * @author yurh
    * @create 2018-04-10 17:16:53
    **/
    public String applyActivity(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));


//az_sign_up 表
        AzSignUp azSignUp = new AzSignUp();
        azSignUp.setUid(Integer.parseInt(userid));
        azSignUp.setObject_id(Integer.parseInt(matchid));
        azSignUp.setCreate_time(new Date());
        azSignUpMapper.insertSelective(azSignUp);
        retmap.put("success", 1);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }

    /**
    * @Desc 发布评论
    * @author yurh
    * @create 2018-04-10 17:24:14
    **/
    public String publishComment(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String matchid=StringUtil.parseString(dataMap.get("matchid"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String content=StringUtil.parseString(dataMap.get("content"));

        AzReply azReply = new AzReply();

        azReply.setContent(content);
        azReply.setObject_id(Integer.parseInt(matchid));
        azReply.setTablename("posts");
        azReply.setUid(Integer.parseInt(userid));
        azReply.setCreate_time(Calendar.getInstance().getTimeInMillis());

        azReplyMapper.insertSelective(azReply);

        retmap.put("success", 1);
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }

    /**
    * @Desc 发布话题
    * @author yurh
    * @create 2018-04-11 09:13:27
    **/
    public String publishTopic(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String title=StringUtil.parseString(dataMap.get("title"));
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String content=StringUtil.parseString(dataMap.get("content"));
        String topictypeid=StringUtil.parseString(dataMap.get("topictypeid"));//版块类型 az_forum_type 表的id
        String data=StringUtil.parseString(dataMap.get("data"));



        AzForum azForum = new AzForum();
        azForum.setType_id(Integer.parseInt(topictypeid));
        azForum.setName(title);
        azForum.setCreate_time(System.currentTimeMillis());
        azForum.setUid(Integer.parseInt(userid));
        azForumMapper.insertSelective(azForum);


        AzForumPost azForumPost = new AzForumPost();
        azForumPost.setForum_id(azForum.getId());
        azForumPost.setUid(Integer.parseInt(userid));
        azForumPost.setCreate_time(System.currentTimeMillis());
        azForumPost.setTitle(title);
        azForumPost.setContent(content);
        azForumPostMapper.insertSelective(azForumPost);

        retmap.put("success", 1);
        config.setExcludes(new String[] {});
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();
    }


    /**
    * @Desc 小程序登录
    * @author yurh
    * @create 2018-04-11 16:47:07
    **/
    @Transactional(rollbackFor = Exception.class)
    public String xcxLogin(JSONObject jobj) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String code=StringUtil.parseString(dataMap.get("code"));
        WeixinXCXInfo weixinXCXInfo = new WeixinXCXInfo();
        weixinXCXInfo.setJs_code(code);
        WeixinXCXLoginResult weixinXCXLoginResult = XcxUtil.getXCXLoginInfoByAuth(weixinXCXInfo);
        if(weixinXCXLoginResult != null && !"".equals(weixinXCXLoginResult.getOpenid())){
            AzOauthUser azOauthUser = azUsersMapper.getAzOauthUserByOpenid(weixinXCXLoginResult.getOpenid());

            if(azOauthUser != null){
                //用户已存在，更新资料(昵称和头像)  小程序更新昵称和头像在完善手机号完成，因为这个获取信息只能获取openid
//                if(StringUtil.isNullObject(azOauthUser.getName()) || StringUtil.isNullObject(azOauthUser.getHead_img())){
//                    azUsersMapper.updateAzOauthUserWXinfo(azOauthUser.getId(),headimgurl,nickname,azOauthUser);
//                }
                weixinXCXLoginResult.setAzOauthUser(azOauthUser);
                Map<String, Object> retmap = new HashMap<String, Object>();
                retmap.put("success", 1);
                retmap.put("flag", 1);
                retmap.put("msg", "该用户是会员");
                retmap.put("data", weixinXCXLoginResult);
                config.setExcludes(new String[] {  });
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();

            }else{
                //用户不存在，只返回未绑定信息，在绑定信息的时候加入
//                AzOauthUser newmember = new AzOauthUser();
//                newmember.setOpenid(azOauthUser.getOpenid());
                Map<String, Object> retmap = new HashMap<String, Object>();
                retmap.put("success", 1);
                retmap.put("flag", 0);
                retmap.put("msg", "用户信息不存在，不是会员");
                retmap.put("data", weixinXCXLoginResult);
                config.setExcludes(new String[] {  });
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();

            }
        }

        Map<String, Object> retmap = new HashMap<String, Object>();
        retmap.put("success", 0);
        retmap.put("msg", "没有用户");
        config.setExcludes(new String[] {  });
        jsonObject = JSONObject.fromObject(retmap, config);
        return jsonObject.toString();

    }

    /**
    * @Desc 小程序完善手机号
    * @author yurh
    * @create 2018-04-11 17:35:49
    **/
    public String xcxPerfectDn(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String phoneNum=StringUtil.parseString(dataMap.get("mobile"));
        String verificationCode=StringUtil.parseString(dataMap.get("verificationCode"));
        String password=StringUtil.parseString(dataMap.get("password"));
        String openid = String.valueOf(dataMap.get("openid"));
        String openname = String.valueOf(dataMap.get("name"));
        String openheadimg = String.valueOf(dataMap.get("head_img"));
        Map<String, Object> retmap = new HashMap<String, Object>();//返回信息
        AzValidatecode validatecode = azUsersMapper.getvalidateCode(verificationCode,phoneNum);//当前有效的验证码

        if(validatecode != null){
            if(verificationCode.equals(validatecode.getCode())){

                Date date = new Date();
                long currenttime = date.getTime();
                long recordtime = validatecode.getCtime().getTime();
                long datetime = (currenttime - recordtime) / (1000);
                if(datetime > 180){
                    retmap.put("success", 0);
                    retmap.put("msg", "此验证码过3分钟有效期");
                    jsonObject = JSONObject.fromObject(retmap, config);
                    return jsonObject.toString();
                }

                String ip = this.getIpAddr(request);


                AzUsers azUsers = azUsersMapper.getAzUsersByPhone(phoneNum);//绑定手机号码
                if(azUsers != null ){
                    //更新微信信息
                    if(!StringUtil.isNullObject(azUsers.getOpenid())){//如果输入的绑定的手机号已经存在了openid
                        retmap.put("success", 0);
                        retmap.put("msg", "该号码已被绑定");
                        jsonObject = JSONObject.fromObject(retmap, config);
                        return jsonObject.toString();
                    }else{
                        //没哟绑定，新增az_oauth_user表信息
                        AzOauthUser  azOauthUser = new AzOauthUser();
                        azOauthUser.setHead_img(openheadimg);
                        azOauthUser.setName(openname);
                        azOauthUser.setOpenid(openid);
                        azOauthUser.setFrom("小程序");
                        azOauthUser.setUid(azUsers.getId());
                        azOauthUser.setLast_login_ip(ip);
                        azOauthUser.setLast_login_time(new Date());
                        azOauthUser.setCreate_time(new Date());
                        azOauthUser.setLogin_times(1);
                        azOauthUser.setMobile(phoneNum);
                        azOauthUserMapper.insertSelective(azOauthUser);
                        retmap.put("success", 1);
                        retmap.put("data", azOauthUser);
                        jsonObject = JSONObject.fromObject(retmap, config);
                        return jsonObject.toString();
                    }

                }else{
                    //没有信息，新增用户信息 AzUsers 和Azoauthuser 表
                    AzUsers azUsers1 = new AzUsers();
                    azUsers1.setUser_login(phoneNum);
                    azUsers1.setUser_pass(password);
                    azUsers1.setUser_nicename(phoneNum.substring(phoneNum.length() - 4,phoneNum.length()).concat("rose"));
                    azUsers1.setLast_login_ip(ip);
                    azUsers1.setLast_login_time(new Date());
                    azUsers1.setCreate_time(new Date());
                    azUsers1.setMobile(phoneNum);
                    azUsersMapper.insertSelective(azUsers1);

                    AzOauthUser  azOauthUser = new AzOauthUser();
                    azOauthUser.setHead_img(openheadimg);
                    azOauthUser.setName(openname);
                    azOauthUser.setOpenid(openid);
                    azOauthUser.setFrom("小程序");
                    azOauthUser.setUid(azUsers1.getId());
                    azOauthUser.setLast_login_ip(ip);
                    azOauthUser.setLast_login_time(new Date());
                    azOauthUser.setCreate_time(new Date());
                    azOauthUser.setLogin_times(1);
                    azOauthUser.setMobile(phoneNum);
                    azOauthUserMapper.insertSelective(azOauthUser);



                    retmap.put("success", 1);
                    retmap.put("data", azOauthUser);
                    jsonObject = JSONObject.fromObject(retmap, config);
                    return jsonObject.toString();

                }

            }else{
                //验证码不相同
                retmap.put("success", 0);
                retmap.put("msg", "验证码错误");
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();
            }

        }else{
            retmap.put("success", 0);
            retmap.put("msg", "验证码错误");
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();

        }
    }

    /**
    * @Desc 忘记密码
    * @author yurh
    * @create 2018-04-12 15:03:01
    **/
    public String updatePassword(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String phoneNum=StringUtil.parseString(dataMap.get("phoneNum"));
        String verificationCode=StringUtil.parseString(dataMap.get("verificationCode"));
        String password=StringUtil.parseString(dataMap.get("password"));

        Map<String, Object> retmap = new HashMap<String, Object>();//返回信息
        AzValidatecode validatecode = azUsersMapper.getvalidateCode(verificationCode,phoneNum);//当前有效的验证码
        if(validatecode != null){
            if(verificationCode.equals(validatecode.getCode())){

                Date date = new Date();
                long currenttime = date.getTime();
                long recordtime = validatecode.getCtime().getTime();
                long datetime = (currenttime - recordtime) / (1000);
                if(datetime > 180){
                    retmap.put("success", 0);
                    retmap.put("msg", "此验证码过3分钟有效期");
                    jsonObject = JSONObject.fromObject(retmap, config);
                    return jsonObject.toString();
                }

                azUsersMapper.updatePassword(phoneNum,password);

                retmap.put("success", 1);
                retmap.put("msg", "修改成功");
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();




            }else{
                //验证码不相同
                retmap.put("success", 0);
                retmap.put("msg", "验证码错误");
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();
            }

        }else{
            retmap.put("success", 0);
            retmap.put("msg", "验证码错误");
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();

        }
    }


    /**
    * @Desc 修改用户手机号
    * @author yurh
    * @create 2018-04-12 15:07:21
    **/
    public String updateMobileByUid(JSONObject jobj, HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
        String j=StringUtil.parseString(jobj.get("json"));
        JSONObject dataMap=getStringtoJson(j);
        String userid=StringUtil.parseString(dataMap.get("userid"));
        String mobile=StringUtil.parseString(dataMap.get("mobile"));
        String verificationCode=StringUtil.parseString(dataMap.get("verificationCode"));

        Map<String, Object> retmap = new HashMap<String, Object>();//返回信息
        AzValidatecode validatecode = azUsersMapper.getvalidateCode(verificationCode,mobile);//当前有效的验证码
        if(validatecode != null){
            if(verificationCode.equals(validatecode.getCode())){

                Date date = new Date();
                long currenttime = date.getTime();
                long recordtime = validatecode.getCtime().getTime();
                long datetime = (currenttime - recordtime) / (1000);
                if(datetime > 180){
                    retmap.put("success", 0);
                    retmap.put("msg", "此验证码过3分钟有效期");
                    jsonObject = JSONObject.fromObject(retmap, config);
                    return jsonObject.toString();
                }

                azUsersMapper.updateMobileByUid(userid,mobile);

                retmap.put("success", 1);
                retmap.put("msg", "修改成功");
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();




            }else{
                //验证码不相同
                retmap.put("success", 0);
                retmap.put("msg", "验证码错误");
                jsonObject = JSONObject.fromObject(retmap, config);
                return jsonObject.toString();
            }

        }else{
            retmap.put("success", 0);
            retmap.put("msg", "验证码错误");
            jsonObject = JSONObject.fromObject(retmap, config);
            return jsonObject.toString();

        }
    }


}

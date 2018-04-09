package com.emi.roots.appinterface.service;

import com.alibaba.fastjson.JSON;
import com.emi.page.PageInfo;
import com.emi.page.PagerHelper;
import com.emi.roots.entity.*;
import com.emi.roots.mapper.AzOauthUserMapper;
import com.emi.roots.mapper.AzSlideMapper;
import com.emi.roots.mapper.AzUsersMapper;
import com.emi.roots.util.*;
import com.emi.roots.wechat.bean.UserInfo;
import com.emi.roots.wechat.util.Constants;
import com.emi.roots.wechat.util.WeChat;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static jxl.biff.FormatRecord.logger;

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


//    @Override
//    @Transactional
//    public String followMemservice(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String memserviceid=StringUtil.parseString(dataMap.get("memserviceid"));
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        memserviceMapper.updatefollowednumadd(memserviceid);//关注数量加1
//        memservicefollowMapper.addmemservicefollowInfo(memberid,memserviceid);
//
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//
//    @Override
//    public String getNewsCast(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String citycode=StringUtil.parseString(dataMap.get("citycode"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        //isdefaultdisplay：是否默认显示 1是 0否   ，1:citycode根据当前会员的citycode  0: 当选择具体城市时值为0
//        String isdefaultdisplay=StringUtil.parseString(dataMap.get("isdefaultdisplay"));//是否默认显示
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        List<News> newsList =newsMapper.getFenPageNewsList(pageInfo,citycode,isdefaultdisplay,memberid);
//
//        retmap.put("success", 1);
//        retmap.put("data", newsList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getIndustryNews(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        List<News> newsList =newsMapper.getFenPageIndustryNews(pageInfo);
//
//            retmap.put("success", 1);
//        retmap.put("data", newsList);
//            config.setExcludes(new String[] {  "name" });
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getAdvertImg(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        Imgmanage imgmanage = imgmanageMapper.getAdvertImg();
//
//            retmap.put("success", 1);
//            retmap.put("imgurl", imgmanage.getImgurl());
//            retmap.put("jumpurl", imgmanage.getJumpurl());
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//    }
//
//
    public String getCarouselImgs(JSONObject jobj) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        String v=vertifySgin(jobj);
        if(v!=null){
            return v;
        }
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
//
//    @Override
//    public String getFlashnews(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        List<Flashnews> flashnewsList = flashnewsMapper.getFlashnews();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//            retmap.put("success", 1);
//            retmap.put("data", flashnewsList);
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String forgetPassword(JSONObject jobj) throws Exception{
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String phoneNum=StringUtil.parseString(dataMap.get("phoneNum"));
//        String verificationCode=StringUtil.parseString(dataMap.get("verificationCode"));
//        String password=StringUtil.parseString(dataMap.get("password"));
//
//        Member member = memberMapper.getMemberByphoneNum(phoneNum);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        if(member == null ){
//            retmap.put("success", 0);
//            retmap.put("msg", "会员不存在");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//        }
//
//        Validatecode validatecode = memberMapper.getvalidateCode(verificationCode,phoneNum);
//
//        if(verificationCode.equals(validatecode.getCode())){
//            //验证码相同
//            Date date = new Date();
//            long currenttime = date.getTime();
//            long recordtime = validatecode.getCtime().getTime();
//            long datetime = (currenttime - recordtime) / (1000);
//            if(datetime > 180){
//                retmap.put("success", 0);
//                retmap.put("msg", "此验证码过3分钟有效期");
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//            }
//
//            //修改密码
//            memberMapper.updatePasswordByphone(phoneNum,password);
//
//            retmap.put("success", 1);
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//        }else{
//            //验证码不相同
//            retmap.put("success", 0);
//            retmap.put("msg", "验证码错误");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//
//        }
//
//
//    }
//
//
//    @Override
//    public String getPhonevalidatecode(JSONObject jobj, String securityCode) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String phoneNum=StringUtil.parseString(dataMap.get("phoneNum"));
//
//        Validatecode vaa =new Validatecode();
//        vaa.setCode(securityCode);
//        vaa.setDn(phoneNum);
//        validatecodeMapper.updateCheckCode(vaa);//将之前的设置成失效
//
//
//        //插入新的验证码
//        Validatecode fjcheckcode=new Validatecode();
//        fjcheckcode.setCode(securityCode);
//        fjcheckcode.setCtime(new Timestamp(new Date().getTime()));
//        fjcheckcode.setDn(phoneNum);
//        fjcheckcode.setState(0);
//        validatecodeMapper.insertCheckCode(fjcheckcode);
//
//        if(true){
//            System.out.println(DateUtil.dateToString(new Date(0), "yyyy-MM-dd HH:mm:ss")+" ==> 验证码生成成功:"+securityCode);
//        }else{
//            System.out.println(DateUtil.dateToString(new Date(0), "yyyy-MM-dd HH:mm:ss")+" ==> 验证码生成失败,请检查缓存设置");
//        }
//        //发送短信方法
//        //String efSec = OtoPropertites.get("cache.effectiveTime");
//        //String minute = (Integer.parseInt(efSec)/60)+"";
//        NewSendTemplateSMS.sendRegMsg(phoneNum, new String[]{securityCode,"60"});
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
    @Transactional(rollbackFor = Exception.class)
    public String regist(JSONObject jobj,HttpServletRequest request) throws Exception{
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        String v=vertifySgin(jobj);
        if(v!=null){
            return v;
        }
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
//
//
//    @Override
//    public String getStrongest(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        //对于老的app，可能没有此key
//        if(StringUtil.isNullObject(memberid)){
//            memberid="0";
//        }
//
//        List<Member> memberList = memberMapper.getMemberisstrongest(memberid);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//            retmap.put("success", 1);
//            retmap.put("data", memberList);
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//
//
//
//    }
//
//
//    @Override
//    public String getIndustyList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        List<Industry> industryList = industryMapper.getIndustryListForApp();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//            retmap.put("success", 1);
//            retmap.put("data", industryList);
//            config.setExcludes(new String[] {"id","parentcode","isdelete"});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getMemserviceCommentDetail(JSONObject jobj) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String servicecommentid=StringUtil.parseString(dataMap.get("servicecommentid"));
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        List<Memservicecomment> memservicecommentList = memservicecommentMapper.getFenPageMemservicecommentListByServiceid(pageInfo,servicecommentid);
//            retmap.put("success", 1);
//            retmap.put("data", memservicecommentList);
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//
//
//    }
//
//
//    @Override
//    @Transactional
//    public String replyMemservice(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String serviceid=StringUtil.parseString(dataMap.get("serviceid"));
//        String servicecommentid=StringUtil.parseString(dataMap.get("servicecommentid"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));
//        String content=StringUtil.parseString(dataMap.get("content"));
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        Memservicecomment mc = new Memservicecomment();
//        mc.setServiceid(Integer.parseInt(serviceid));
//        mc.setContent(content);
//        mc.setTomemberid(Integer.parseInt(tomemberid));
//        mc.setMemberid(Integer.parseInt(memberid));
//
//
//        memserviceMapper.updatecommentednum(serviceid);
//
//        memservicecommentMapper.insert(mc);
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String getMemberList(JSONObject jobj) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String citycode=StringUtil.parseString(dataMap.get("citycode"));
//        String industrycode=StringUtil.parseString(dataMap.get("industrycode"));
//        String membertype=StringUtil.parseString(dataMap.get("membertype"));
//
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//
//        StringBuffer sb = new StringBuffer();
//        if(citycode != null && !"".equals(citycode)){
//            sb.append(" and m.citycode = '"+citycode+"' ");
//        }
//        if(industrycode != null && !"".equals(industrycode)){
//            sb.append(" and m.industrycode = '"+industrycode+"' ");
//        }
//
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        //对于老的app 可能没有此key
//        if(StringUtil.isNullObject(memberid)){
//            memberid="0";
//        }
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Member> memberList = new ArrayList<Member>();
//        String tempparam = sb.toString();
//        int tempmembertype= Integer.parseInt(membertype);
//
//        if(tempmembertype == 0){
//            //0所有会员
//            memberList = memberMapper.getFenPageMemberRM(pageInfo,tempparam,memberid);
//
//        }else if(tempmembertype == 1){
//            // 1最强智库（企业家导师）
//            memberList = memberMapper.getFenPageMemberDS(pageInfo,tempparam,memberid);
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//            retmap.put("success", 1);
//            retmap.put("data", memberList);
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//    }
//
//
//
//
//
//    @Override
//    @Transactional
//    public String publishMemservice(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String title=StringUtil.parseString(dataMap.get("title"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String content=StringUtil.parseString(dataMap.get("content"));
//        String typeid=StringUtil.parseString(dataMap.get("typeid"));
//        String nature=StringUtil.parseString(dataMap.get("nature"));
//
//        Memservice ms = new Memservice();
//        ms.setContent(content);
//        ms.setMemberid(Integer.parseInt(memberid));
//        ms.setTypeid(Integer.parseInt(typeid));
//        ms.setNature(Integer.parseInt(nature));
//        ms.setTitle(title);
//
//
//        int publichcount = Common.PUBLISH_NUM;
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        Map  map  =  memserviceMapper.getCountMemserviceByMemberid(memberid);
//        if(map != null ){
//            int count = Integer.parseInt(String.valueOf(map.get("count")));
//            if(count == publichcount){
//                retmap.put("success", 0);
//                retmap.put("msg", "该用户已超每月使用条数4条，无法发布服务");
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//
//            }
//        }
//
//        memserviceMapper.insert(ms);
//        int serviceid= ms.getId();
//
//        //插入图片
//        List<Memserviceimg> listimgs = new ArrayList<Memserviceimg>();
//
//        JSONArray jsonArray = JSONArray.fromObject(dataMap.get("data"));
//        for(int  i = 0;i<jsonArray.size();i++){
//            JSONObject jsonObject1 = JSONObject.fromObject(jsonArray.get(i));
//            String url = jsonObject1.getString("imgurl");
//            Memserviceimg memserviceimg = new Memserviceimg();
//            memserviceimg.setImgurl(url);
//            memserviceimg.setServiceid(serviceid);
//            listimgs.add(memserviceimg);
//        }
//
//        if(listimgs.size()>0){
//            memserviceimgMapper.insertListimgs(listimgs);
//        }
//
//        Member member=new Member();
//        member.setId(Integer.parseInt(memberid));
//        member.setNeednum(new Integer("1"));
//        memberMapper.updateMemNum(member);
//
//        JPushMessage jms=new JPushMessage();
//        jms.setMessage(title);
//        JSONObject njobj=new JSONObject();
//        njobj.put("type",1);
//        njobj.put("id",serviceid);
//        njobj.put("sendmemberid",memberid);
//        jms.setExtras(njobj.toString());
//        jms.setOrderid(serviceid);
//        jms.setOrdersource("service");
//        jpushMapper.addMs(jms);
//
//
//        retmap.put("success", 1);
//        retmap.put("msg", "新增成功");
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getPositionList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String positiontype=StringUtil.parseString(dataMap.get("positiontype"));//职位类型 0注册时企业家职位  1私董会职位
//
//        List<Position> positionList = positionMapper.getPositionList(positiontype);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//            retmap.put("success", 1);
//            retmap.put("data", positionList);
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String perfectAccount(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String name=StringUtil.parseString(dataMap.get("name"));
//        String companyname=StringUtil.parseString(dataMap.get("companyname"));
//        String positionid=StringUtil.parseString(dataMap.get("positionid"));
//
//        memberMapper.updateMemberByperfectAccount(memberid,name,companyname,positionid);
//        Member m=memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("msg", "成功");
//        retmap.put("data",m);
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getMemserviceDetail(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String id=StringUtil.parseString(dataMap.get("id"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        Memservice me = memserviceMapper.getMemserviceById(id,memberid);
//
//
//        //点击一次查看服务详情就增加一次浏览数量
//        memserviceMapper.updateviewnum(me.getId());
//
//        List<Memserviceimg> memserviceimgList = memserviceMapper.getMemserviceimgsByserviceic(me.getId());
//        me.setData(memserviceimgList);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", me);
//        retmap.put("msg", "成功");
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getMemserviceList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        int  nature=Integer.parseInt(StringUtil.parseString(dataMap.get("nature")));
//        int typeid=Integer.parseInt(StringUtil.parseString(dataMap.get("typeid")));
//        String keyword=StringUtil.parseString(dataMap.get("keyword"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        StringBuffer sb = new StringBuffer();
//
//        if(nature >0){
//            sb.append(" and m.nature = '"+nature+"' ");
//
//        }
//
//        if(typeid >0){
//            sb.append(" and m.typeid = '"+typeid+"'  ");
//
//        }
//
//
//        if(keyword != null && !"".equals(keyword)){
//            sb.append(" and m.title like '%"+keyword+"%' ");
//        }
//
//        String tempparam = sb.toString();
//        List<Memservice> memserviceList =  memserviceMapper.getFenPageMemserviceList(pageInfo,tempparam);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//            retmap.put("success", 1);
//        retmap.put("data", memserviceList);
//            config.setExcludes(new String[] {  });
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String getFlashnewsList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Flashnews> list = flashnewsMapper.getFenPageFlashnewsList(pageInfo);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//            retmap.put("success", 1);
//            retmap.put("data", list);
//            config.setExcludes(new String[] {  });
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String getNearbyMember(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String longitude=StringUtil.parseString(dataMap.get("longitude"));//经度
//        String latitude=StringUtil.parseString(dataMap.get("latitude"));//维度
//        String memberid=dataMap.getString("memberid");
//
//
//        double juli = 500000;//预留距离，不用前端传（公里）
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Memberbrcf> memberList=new ArrayList<Memberbrcf>();
//
//        //中国纬度 北纬4°至北纬53°31
//        //中国经度 东经73°40′至东经135°5′
//        BigDecimal longitudeb=StringUtil.stringTobig(longitude);//经度
//        BigDecimal latitudeb=StringUtil.stringTobig(latitude);//维度
//        if(BigDecimal.valueOf(4).compareTo(latitudeb)==-1 && latitudeb.compareTo(BigDecimal.valueOf(53))==-1
//                && BigDecimal.valueOf(73).compareTo(longitudeb)==-1 && longitudeb.compareTo(BigDecimal.valueOf(135))==-1
//                ){
//
//            memberList = memberMapper.getFenPageNearbyMember(pageInfo,longitude,latitude,juli,memberid);
//
//            for(int i = 0 ;i<memberList.size();i++){
//                Memberbrcf member = memberList.get(i);
//                //double lat1, double lng1, double lat2, double lng2
//                double dis = LocationUtils.getDistance(Double.parseDouble(latitude),Double.parseDouble(longitude),Double.parseDouble(member.getLatitude()),Double.parseDouble(member.getLongitude()));
//                member.setDistance(String.valueOf(dis));
//            }
//
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memberList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//
//    @Override
//    @Transactional
//    public String cancelFollowMemservice(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memserviceid=StringUtil.parseString(dataMap.get("memserviceid"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        int ss = memservicefollowMapper.delmemservicefollowBymemserviceidandmemberid(memserviceid,memberid);
//        if(ss==1){
//            memserviceMapper.updatefollowednum(memserviceid);//减少服务关注数量
//            retmap.put("success", 1);
//            retmap.put("msg", "");
//            }else{
//            retmap.put("success", 0);
//            retmap.put("msg", "您已取消");
//         }
//
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    @Transactional
//    public String cancelFollowMember(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        int ss = memfollowMapper.delmemfollowBymemberidandtomemberid(memberid,tomemberid);
//        if(ss==1){
//            memberMapper.updatefansnumBytomemberid(tomemberid);
//            retmap.put("success", 1);
//        }else{
//            retmap.put("success", 0);
//            retmap.put("success", "您已取消");
//        }
//
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//
//
//
//
//
//    @Override
//    public String getAllMemmouth(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        List<Memmouth> memmouthList = memmouthMapper.getFenPageAllMemmouth(pageInfo,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//            retmap.put("success", 1);
//            retmap.put("data", memmouthList);
//            config.setExcludes(new String[] {  });
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//
//    }
//
//
//    @Override
//    public String getImpressionbaseList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        List<Impressionbase> impressionbaseList = impressionbaseMapper.getImpressionbaseList();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//            retmap.put("success", 1);
//            retmap.put("data", impressionbaseList);
//            config.setExcludes(new String[] {  });
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//    }
//
//
//    @Override
//    @Transactional
//    public String addMemimpression(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));
//        Object objc = dataMap.get("data");
//        Object objcdefine = dataMap.get("define");
//        List<Memimpression> list = new ArrayList<Memimpression>();
//
//
//        if(objc != null){
//            JSONArray jsonArray = JSONArray.fromObject(objc);
//
//            for(int i = 0 ;i<jsonArray.size();i++){
//                Memimpression me  = new Memimpression();
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                int baseid = jsonObject1.getInt("baseid");
//                if(baseid >0){
//                    me.setBaseid(baseid);
//                    me.setMemberid(Integer.parseInt(memberid));
//                    me.setTomemberid(Integer.parseInt(tomemberid));
//                    list.add(me);
//                }
//
//            }
//
//        }
//
//
//
//        if(objcdefine != null){
//            JSONArray jsonArrayobjcdefine = JSONArray.fromObject(objcdefine);
//
//            for(int i = 0 ;i<jsonArrayobjcdefine.size();i++){
//                Memimpression me  = new Memimpression();
//                JSONObject jsonObjectdefine= jsonArrayobjcdefine.getJSONObject(i);
//                String  newdata = jsonObjectdefine.getString("newdata");
//                if(newdata != null && !"".equals(newdata)){
//                    me.setMemberid(Integer.parseInt(memberid));
//                    me.setTomemberid(Integer.parseInt(tomemberid));
//                    me.setImpressionname(newdata);
//                    list.add(me);
//                }
//            }
//
//        }
//
//        memimpressionMapper.addMemimpreesionList(list);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    @Transactional
//    public String commentMember(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));
//        String content=StringUtil.parseString(dataMap.get("content"));
//
//        Memmouth me = new Memmouth();
//        me.setTomemberid(Integer.parseInt(tomemberid));
//        me.setMemberid(Integer.parseInt(memberid));
//        me.setContent(content);
//        memmouthMapper.insertSelective(me);
//
//        //修改member的评论数
//        memberMapper.updatecommentnumBymemberid(tomemberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    @Transactional
//    public String agreeMember(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));
//
//        Memagree me = new Memagree();
//        me.setTomemberid(Integer.parseInt(tomemberid));
//        me.setMemberid(Integer.parseInt(memberid));
//
//        memagreeMapper.insertSelective(me);
//
//        //修改member的点赞数
//        memberMapper.updateagreenumBymemberid(tomemberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String getMemberDetail(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String id=StringUtil.parseString(dataMap.get("tomemberid"));//被查看人id
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));//操作人id
//
//        Member member = memberMapper.getMemberinfoById(id);
//        //有效的服务
//        List<Memservice> memserviceList = memserviceMapper.getMemservicevalidBymemberid(id);
//        member.setMemservicelist(memserviceList);
//        //印象列表
//        List<Memimpression> memimpressionList = memimpressionMapper.getmemimpressionList(id);
//        member.setMemimpressionlist(memimpressionList);
//
//        //倒叙取两个评论
//        List<Memmouth> memmouthList = memmouthMapper.getTwoMemmouthBymemberid(id);
//        member.setMemmouthlist(memmouthList);
//
//        //照片
//        List<Memimgs> memimgsList = memberMapper.getMemberimgsListBymemberid(member.getId());
//
//        member.setImgslist(memimgsList);
//
//        //查看是否关注
//        Memfollow mf = memfollowMapper.getFollowInfoBymemberidAndtomemberid(id,memberid);
//        if(mf !=null){
//            //被关注
//            member.setIsfollow(1);
//        }else{
//            member.setIsfollow(0);
//        }
//
//
//        //查看是否点赞
//        Memagree ma = memagreeMapper.getAgreeInfoBymemberidAndtomemberid(id,memberid);
//        if(ma != null){
//            //点赞
//            member.setIsagree(1);
//        }else{
//            member.setIsagree(0);
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("member", member);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    @Transactional
//    public String followMember(JSONObject jobj) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));//
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));//
//
//        Memfollow mf = new Memfollow();
//        mf.setMemberid(Integer.parseInt(memberid));
//        mf.setTomemberid(Integer.parseInt(tomemberid));
//        memfollowMapper.insertSelective(mf);
//
//        memberMapper.addupdatefansnumBytomemberid(tomemberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String getActivityList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String citycode=StringUtil.parseString(dataMap.get("citycode"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        //isdefaultdisplay：是否默认显示 1是 0否   ，1:citycode根据当前会员的citycode  0: 当选择具体城市时值为0
//        String isdefaultdisplay=null;
//        if(dataMap.containsKey("isdefaultdisplay")){//老版本的apk没有此参数
//            isdefaultdisplay=StringUtil.parseString(dataMap.get("isdefaultdisplay"));//是否默认显示
//        }
//
////        StringBuffer sb = new StringBuffer();
////        if(citycode != null && !"".equals(citycode)){
////            sb.append(" and (aty.citycode = '"+citycode+"' or aty.citycode='0') ");
////        }
////        String param = sb.toString();
//
//        List<Activity> list =  activityMapper.getFenPageActivityInterface(pageInfo,citycode,isdefaultdisplay,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", list);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String getMyJoinedActivityList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Activity> list =  activityMapper.getFenPageMyActivityInterface(pageInfo,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", list);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    @Transactional
//    public String addTopicComment(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String topicid=StringUtil.parseString(dataMap.get("topicid"));
//        String topiccommentid=StringUtil.parseString(dataMap.get("topiccommentid"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));
//        String content=StringUtil.parseString(dataMap.get("content"));
//
//
//
//        Topiccomment tc = new Topiccomment();
//        tc.setTomemberid(Integer.parseInt(tomemberid));
//        tc.setMemberid(Integer.parseInt(memberid));
//        tc.setContent(content);
//        tc.setTopicid(Integer.parseInt(topicid));
//        tc.setCommentid(Integer.parseInt(topiccommentid));
//
//        topiccommentMapper.insertSelective(tc);
//        tc.setTopiccommentid(tc.getId());
//
//        if(topiccommentid != null && Integer.parseInt(topiccommentid) >0){
//            //评论id不为空，说明是对评论进行评论，修改主评论的被评论数量
//            topiccommentMapper.updatecommentnum(Integer.parseInt(topiccommentid));
//
//        }
//
//        topicMapper.updateaddCommentnum(topicid);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", tc);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String replyCommentList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String topicid=StringUtil.parseString(dataMap.get("topicid"));
//        String topiccommentid=StringUtil.parseString(dataMap.get("topiccommentid"));
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Topiccomment> topiccommentList = topiccommentMapper.getFenPagereplyCommentList(pageInfo,topicid,topiccommentid,memberid);
//
//        StringBuffer sb=new StringBuffer();
//        for(Topiccomment tm:topiccommentList){
//            sb.append("'"+tm.getId().intValue()+"',");
//        }
//
//        String sbstr=sb.toString();
//        if(sbstr.length()>0){
//            sbstr="("+sbstr.substring(0,sbstr.length()-1)+")";
//
//            List<Topiccommentagree> tgrs=topiccommentagreeMapper.getMemberAgreeList(memberid,sbstr);
//            for(Topiccomment tm:topiccommentList){
//                tm.setIsagree(Integer.valueOf(0));
//                for(Topiccommentagree tg:tgrs){
//                    if(tm.getMemberid().intValue()==tg.getTomemberid().intValue() && tm.getId().intValue()==tg.getCommentid().intValue()){
//                        tm.setIsagree(Integer.valueOf(1));
//                        break;
//                    }
//                }
//            }
//
//        }
//
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", topiccommentList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    /**
//     * 根据用户id获取是否加入塞维斯，是否具有权限    1:有权限   0:无权限
//     * @param memberid
//     * @return
//     */
//    public int getIsServiceMember(String memberid){
//        Member member = memberMapper.selectByPrimaryKey(Integer.parseInt(memberid));
//        if(member != null ){
//            if(member.getServiceorgid() == null){
//                return 0;
//            }else{
//                if((int)member.getServiceorgid() >0){
//                    return 1;
//                }else{
//                    return 0;
//                }
//            }
//
//        }else{
//            return 0;
//        }
//
//    }
//
//    @Override
//    public String getTopicList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String topictypeid=StringUtil.parseString(dataMap.get("topictypeid"));
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        int  isServiceMember = this.getIsServiceMember(memberid);
////        if(isServiceMember == 1){
//
//            StringBuffer sb = new StringBuffer();
//            if(topictypeid != null && !"".equals(topictypeid)){
//                if(Integer.parseInt(topictypeid) >0 ){
//                    sb.append(" and t.topictypeid  = '"+topictypeid+"' ");
//                }
//            }
//
//
//            String param = sb.toString();
//            List<Topic> list = topicMapper.getFenPageTopicList(pageInfo,memberid,param);
//
//            retmap.put("success", 1);
//            retmap.put("data", list);
//            config.setExcludes(new String[] {  });
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
////        }else{
////            retmap.put("success", 0);
////            retmap.put("msg", "不是赛微思会员无法获取信息");
////            config.setExcludes(new String[] {  });
////            jsonObject = JSONObject.fromObject(retmap, config);
////            return jsonObject.toString();
////        }
//
//
//
//    }
//
//
//    @Override
//    public String getTopicCommentList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String id=StringUtil.parseString(dataMap.get("id"));
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Topiccomment> topiccommentList =  topiccommentMapper.getFenPageTopicCommentList(pageInfo,id,memberid);
//
//        StringBuffer sb=new StringBuffer();
//        for(Topiccomment tm:topiccommentList){
//            sb.append("'"+tm.getId().intValue()+"',");
//        }
//
//        String sbstr=sb.toString();
//        if(sbstr.length()>0){
//            sbstr="("+sbstr.substring(0,sbstr.length()-1)+")";
//
//            List<Topiccommentagree> tgrs=topiccommentagreeMapper.getMemberAgreeList(memberid,sbstr);
//            for(Topiccomment tm:topiccommentList){
//                tm.setIsagree(Integer.valueOf(0));
//                for(Topiccommentagree tg:tgrs){
//                    if(tm.getMemberid().intValue()==tg.getTomemberid().intValue() && tm.getId().intValue()==tg.getCommentid().intValue()){
//                        tm.setIsagree(Integer.valueOf(1));
//                        break;
//                    }
//                }
//            }
//
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", topiccommentList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getTopicDetail(JSONObject jobj) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String topicid=StringUtil.parseString(dataMap.get("topicid"));
//        String memberid = StringUtil.parseString(dataMap.get("memberid"));
//
//
//        Topic topic = topicMapper.getTopicDetail(topicid,memberid);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        List<Topicimgs> topicimgsList = topicimgsMapper.gettopicimgsList(topic.getId());
//
//        //增加一次阅读
////        Topicview tv = new Topicview();
////        tv.setMemberid(Integer.parseInt(memberid));
////        tv.setTopicid(Integer.parseInt(topicid));
//        topicMapper.updateaddviewnum(topicid);
//
//
//        topic.setData(topicimgsList);
//        retmap.put("success", 1);
//        retmap.put("data", topic);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//
//    }
//
//
//    @Override
//    @Transactional
//    public String cancelFollowTopic(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String id=StringUtil.parseString(dataMap.get("id"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        int res=topicfollowMapper.cancelFollowTopic(id,memberid);
//        if(res==1){
//            topicMapper.updatefollownumById(id);
//        }
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    @Transactional
//    public String agreeTopiccomment(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String id=StringUtil.parseString(dataMap.get("id"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String tomemberid=StringUtil.parseString(dataMap.get("tomemberid"));
//
//        topiccommentMapper.updateagreenumById(id);
//
//        Topiccommentagree topiccommentagree = new Topiccommentagree();
//        topiccommentagree.setCommentid(Integer.parseInt(id));
//        topiccommentagree.setTomemberid(Integer.parseInt(tomemberid));
//        topiccommentagree.setMemberid(Integer.parseInt(memberid));
//        topiccommentagreeMapper.insertSelective(topiccommentagree);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    @Transactional
//    public String publicTopic(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String title=StringUtil.parseString(dataMap.get("title"));
//        String content=StringUtil.parseString(dataMap.get("content"));
//        String contenturl=StringUtil.parseString(dataMap.get("contenturl"));
//        String topictypeid=StringUtil.parseString(dataMap.get("topictypeid"));
//        String publishmode=StringUtil.parseString(dataMap.get("publishmode"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        Object obj = dataMap.get("data");
//        JSONArray jsonArray =  JSONArray.fromObject(obj);
//
//
//        Topic t  = new Topic();
//        t.setMemberid(Integer.parseInt(memberid));
//        t.setTitle(title);
//        t.setContent(content);
//        t.setContenturl(contenturl);
//        t.setTopictypeid(Integer.parseInt(topictypeid));
//        t.setPublishmode(Integer.parseInt(publishmode));
//
//        topicMapper.insertSelective(t);
//
//        List<Topicimgs> list = new ArrayList<Topicimgs>();
//        for(int i = 0 ;i<jsonArray.size();i++){
//            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//            String iml = jsonObject1.getString("imgurl");
//            Topicimgs ti  = new Topicimgs();
//            ti.setTopicid(t.getId());
//            ti.setImgurl(iml);
//            list.add(ti);
//        }
//
//        if(list !=null && list.size()>0){
//            topicimgsMapper.addTopicimgsList(list);
//
//        }
//
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//
//
//
//
//    @Override
//    @Transactional
//    public String followTopic(JSONObject jobj) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String id=StringUtil.parseString(dataMap.get("id"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        Topicfollow tf = new Topicfollow();
//        tf.setTopicid(Integer.parseInt(id));
//        tf.setMemberid(Integer.parseInt(memberid));
//        topicfollowMapper.insertSelective(tf);
//
//
//        topicMapper.updateaddfollownumById(id);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getTopictypeList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        List<Topictype> topictypeList = topictypeMapper.getTopictypeList();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", topictypeList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String getMyFollowedTopic(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Topic> topicList = topicMapper.getFenPageMyFollowedTopic(pageInfo,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", topicList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//
//    }
//
//    @Override
//    public String getMyIntroduce(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        Member member = memberMapper.getMemberinfoById(memberid);
//        //有效的服务
//        List<Memservice> memserviceList = memserviceMapper.getMemservicevalidBymemberid(memberid);
//        member.setMemservicelist(memserviceList);
//        //印象列表
//        List<Memimpression> memimpressionList = memimpressionMapper.getmemimpressionList(memberid);
//        member.setMemimpressionlist(memimpressionList);
//
//        //倒叙取两个评论
//        List<Memmouth> memmouthList = memmouthMapper.getTwoMemmouthBymemberid(memberid);
//        member.setMemmouthlist(memmouthList);
//
//        //照片
//        List<Memimgs> memimgsList = memberMapper.getMemberimgsListBymemberid(Integer.parseInt(memberid));
//
//        member.setImgslist(memimgsList);
//
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", member);
//        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
//        config.setExcludes(new String[] {"typeid","memberid","ctime","isfollow","flagid","status","publishmemberimg","publishmembername","publishmemberid","typename","isdelete","commentednum","followednum","followid","isValid",  "baseid","impressionname","memberid","tomemberid","ctime"});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    public String getMySimpleIntroduce(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        Member member = memberMapper.getMemberinfoById(memberid);
//
//        JSONObject result=new JSONObject();
//        if(member==null){
//            result.put("isprivate",0);
//        }else{
//
//            int privateFlag=StringUtil.isNullObject(member.getIsprivate())?0:member.getIsprivate().intValue();
//
//            if(privateFlag==0){
//                Orgperson orgperson=orgpersonMapper.getPersonphonenum(member.getPhonenum());//内部职工
//
//                if(orgperson==null){
//                    result.put("isprivate",0);
//                }else{
//                    result.put("isprivate",1);
//                }
//
//            }else {
//                result.put("isprivate",1);
//            }
//
//        }
//
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", result);
//        config.registerJsonValueProcessor(Date.class,new DateJsonValueProcessor("yyyy-MM-dd"));
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    @Transactional
//    public String updateMyIntroduce(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        Member member =  com.alibaba.fastjson.JSONObject.parseObject(j,Member.class);
//
//        Member member1 = memberMapper.selectByPrimaryKey(member.getId());
//        //地址转换成坐标
//        String address = member.getCompanyaddress();
//
//        if(!StringUtil.isNullObject(address)&& !address.equalsIgnoreCase(member1.getCompanyaddress())){
//            String lnglatString = this.getlngandlatByaddress(address);
//            JSONObject jsonObject1 = JSONObject.fromObject(lnglatString);
//            JSONObject resultJsonobject = jsonObject1.getJSONObject("result");
//            if(resultJsonobject !=null && resultJsonobject.size() >0){
//                JSONObject locationJsonobject = resultJsonobject.getJSONObject("location");
//                String lng = locationJsonobject.getString("lng");//经度
//                String lat = locationJsonobject.getString("lat");//维度
//
//                member.setLatitude(lat);
//                member.setLongitude(lng);
//            }
//
//        }
//
////       com.alibaba.fastjson.JSONObject jsonObject1 =  JSON.parseObject(j);
////        String password = jsonObject1.getString("password");//老密码
////        if(member1 != null){
////            if(!password.equals(member1.getPassword())){
////                Map<String, Object> retmap = new HashMap<String, Object>();
////                retmap.put("success", 0);
////                retmap.put("msg", "原密码输入错误");
////                return jsonObject.toString();
////            }else{
////                String newpassword = jsonObject1.getString("newpassword");//新密码
////                memberMapper.updateMemberPassword(newpassword,member.getId());
////            }
////        }
//
//        memberMapper.updateByPrimaryKeySelective(member);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//
//    @Override
//    @Transactional
//    public String updatePassword(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        Member member =  com.alibaba.fastjson.JSONObject.parseObject(j,Member.class);
//        Member member1 = memberMapper.selectByPrimaryKey(member.getId());
//
//        com.alibaba.fastjson.JSONObject jsonObject1 =  JSON.parseObject(j);
//        String password = jsonObject1.getString("password");//老密码
//        if(member1 != null){
//            if(!password.equals(member1.getPassword())){
//                Map<String, Object> retmap = new HashMap<String, Object>();
//                retmap.put("success", 0);
//                retmap.put("msg", "原密码输入错误");
//                return JSONObject.fromObject(retmap, config).toString();
//            }else{
//                String newpassword = jsonObject1.getString("newpassword");//新密码
//                memberMapper.updateMemberPassword(newpassword,member.getId());
//            }
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//
//
//    protected  String getlngandlatByaddress(String address){
//        try {
//            SnCal snCal = new SnCal();
//            Map paramsMap = new LinkedHashMap<String, String>();
//            paramsMap.put("address", address);
//            paramsMap.put("output", "json");
//            paramsMap.put("ak", "nOomyiovnUrFU2PerEXfRZi0WRnDYPEw");
//
//            String paramsStr = snCal.toQueryString(paramsMap);
//
//
//            String wholeStr = new String("/geocoder/v2/?" + paramsStr + "XOHN4GcwmsbGPuljwRHluL8Nu9SmeESL");
//            System.out.println(wholeStr);
//
//
//            String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
//            System.out.println(tempStr);
//            String sn = snCal.MD5(tempStr);
//            System.out.println(snCal.MD5(tempStr));
//
//
//            String url = "http://api.map.baidu.com/geocoder/v2/?address="+URLEncoder.encode(address,"UTF-8")+"&output=json&ak=nOomyiovnUrFU2PerEXfRZi0WRnDYPEw&sn="+sn;
//
//            // get请求返回结果
//            JSONObject jsonResult = null;
//            CloseableHttpClient client = HttpClients.createDefault();
//            // 发送get请求
//            HttpGet request = new HttpGet(url);
//            // 设置请求和传输超时时间
//            RequestConfig requestConfig = RequestConfig.custom()
//                    .setSocketTimeout(2000).setConnectTimeout(2000).build();
//            request.setConfig(requestConfig);
//            try {
//                CloseableHttpResponse response = client.execute(request);
//
//                //请求发送成功，并得到响应
//                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//                    //读取服务器返回过来的json字符串数据
//                    HttpEntity entity = response.getEntity();
//                    String strResult = EntityUtils.toString(entity, "utf-8");
//                    //把json字符串转换成json对象
//
//                    System.out.println(strResult);
//                    return strResult;
////                    jsonResult = JSONObject.parseObject(strResult);
//                } else {
//                    logger.error("get请求提交失败:" + url);
//                }
//            } catch (IOException e) {
//                logger.error("get请求提交失败:" + url, e);
//            } finally {
//                request.releaseConnection();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//
//    }
//
//
//    public static void main(String[] args){
//        try {
//
////            SnCal snCal = new SnCal();
////            Map paramsMap = new LinkedHashMap<String, String>();
////            paramsMap.put("address", "江苏省南通市港闸区幸福新城软通动力");
////            paramsMap.put("output", "json");
////            paramsMap.put("ak", "nOomyiovnUrFU2PerEXfRZi0WRnDYPEw");
////
////            String paramsStr = snCal.toQueryString(paramsMap);
////
////
////            String wholeStr = new String("/geocoder/v2/?" + paramsStr + "XOHN4GcwmsbGPuljwRHluL8Nu9SmeESL");
////            System.out.println(wholeStr);
////
////
////            String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
////            System.out.println(tempStr);
////            String sn = snCal.MD5(tempStr);
////            System.out.println(snCal.MD5(tempStr));
////
////
////            String url = "http://api.map.baidu.com/geocoder/v2/?address="+URLEncoder.encode("江苏省南通市港闸区幸福新城软通动力","UTF-8")+"&output=json&ak=nOomyiovnUrFU2PerEXfRZi0WRnDYPEw&sn="+sn;
////
////            // get请求返回结果
////            JSONObject jsonResult = null;
////            CloseableHttpClient client = HttpClients.createDefault();
////            // 发送get请求
////            HttpGet request = new HttpGet(url);
////            // 设置请求和传输超时时间
////            RequestConfig requestConfig = RequestConfig.custom()
////                    .setSocketTimeout(2000).setConnectTimeout(2000).build();
////            request.setConfig(requestConfig);
////            try {
////                CloseableHttpResponse response = client.execute(request);
////
////                //请求发送成功，并得到响应
////                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
////                    //读取服务器返回过来的json字符串数据
////                    HttpEntity entity = response.getEntity();
////                    String strResult = EntityUtils.toString(entity, "utf-8");
////                    //把json字符串转换成json对象
////                    System.out.println(strResult);
//////                    jsonResult = JSONObject.parseObject(strResult);
////                } else {
////                    logger.error("get请求提交失败:" + url);
////                }
////            } catch (IOException e) {
////                logger.error("get请求提交失败:" + url, e);
////            } finally {
////                request.releaseConnection();
////            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
//
//    @Override
//    public String getMyPublishedTopic(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        List<Topic> topicList = topicMapper.getFenPageMyPublishedTopic(pageInfo,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", topicList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getMyFollowedNewsList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        List<News> newsList = newsMapper.getFenPageMyFollowedNewsList(pageInfo,memberid);
//
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", newsList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getMyFollowedActivityList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        List<Activity> activityList = activityMapper.getFenPageMyFollowedActivityList(pageInfo,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", activityList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String getMyFollowedServiceList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Memservice> memserviceList = memserviceMapper.getFenPageMyFollowedServiceList(pageInfo,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memserviceList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getMyPublishedServiceList(JSONObject jobj) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Memservice> memserviceList = memserviceMapper.getFenPageMyPublishedServiceList(pageInfo,memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memserviceList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//    @Override
//    public String getMyRecommendedMemberList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//        List<Member> memberList = memberMapper.getFenPageMyRecommendedMemberList(pageInfo,memberid);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memberList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//    @Override
//    public String getMyFollowedMember(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//        List<Member> memberList = memberMapper.getFenPageMyFollowedMember(pageInfo,memberid);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memberList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getCityList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        List<City> cityList = orgMapper.getCityList();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", cityList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String setIspublic(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String ispublic=StringUtil.parseString(dataMap.get("ispublic"));
//
//        memberMapper.updateSetIspublic(memberid,ispublic);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getMemservicetypeList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        List<Memservicetype> memservicetypeList = memservicetypeMapper.getMemservicetypeList();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memservicetypeList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//    @Override
//    public String getMemserviceflagList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//
//        List<Memserviceflag> memserviceflagList = memserviceflagMapper.getMemserviceflagList();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memserviceflagList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getMemserviceAuthNumAndUseNum(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        int servicenum = 0;
//        Map map = memberauthMapper.getAuthnumByMemberid(memberid);
//        if(map == null){
//            servicenum = 4;
//        }else{
//            Object obj = map.get("servicenum");
//            if(obj != null ){
//                servicenum = Integer.parseInt(String.valueOf(obj));
//            }else{
//                servicenum=4;
//            }
//        }
//
//
//
//
//        Map map2 = memberMapper.getUsedNumBymemberid(memberid);
//        int usenum = Integer.parseInt(String.valueOf(map2.get("usenum")));
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("servicenum", servicenum);
//        retmap.put("usenum", usenum);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//    @Override
//    public String getMemserviceCommentList(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String pageIndex=StringUtil.parseString(dataMap.get("pageIndex"));
//        String id=StringUtil.parseString(dataMap.get("id"));
//        PageInfo pageInfo= PagerHelper.getPageInfo(PageInfo.PAGESIZE, pageIndex);
//
//
//        List<Memservicecomment> memservicecommentList = memservicecommentMapper.getFenPageMemserviceCommentList(pageInfo,id);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memservicecommentList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String delMemimgsById(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String id=StringUtil.parseString(dataMap.get("id"));
//        memberMapper.delMemimgsByid(id);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String updateMemimgsByMemberid(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
////        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        String imgslist=StringUtil.parseString(dataMap.get("imgslist"));
//        List<Memimgs> memimgsList= JSON.parseArray(imgslist,Memimgs.class);
//
//        memberMapper.addMemimgsList(memimgsList);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("imgslist", memimgsList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    @Transactional
//    public String addMemberMouth(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        Memmouth  memmouth = JSON.parseObject(j,Memmouth.class);
//        memmouthMapper.insertSelective(memmouth);
//        memberMapper.addupdatecommentnum(memmouth.getTomemberid());
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getCommumicationBookPrivate(JSONObject jobj) {
//        //获取私董通讯录
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        Member mymember=memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
//
//        //对于老的app 可能没有此key
//        if(StringUtil.isNullObject(memberid)){
//            memberid="0";
//        }
//
//        List<Map> memberList=new ArrayList<Map>();
//        if(StringUtil.isNullObject(mymember.getServiceorgid()) && StringUtil.isNullObject(mymember.getTitlecode()) ) {
//            //不会查联系人
//        } else{
//            String citycode=mymember.getCitycode();
//            if(citycode.equalsIgnoreCase("0")){//全国为0 显示所有的
//                citycode=null;
//            }
//            memberList=memberMapper.getCommumicationBookPrivate(citycode,memberid);
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memberList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String getCommumicationBookService(JSONObject jobj) {
//        //获取塞维斯员工通讯录
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        Member mymember=memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
//
//        //对于老的app 可能没有此key
//        if(StringUtil.isNullObject(memberid)){
//            memberid="0";
//        }
//
//        List<Orgperson> orgpersonList=new ArrayList<Orgperson>();
//        if(StringUtil.isNullObject(mymember.getServiceorgid()) && StringUtil.isNullObject(mymember.getTitlecode()) ) {
//            //不会查联系人
//        } else{
//            String citycode=mymember.getCitycode();
//            if(citycode.equalsIgnoreCase("0")){//全国为0 显示所有的
//                citycode=null;
//            }
//            orgpersonList = memberMapper.getCommumicationBookService(citycode,memberid);
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", orgpersonList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
    @Transactional(rollbackFor = Exception.class)
    public String wxLogin(JSONObject jobj) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        String v=vertifySgin(jobj);
        if(v!=null){
            return v;
        }
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
        String v=vertifySgin(jobj);
        if(v!=null){
            return v;
        }
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
//    //创建账户
//    private void createAccount(String unionid,Member currentMem){
//        //增加账户开始
//        Basecommonmember unionidAccount=basecommonmemberMapper.getbcByUnionid(unionid);//根据union查询公共账户信息
//
//        if(unionidAccount==null){//不存在公共账户记录
//            Basecommonmember bcuuid=basecommonmemberMapper.getbcByOnlyUuid(currentMem.getCommonuuid().toString());//根据uuid公共账户查询
//
//            //更新unionid
//            if(bcuuid!=null && StringUtil.isNullObject(bcuuid.getUnionid())){
//                basecommonmemberMapper.uptUnionid(unionid,bcuuid.getId().toString());
//            }
//
//
//        }else{//存在公共账户记录
//            Basecommonmember bcuuid=basecommonmemberMapper.getbcByUuidUnionidIsnull(currentMem.getCommonuuid().toString());//根据uuid查询
//
//            if(bcuuid!=null){
//                basecommonmemberMapper.uptDelete(bcuuid.getId().toString());
//
//                //修改账户金额
//                if(StringUtil.isNullObject(bcuuid.getAccountbalance())){
//                    bcuuid.setAccountbalance(new BigDecimal(0));
//                }
//                basecommonmemberMapper.uptPrice(bcuuid,unionidAccount.getId().toString());
//                basecommonmemberMapper.uptCommonuuid(currentMem.getCommonuuid(),unionidAccount.getId().toString());
//            }
//
//        }
//        //增加账户结束
//    }
//
//
//
//    public String getImFriends(JSONObject jobj){
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String keyword=StringUtil.parseString(dataMap.get("keyword"));
//
//        List<Map> memberList=memberMapper.memberList(keyword);
//
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memberList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//    @Transactional(rollbackFor = Exception.class)
//    public String agreeFriends(JSONObject jobj) throws Exception{
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//
//        String fromid=StringUtil.parseString(dataMap.get("fromid"));
//        String toid=StringUtil.parseString(dataMap.get("toid"));
//
//        List<Immessagefriends> ids=new ArrayList<Immessagefriends>();
//
//        Immessagefriends immessagefriends=new Immessagefriends();
//        immessagefriends.setFromid(new Integer(fromid));
//        immessagefriends.setToid(new Integer(toid));
//        ids.add(immessagefriends);
//
//        immessagefriends=new Immessagefriends();
//        immessagefriends.setFromid(new Integer(toid));
//        immessagefriends.setToid(new Integer(fromid));
//        ids.add(immessagefriends);
//
//
//        immessagefriendsMapper.insertSelectiveList(ids);
//
//        String agreeRes=imessageRequest.agreeFriends(immessagefriends);
//        JSONObject agreeResJobj=JSONObject.fromObject(agreeRes);
//        if(agreeResJobj.getInt("success")==0){
//            throw new Exception();
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//    @Transactional(rollbackFor = Exception.class)
//    public String deleteFriends(JSONObject jobj) throws Exception{
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//
//        String id=StringUtil.parseString(dataMap.get("id"));
//        String delid=StringUtil.parseString(dataMap.get("delid"));
//
//        Immessagefriends immessagefriends=new Immessagefriends();
//        immessagefriends.setFromid(new Integer(id));
//        immessagefriends.setToid(new Integer(delid));
//        immessagefriendsMapper.relieveFriend(immessagefriends);
//
//        String agreeRes=imessageRequest.deleFriends(immessagefriends);
//        JSONObject agreeResJobj=JSONObject.fromObject(agreeRes);
//        if(agreeResJobj.getInt("success")==0){
//            throw new Exception();
//        }
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//    public String getFriendsList(JSONObject jobj){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        List<Map> memberList=immessagefriendsMapper.getMyFriend(memberid);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", memberList);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//    public String broadcastList(JSONObject jobj){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        List<Broadcast> broadcast=broadcastMapper.getBroadcast();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        retmap.put("success", 1);
//        retmap.put("data", broadcast);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//
//    public String getMyAccount(JSONObject jobj){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//        Member member=memberMapper.selectByPrimaryKey(new Integer(memberid));
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        if(StringUtil.isNullObject(member.getCommonuuid())){
//            retmap.put("accountbalance", "0");
//        }else{
//            Basecommonmember bm=basecommonmemberMapper.getbcByOnlyUuid(member.getCommonuuid());
//            retmap.put("accountbalance", bm.getAccountbalance().toPlainString());
//        }
//
//        retmap.put("success", 1);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String getWxInfor(JSONObject jobj) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        String v=vertifySgin(jobj);
//        if(v!=null){
//            return v;
//        }
//        String j=StringUtil.parseString(jobj.get("json"));
//        JSONObject dataMap=getStringtoJson(j);
//        String code=StringUtil.parseString(dataMap.get("code"));
//        String memberid=StringUtil.parseString(dataMap.get("memberid"));
//
//        WeChatConf weChatConf = new WeChatConf();
//        weChatConf.setWxappid("wx1ca735669a351195");//赛微思开放平台相关数据
//        weChatConf.setWxsecret("0c9e7b12309cd6f02db20c63504bfaa2");
//        UserInfo userInfo = WeChat.getUserInfoByAuth(weChatConf,code);
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        if(userInfo==null){
//            retmap.put("success", 0);
//            retmap.put("msg", "获取用户信息失败");
//            retmap.put("data", userInfo);
//            config.setExcludes(new String[] {  });
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//
//        Member member = memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
//
//        //存在账户合并的风险（微信账户跟登陆不是同一个人） 注释
////        //创建账户开始
////        createAccount(userInfo.getUnionid(),member);
////        //创建账户结束
//
////        Basecommonmember bcommon=basecommonmemberMapper.getbcByOnlyUuid(member.getCommonuuid().toString());
////        if(bcommon!=null && StringUtil.isNullObject(bcommon.getUnionid())){
////            basecommonmemberMapper.uptUnionid(userInfo.getUnionid(),bcommon.getId().toString());
////        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        retmap.put("data", userInfo);
//        config.setExcludes(new String[] {  });
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    //=====================================================songyh   start=======================================================================================
//
//    private String vertifySgin(String param,String sign){
//
//        String publicKey= SignUtils.PUBlICKEY;
//        boolean bool=false;
//        try {
//            bool=SignUtils.verify(param, sign, publicKey,true);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if(!bool){
//            Map<String,Object> retMap = new HashMap<String, Object>();
//            retMap.put("success", 0);
//            retMap.put("msg", "对不起，参数签名错误");
//            return Object2Json.bean2Json2(retMap);
//        }
//        return null;
//
//    }
//
//    @Override
//    public String getActivityDetail(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//
//        String currentPage="1";//默认查第一页
//        PageInfo pageInfo=PagerHelper.getPageInfo(PageInfo.PAGESIZE, currentPage);
//
//        List<Activitysignup> activitysignups=activitysignupMapper.getFenPageSignuped(pageInfo,activityid);
//        retmap.put("data", activitysignups);
//
//        List<Activitycomment> datalist = activitycommentMapper.getActivitycomment(activityid);
//        retmap.put("datalist", datalist);
//
//        Activity activity = activityMapper.selectByPrimaryKey(Integer.valueOf(activityid));
//
//        retmap.put("title",activity.getTitle());
//        retmap.put("activityimg",activity.getActivityimg());
//        retmap.put("place", activity.getPlace());
//        retmap.put("begintime", activity.getBegintime());
//        retmap.put("endtime", activity.getEndtime());
//        retmap.put("activitynum", activity.getActivitynum());
//        retmap.put("fee", activity.getFee());
//        retmap.put("privatefee", activity.getPrivatefee());
//        retmap.put("tutorfee", activity.getTutorfee());
//        retmap.put("publisher", activity.getPublisher());
//        retmap.put("introduce", activity.getIntroduce());
//        retmap.put("signupnum", activity.getSignupnum());
//        retmap.put("commentnum", activity.getCommentnum());
//
//        Activityfollow activityfollow=activityfollowMapper.getActivityfollowByMember(activityid,memberid);
//        Activitysignup activitysignup=activitysignupMapper.getActivitysignupByMember(activityid,memberid);
//
//        retmap.put("isfollow", activityfollow==null?0:1);
//        retmap.put("issignup", activitysignup==null?0:1);
//        retmap.put("contextPath",request.getContextPath());
//        retmap.put("follownum", activity.getFollownum());
//        retmap.put("ispay",activitysignup==null?0:activitysignup.getIspay());
//        retmap.put("isneedpay",activitysignup==null?0:activitysignup.getIsneedpay());
//
//        List<Activityprogram> activityprogram=activityprogramMapper.getActivityRankingAjax(activityid);
//        if(activityprogram.size()>0){
//            retmap.put("haveprogram",1);//是否存在投票节目
//        }else{
//            retmap.put("haveprogram",0);
//        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String getAllJoinedActivityList(HttpServletRequest request) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        String currentPage=request.getParameter("currentPage");
//        PageInfo pageInfo=PagerHelper.getPageInfo(PageInfo.PAGESIZE, currentPage);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//
////        Activity activity = activityMapper.selectByPrimaryKey(Integer.valueOf(activityid));
//        List<Activitysignup> activitysignups=activitysignupMapper.getFenPageSignuped(pageInfo,activityid);
//
//        retmap.put("data", activitysignups);
//        retmap.put("page", pageInfo);
//        retmap.put("contextPath",request.getContextPath());
//
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//
//    }
//
//    @Override
//    @Transactional
//    public String followActivity(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Activityfollow af=new Activityfollow();
//        af.setMemberid(Integer.valueOf(memberid));
//        af.setActivityid(Integer.valueOf(activityid));
//
//        activityfollowMapper.insertSelective(af);
//        activityMapper.updateFollownum(0,activityid);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    @Transactional
//    public String cancelFollowActivity(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        int ri=activityfollowMapper.deleteByPrimaryKeyByMem(activityid,memberid);
//
//        if(ri==1){
//            activityMapper.updateFollownum(1,activityid);
//        }else{
//            retmap.put("success", 0);
//            retmap.put("msg", "已取消关注");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//
//    @Override
//    @Transactional
//    public String joinActivity(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Member m=memberMapper.selectByPrimaryKey(new Integer(memberid));
//        Activity activity=activityMapper.selectByPrimaryKey(new Integer(activityid));
//
//        if(new Date().compareTo(activity.getClosingtime())>0){
//            retmap.put("success", 0);
//            retmap.put("msg", "已过截止日期，不可线上报名");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//
//        Org org=orgMapper.selectByPrimaryKey(activity.getForwhoid());
////        Map childrenOrg=orgMapper.getChildrenOrg(activity.getForwhoid().toString());
//
//        //当前人的身份 1:导师 2:私董 3:VIP 4:注册用户
//        List<String> strs=new ArrayList<String>();
//        if( !StringUtil.isNullObject(m.getIsprivate()) && m.getIsprivate().intValue()==1 && !StringUtil.isNullObject(m.getServiceorgid()) ){
//            strs.add("2");
//        }
//
//        if(!StringUtil.isNullObject(m.getTitlecode())){
//            strs.add("1");
//        }
//
//        if(!StringUtil.isNullObject(m.getMemberlevel()) && m.getMemberlevel()==1){
//            strs.add("3");
//        }
//
//        boolean canjoin=false;//是否有资格加入活动
//
//        //1:导师 2:私董 3:VIP 4:注册用户
//        if(activity.getFortypeids().indexOf("4")!=-1){
//            canjoin=true;
//        }else{
//            if(org.getOrgtype().intValue()==140){ //私董
//
//                if(!StringUtil.isNullObject(m.getIsprivate()) && m.getIsprivate().intValue()==1 && !StringUtil.isNullObject(m.getServiceorgid()) ){
//                    if(activity.getForwhoid().intValue()==m.getServiceorgid().intValue() && isCanJoin(strs,activity.getFortypeids()) ){
//                        canjoin=true;
//                    }
//                }
//
//                if(!canjoin){
//                    retmap.put("success", 0);
//                    retmap.put("msg", "只能"+org.getName()+"成员报名");
//                    jsonObject = JSONObject.fromObject(retmap, config);
//                    return jsonObject.toString();
//                }
//
//            }else if(org.getOrgtype().intValue()==110){ //城市
//                if( (StringUtil.obj2String(m.getCitycode()).equals(org.getCitycode()) || StringUtil.obj2String(m.getCitycode()).equals("0")) && isCanJoin(strs,activity.getFortypeids()) ){
//                    canjoin=true;
//                }
//            }else if(org.getOrgtype().intValue()==100){ //国家
//                if(isCanJoin(strs,activity.getFortypeids()) ){
//                    canjoin=true;
//                }
//            }
//
//            if(!canjoin){
//                String[] aa = activity.getFortypeids().split(",");
//                String ss = "";
//                for(int a=0;a<aa.length;a++){
//                    if(Integer.parseInt(aa[a])==1){
//                        ss="导师";
//                    }
//                    if(Integer.parseInt(aa[a])==2){
//                        ss+="私董";
//                    }
//                    if(Integer.parseInt(aa[a])==3){
//                        ss+="VIP";
//                    }
//                }
//
//                retmap.put("success", 0);
//                retmap.put("msg", "只有"+org.getCity()+ss+"才能报名");
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//            }
//
//
//        }
//
//        Activitysignup activitysignup=new Activitysignup();
//        activitysignup.setActivityid(new Integer(activityid));
//        activitysignup.setMemberid(new Integer(memberid));
//        activitysignup.setIspay(new Integer(0));
//
//
//        if(!StringUtil.isNullObject(m.getTitlecode())){//导师价格
//            activitysignup.setPrice(activity.getTutorfee());
//        }else if(!StringUtil.isNullObject(m.getIsprivate()) && m.getIsprivate().intValue()==1){//赛微思会员价格（私董价格）
//            activitysignup.setPrice(activity.getPrivatefee());
//        }else{//大众价格
//            activitysignup.setPrice(activity.getFee());
//        }
//
//        if((activitysignup.getPrice()==null?new BigDecimal(0):activitysignup.getPrice()).compareTo(new BigDecimal(0))==0){
//            activitysignup.setIsneedpay(new Integer(0));
//        }else {
//            activitysignup.setIsneedpay(new Integer(1));
//        }
//
//        String phonenum=request.getParameter("phonenum");
//        activitysignup.setPhonenum(phonenum);
//
//        String isneedinvoice=request.getParameter("isneedinvoice");
//        activitysignup.setIsneedinvoice(new Integer(isneedinvoice));
//
//        if(isneedinvoice.equals("1")){
//            String invoicephonemun=request.getParameter("invoicephonemun");
//            if(StringUtil.isNullObject(invoicephonemun)){
//                retmap.put("success", 0);
//                retmap.put("msg", "发票联系人不能为空");
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//            }else{
//                activitysignup.setInvoicephonemun(invoicephonemun);
//            }
//
//        }
//
//        activitysignupMapper.insertSelective(activitysignup);
//        activityMapper.updateSignupnum(0,activityid);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    };
//
//    //当前身份是否有权加入活动
//    private boolean isCanJoin(List<String> strs,String fortypeids){
//        boolean isCan=false;
//
//        for(String s:strs){
//            if(fortypeids.indexOf(s)!=-1){
//                return true;
//            }
//        }
//        return isCan;
//    }
//
//
//    @Override
//    public String getSureDetail(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Activity activity = activityMapper.selectByPrimaryKey(Integer.valueOf(activityid));
//        Activitysignup activitysignup=activitysignupMapper.getActivitysignupByMember(activityid,memberid);
//
//        Member m=memberMapper.selectByPrimaryKey(Integer.valueOf(memberid));
//
//        if(!StringUtil.isNullObject(m.getTitlecode())){//导师价格
//            retmap.put("lastfee",activity.getTutorfee());
//        }else if(!StringUtil.isNullObject(m.getServiceorgid())){//赛微思会员价格（私董价格）
//            retmap.put("lastfee",activity.getPrivatefee());
//        }else{//大众价格
//            retmap.put("lastfee",activity.getFee());
//        }
//
//        retmap.put("title",activity.getTitle());
//        retmap.put("begintime", activity.getBegintime());
//        retmap.put("endtime", activity.getEndtime());
//        retmap.put("memname", m.getName());
//        retmap.put("phonenum", m.getPhonenum());
//
//        if(activitysignup!=null){
//            retmap.put("signupfee", activitysignup.getPrice());//报名时的费用
//        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String activityPay(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//        String paytype=request.getParameter("paytype");//支付方式 1微信支付 2支付宝支付 3微信h5支付 4余额支付
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Activitysignup activitysignup=activitysignupMapper.getActivitysignupByMember(activityid,memberid);
//        Map tradenoMap=getTradeno(activitysignup.getId().toString(),memberid,PayUtil.PAYACTIVITY,paytype,activitysignup.getPrice());
//
//        if(tradenoMap!=null && tradenoMap.get("success").toString().equalsIgnoreCase("1")){
//            retmap.put("success", 1);
//            retmap.put("paytype",paytype);
//            retmap.put("data", tradenoMap);
//            retmap.put("msg", "");
//        }else{
//            retmap.put("success", 0);
//            retmap.put("msg", tradenoMap.get("msg").toString());
//        }
//
//        if(retmap.get("success").toString().equalsIgnoreCase("1") && paytype.equalsIgnoreCase("4")){//余额支付
//            Activitysignup record=new Activitysignup();
//            record.setId(activitysignup.getId());
//            record.setIspay(new Integer(1));
//            activitysignupMapper.updateByPrimaryKeySelective(record);//修改支付标志
//        }
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String rechargePay(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("membersign");//签名
//        String paytype=request.getParameter("paytype");//支付方式 1微信支付 2支付宝支付 3微信h5支付 4余额支付
//        String price=request.getParameter("price");//充值金额
//
//        Pattern p = Pattern.compile("^[1-9]\\d*$");
//        Matcher mat = p.matcher(price);
//        if(!mat.find()){
//            retmap.put("success", 0);
//            retmap.put("msg", "充值金额非法");
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Map tradenoMap=getTradeno(null,memberid,PayUtil.PAYRECHARGE,paytype,new BigDecimal(price) );//充值支付
//
//        if(tradenoMap!=null && tradenoMap.get("success").toString().equalsIgnoreCase("1")){
//            retmap.put("success", 1);
//            retmap.put("paytype",paytype);
//            retmap.put("data", tradenoMap);
//            retmap.put("msg", "");
//        }else{
//            retmap.put("success", 0);
//            retmap.put("msg", tradenoMap.get("msg").toString());
//        }
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    //获取微信订单信息
//    //orderid:对应的主键id
//    // memberid:对应的memberid
//    // ordersource:单据来源（活动。。。）
//    // paytype:支付方式 "1","wechatonline" "2","alipayonline" "3","wechath5" 4余额支付
//    //
//    private Map getTradeno(String orderid,String memberid,String ordersource,String paytype,BigDecimal toToPayPrice){
//
//        Map<String,Object> mm=new HashMap<String, Object>();
//        Paytradeno record=new Paytradeno();
//        record.setOrdersource(ordersource);
//        record.setPaytype(new Integer(paytype));
//        record.setMemberid(new Integer(memberid));
//
//        //微信h5支付
//        if(paytype.equalsIgnoreCase("3")){
//            WeChatConf conf=weChatConfMapper.getWechatconf();
//
//            if(ordersource.equals(PayUtil.PAYACTIVITY)){//活动支付
//                Activitysignup activitysignup=activitysignupMapper.selectByPrimaryKey(new Integer(orderid));
//                mm= WeChat.initOrderTradeNo(paytype,activitysignup.getPrice(),conf);
//                //增加tradeno
//                if(mm.containsKey("tradeno")){
//                    record.setOrderid(new Integer(orderid));
//                    record.setPaystate(new Integer(1));
//                    record.setTradeno(mm.get("tradeno").toString());
//                    record.setPaymoney(activitysignup.getPrice());
//                    paytradenoMapper.insertSelective(record);
//                }
//            }else if(ordersource.equals(PayUtil.PAYRECHARGE)){//充值
//                mm= WeChat.initOrderTradeNo(paytype,toToPayPrice,conf);
//                if(mm.containsKey("tradeno")){
//                    record.setPaystate(new Integer(1));
//                    record.setTradeno(mm.get("tradeno").toString());
//                    record.setPaymoney(toToPayPrice);
//                    paytradenoMapper.insertSelective(record);
//                }
//
//            }else if(ordersource.equals(PayUtil.PLEDGEWECHAT) || ordersource.equals(PayUtil.PLEDGEAPP)){//申请vip/私董定金微信支付
//
////                Memapply memapply=memapplyMapper.getMemapply(orderid);
//                mm= WeChat.initOrderTradeNo(paytype,toToPayPrice,conf);
//                //增加tradeno
//                if(mm.containsKey("tradeno")){
//                    record.setOrderid(new Integer(orderid));
//                    record.setPaystate(new Integer(1));
//                    record.setTradeno(mm.get("tradeno").toString());
//                    record.setPaymoney(toToPayPrice);
//                    paytradenoMapper.insertSelective(record);
//                }
//
//            }
//
//            if(mm.containsKey("tradeno")){
//                mm.put("success",1);
//                mm.put("msg","");
//            }else{
//                mm.put("success",0);
//                mm.put("msg","获取tradeno失败");
//            }
//
//            //余额支付
//        }else if(paytype.equalsIgnoreCase("4")){
//
//            //减少个人账户余额
//            Member meb=memberMapper.selectByPrimaryKey(new Integer(memberid));
//            Basecommonmember bm=basecommonmemberMapper.getbcByOnlyUuid(meb.getCommonuuid());
//
//            if(StringUtil.bigTobig(bm.getAccountbalance()).compareTo(toToPayPrice)!=-1 ){
//                record.setOrderid(new Integer(orderid));
//                record.setPaystate(new Integer(2));
//                record.setPaymoney(toToPayPrice);
//                paytradenoMapper.insertSelective(record);
//                basecommonmemberMapper.reduceAccountbalance(toToPayPrice.toPlainString(),meb.getCommonuuid());
//
//                mm.put("success",1);
//                mm.put("msg","");
//            }else{
//                mm.put("success",0);
//                mm.put("msg","余额不足");
//            }
//
//            //公众号支付
//        }else if(paytype.equalsIgnoreCase("1")){
//            WeChatConf conf=weChatConfMapper.getWechatconf();
//
//            if(ordersource.equals(PayUtil.PLEDGEWECHAT)){//100元定金支付
//                mm= WeChat.initOrderTradeNoWechat(paytype,toToPayPrice,conf);
//                //增加tradeno
//                if(mm.containsKey("tradeno")){
//                    record.setOrderid(new Integer(orderid));
//                    record.setPaystate(new Integer(1));
//                    record.setTradeno(mm.get("tradeno").toString());
//                    record.setPaymoney(toToPayPrice);
//                    paytradenoMapper.insertSelective(record);
//                    mm.put("success",1);
//                    mm.put("msg","");
//                }
//            }
//
//
//        }
//
//        return mm;
//    }
//
//
//    @Transactional(rollbackFor=Exception.class)
//    public JSONObject  synNotiyPay(String tradeno,String success,String payuser,String state,String refundprice,WeChatConf conf){
//
//        JSONObject jsonObject=new JSONObject();
//
//        if(state!=null&&!"".equals(state)&&"1".equals(success)){
//
//            Paytradeno paytradeno=paytradenoMapper.getByTradeno(tradeno);
//
//            int statevalue=Integer.parseInt(state);
//            if(paytradeno!=null) {
//                switch (statevalue) {
//                    case 2:
//                        Paytradeno pd=new Paytradeno();
//                        pd.setId(paytradeno.getId());
//                        pd.setPaystate(PayUtil.PAY_SUCCESS);
//                        paytradenoMapper.updateByPrimaryKeySelective(pd);
//
//                        if(paytradeno.getOrdersource().equalsIgnoreCase(PayUtil.PAYACTIVITY)){//修改活动支付状态
//                            Activitysignup record=new Activitysignup();
//                            record.setId(paytradeno.getOrderid());
//                            record.setIspay(new Integer(1));
//                            activitysignupMapper.updateByPrimaryKeySelective(record);
//
//                            Activitysignup activitysignup=activitysignupMapper.selectByPrimaryKey(paytradeno.getOrderid());
//
//                            Map<String, Object> signMap=new HashMap<String, Object>();
//                            signMap.put("activityid",activitysignup.getActivityid());
//                            signMap.put("memberid",paytradeno.getMemberid());
//                            String str=StringUtil.getUrlParamsByMap(signMap);
//
//                            String signStr=SignUtils.sign(str,SignUtils.PRIVATEKEY,true);
//                            try {
//                                signStr=URLEncoder.encode(signStr,"UTF-8");
//                            }catch (Exception e){
//
//                            }
//
//                            jsonObject.put("resultUrl","/front/pagesmanage/activity/activity_detail.html?"+str+"&sign="+signStr);
//
//                        }else if(paytradeno.getOrdersource().equalsIgnoreCase(PayUtil.PAYRECHARGE)){//增加个人账户余额
//                            Member meb=memberMapper.selectByPrimaryKey(paytradeno.getMemberid());
//                            basecommonmemberMapper.addAccountbalance(paytradeno.getPaymoney().toPlainString(),meb.getCommonuuid());
//
//                            Map<String, Object> signMap=new HashMap<String, Object>();
//                            signMap.put("memberid",paytradeno.getMemberid());
//                            String str=StringUtil.getUrlParamsByMap(signMap);
//
//                            String signStr=SignUtils.sign(str,SignUtils.PRIVATEKEY,true);
//                            try {
//                                signStr=URLEncoder.encode(signStr,"UTF-8");
//                            }catch (Exception e){
//
//                            }
//
//                            jsonObject.put("resultUrl","/front/pagesmanage/pay/payinfor.html?"+str+"&sign="+signStr);
//
//                        }else if(paytradeno.getOrdersource().equalsIgnoreCase(PayUtil.PLEDGEWECHAT)){// 100元定金支付-微信公众号支付
//                            Memapply memapply = memberMapper.getMemapplyByOrderId(paytradeno.getOrderid());
//                            memberMapper.updateMemapplyStateByid(paytradeno.getOrderid());
//
////                            Map<String, Object> signMap=new HashMap<String, Object>();
////                            signMap.put("memberid",paytradeno.getMemberid());
////                            String str=StringUtil.getUrlParamsByMap(signMap);
////
////                            String signStr=SignUtils.sign(str,SignUtils.PRIVATEKEY,true);
////                            try {
////                                signStr=URLEncoder.encode(signStr,"UTF-8");
////                            }catch (Exception e){
////
////                            }
//                            jsonObject.put("resultUrl","/mobile/jumpApplySV.do?memberid="+paytradeno.getMemberid());
//
//                        }else if(paytradeno.getOrdersource().equalsIgnoreCase(PayUtil.PLEDGEAPP)){// 100元定金支付-微信h5支付
//                            Memapply memapply = memberMapper.getMemapplyByOrderId(paytradeno.getOrderid());
//                            memberMapper.updateMemapplyStateByid(paytradeno.getOrderid());
//
//                            Map<String, Object> signMap=new HashMap<String, Object>();
//                            signMap.put("memberid",paytradeno.getMemberid());
//                            String str=StringUtil.getUrlParamsByMap(signMap);
//
//                            String signStr=SignUtils.sign(str,SignUtils.PRIVATEKEY,true);
//                            try {
//                                signStr=URLEncoder.encode(signStr,"UTF-8");
//                            }catch (Exception e){
//
//                            }
//                            jsonObject.put("resultUrl","/front/pagesmanage/privatecouncil/sidonghui.html?"+str+"&sign="+signStr);
//
//                        }
//
//                        break;
//                    case 3:
//                        return jsonObject;
//                }
//            }
//        }
//        return jsonObject;
//    }
//
//
//    @Override
//    public String h5login(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        String phoneNum=request.getParameter("phoneNum");
//        String password=request.getParameter("password");
//        password=PasswordUtil.generatePassword(password);
//
//        String prepage=request.getParameter("prepage");
//        String activityid=request.getParameter("activityid");
//
//        Member member = memberMapper.login(phoneNum);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        if (member != null) {
//            if (password != null && password.equals(member.getPassword())) {
//                retmap.put("id", member.getId());
//                retmap.put("headimg", member.getHeadimg());
//                retmap.put("companyname", member.getCompanyname());
//                retmap.put("city", member.getCity());
//                retmap.put("citycode", member.getCitycode());
//                retmap.put("name", member.getName());
//
//                if(prepage.equalsIgnoreCase("activityDetail")){//活动页面
//
//                    Map<String, Object> signMap=new HashMap<String, Object>();
//                    signMap.put("activityid",activityid);
//                    signMap.put("memberid",member.getId());
//                    String str=StringUtil.getUrlParamsByMap(signMap);
//
//                    String signStr=SignUtils.sign(str,SignUtils.PRIVATEKEY,true);
//                    try {
//                        signStr=URLEncoder.encode(signStr,"UTF-8");
//                    }catch (Exception e){
//
//                    }
//
//                    retmap.put("idsign",str+"&sign="+signStr);
//                }
//
//
//
//                retmap.put("success", 1);
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//            } else {
//                retmap.put("msg", "用户名或密码错误！");
//                retmap.put("success", 0);
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//            }
//        } else {
//            retmap.put("success", 0);
//            retmap.put("msg", "用户不存在");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//
//    }
//
//    @Override
//    public String getNewscommentList(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        String newsid=request.getParameter("newsid");//新闻id
//        String memberid=request.getParameter("memberid");//会员id
//
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("newsid",newsid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//       /* String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }*/
//
//        String currentPage=request.getParameter("currentPage");
//        PageInfo pageInfo=PagerHelper.getPageInfo(PageInfo.PAGESIZE, currentPage);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        List<Newscomment> newscomment=newscommentMapper.getFenPageComments(pageInfo,newsid,memberid);
//
//        String publicKey= SignUtils.PRIVATEKEY;
//        for(Newscomment nc:newscomment){
//            String ncstr=nc.getId().toString();
//
//            Map<String, Object> signMp=new HashMap<String, Object>();
//            signMp.put("newscommentid",ncstr);
//            signMp.put("memberid",memberid);
//            String conctm=StringUtil.getUrlParamsByMap(signMp);
//
//            String ncstrsign=SignUtils.sign(conctm,publicKey,true);
//            nc.setCommentidsign(ncstrsign);
//        }
//
//        retmap.put("data", newscomment);
//        retmap.put("page", pageInfo);
//        retmap.put("contextPath",request.getContextPath());
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    @Transactional
//    public String followNews(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String newsid=request.getParameter("newsid");
//        String memberid=request.getParameter("memberid");
//
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("newsid",newsid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Newsfollow nf=new Newsfollow();
//        nf.setMemberid(Integer.valueOf(memberid));
//        nf.setNewsid(Integer.valueOf(newsid));
//
//       //newsfollowMapper.insertSelective(nf);
//        newsfollowMapper.addnewsfollow(nf);
//        newsMapper.updateFollownum(0,newsid);//更新已关注数
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    @Transactional
//    public String cancelFollowNews(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String newsid=request.getParameter("newsid");
//        String memberid=request.getParameter("memberid");
//
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("newsid",newsid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        int ss = newsfollowMapper.deleteByPrimaryKeyByMem(newsid,memberid);
//        if(ss==1){
//            newsMapper.updateFollownum(1,newsid);//更新已关注数
//            retmap.put("success", 1);
//            retmap.put("msg", "");
//        }else{
//            retmap.put("success",0);
//            retmap.put("msg", "您已取消关注");
//        }
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    @Transactional
//    public String agreelFollowComment(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");
//
//        String newscommentid=request.getParameter("newscommentid");
//       // String concttParam=StringUtil.concttParam(commentid,memberid);
//
//        String sign=request.getParameter("sign");//签名
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("newscommentid",newscommentid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//        Newscommentagree na = new  Newscommentagree();
//        na.setNewscommentid(Integer.parseInt(newscommentid));
//        na.setMemberid(Integer.parseInt(memberid));
//        newscommentagreeMapper.addNewscommentagrees(na);
//        newscommentMapper.updatenewscomments(1,newscommentid);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getnewsDetail(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String newsid=request.getParameter("newsid");//新闻id
//        String memberid=request.getParameter("memberid");//会员id
//
//
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("newsid",newsid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        newsMapper.updateclicknum(newsid);
//        News n = newsMapper.getselectnews(newsid);
//        retmap.put("title",n.getTitle());
//        retmap.put("content",n.getContent());
//        retmap.put("ctime",n.getCtime());
//        retmap.put("clicknum",n.getClicknum());
//        retmap.put("commentnum",n.getCommentnum());
//        retmap.put("follownum",n.getFollownum());
//        retmap.put("newstype",n.getNewstype());
//        retmap.put("newsimg",n.getNewsimg());
//        retmap.put("serviceorgid",n.getServiceorgid());
//        retmap.put("city",n.getCity());
//        retmap.put("citycode",n.getCitycode());
//
//        Newsfollow newsfollow=newsfollowMapper.getActivityfollowByMember(newsid,memberid);
//        retmap.put("isfollow", newsfollow==null?0:1);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    @Transactional
//    public String getcomment(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String newsid=request.getParameter("newsid");//新闻id
//        String memberid=request.getParameter("memberid");//会员id
//
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("newsid",newsid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        String content = request.getParameter("incontent");
//        if(StringUtil.isNullObject(content)){
//            retmap.put("success",0);
//            retmap.put("msg","请输入内容");
//            return Object2Json.bean2Json2(retmap);
//        }
//
//        Newscomment n = new  Newscomment();
//        n.setMemberid(Integer.parseInt(memberid));
//        n.setContent(content);
//        n.setNewsid(Integer.parseInt(newsid));
//        newscommentMapper.addNewscomments(n);
//        newsMapper.updateCommentnum(0,newsid);
//
//        Newscomment m = newscommentMapper.getNewscomment(n.getId());
//
//        //是否已点暂
//        String publicKey= SignUtils.PRIVATEKEY;
//
//            String ncstr=m.getId().toString();
//
//          Map<String, Object> signMp=new HashMap<String, Object>();
//            signMp.put("newscommentid",ncstr);
//            signMp.put("memberid",memberid);
//            String conctm=StringUtil.getUrlParamsByMap(signMp);
//
//            String ncstrsign=SignUtils.sign(conctm,publicKey,true);
//            m.setCommentidsign(ncstrsign);
//
//        retmap.put("name",m.getName());
//        retmap.put("headimg",m.getHeadimg());
//        retmap.put("contextPath",request.getContextPath());
//        retmap.put("content",m.getContent());
//        retmap.put("ctime",m.getCtime());
//        retmap.put("agreednum",m.getAgreednum());
//        retmap.put("id",m.getId());
//        retmap.put("agree",m.getAgree());
//        retmap.put("commentidsign",m.getCommentidsign());
//
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    @Transactional
//    public String followCommentcancel(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");
//        String newscommentid=request.getParameter("newscommentid");
//
//        String sign=request.getParameter("sign");//签名
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("newscommentid",newscommentid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        int ss = newscommentagreeMapper.deletenewscommentagrees(newscommentid,memberid);
//        if(ss==1){
//            newscommentMapper.updatenewscomments(0,newscommentid);
//            retmap.put("success", 1);
//            retmap.put("msg", "");
//        }else{
//            retmap.put("success", 0);
//            retmap.put("msg", "您已取消");
//        }
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String addapplication(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("sign");//签名
//        String obj = request.getParameter("obj");
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//        Memapply mem = memapplyMapper.getMemapplys(memberid,obj);//申请记录
//        if(mem!=null){
//            Member m = memberMapper.getMemberById(memberid);
//            String publicKey= SignUtils.PRIVATEKEY;
//            String ncstr=m.getId().toString();
//            Map<String, Object> signMp=new HashMap<String, Object>();
//            signMp.put("memberid",memberid);
//            String conctm=StringUtil.getUrlParamsByMap(signMp);
//            String ncstrsign=SignUtils.sign(conctm,publicKey,true);
//            m.setCommentidsign(ncstrsign);
//
//            retmap.put("memberid",m.getId());
//            retmap.put("phonenum", m.getPhonenum());
//            retmap.put("companyname", m.getCompanyname());
//            retmap.put("positionname", m.getPositionname());
//            retmap.put("name", m.getName());
//            retmap.put("sign", m.getCommentidsign());
//            retmap.put("type", 1);
//            String ss = mem.getProvince()+"-"+mem.getCity();
//            retmap.put("address",ss);
//        }else{
//            Member m = memberMapper.getMemberById(memberid);
//            String publicKey= SignUtils.PRIVATEKEY;
//            String ncstr=m.getId().toString();
//            Map<String, Object> signMp=new HashMap<String, Object>();
//            signMp.put("memberid",memberid);
//            String conctm=StringUtil.getUrlParamsByMap(signMp);
//            String ncstrsign=SignUtils.sign(conctm,publicKey,true);
//            m.setCommentidsign(ncstrsign);
//
//            retmap.put("memberid",m.getId());
//            retmap.put("phonenum", m.getPhonenum());
//            retmap.put("companyname", m.getCompanyname());
//            retmap.put("positionname", m.getPositionname());
//            retmap.put("name", m.getName());
//            retmap.put("sign", m.getCommentidsign());
//            retmap.put("type", 0);
//        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String insertmemapply(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("sign");//签名
//        String name=request.getParameter("name");
//        String phonenum=request.getParameter("phonenum");
//        String companyname=request.getParameter("companyname");
//        String positionname=request.getParameter("positionname");
//        String city=request.getParameter("city");
//        String cityResult3=request.getParameter("cityResult3");
//        String obj=request.getParameter("obj");
//        if(StringUtil.isNullObject(cityResult3)){
//            retmap.put("success",0);
//            retmap.put("msg","请选择所在城市");
//            return Object2Json.bean2Json2(retmap);
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Memapply mem = memapplyMapper.getMemapplys(memberid,obj);
//        if(mem!=null){
//            retmap.put("success",0);
//            retmap.put("msg", "您已经申请过!");
//        }else{
//            Memapply m = new Memapply();
//            String[] cityResul = cityResult3.split("-");
//            String[] citys = city.split(",");
//            m.setProvince(cityResul[0]);
//            m.setProvincecode(citys[0]);
//            m.setCity(cityResul[1]);
//            m.setCitycode(citys[1]);
////            m.setCounty(cityResul[2]);
////            m.setCountycode(citys[2]);
//            m.setMemberid(Integer.parseInt(memberid));
//            m.setName(name);
//            m.setPhonenum(phonenum);
//            m.setCompanyname(companyname);
//            m.setPositionname(positionname);
//            if(Integer.parseInt(obj)==0){
//              m.setType(0);
//            }else {
//                m.setType(1);
//            }
//            memapplyMapper.insertmemapply(m);
//            Memapply mapy = memapplyMapper.getMemapplys(memberid,obj);
//            retmap.put("memapplyid", mapy.getId());
//            retmap.put("success", 1);
//            retmap.put("msg", "");
//        }
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    @Override
//    public String wxinsertmemapply(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");//会员id
//        String name=request.getParameter("name");
//        String phonenum=request.getParameter("phonenum");
//        String companyname=request.getParameter("companyname");
//        String positionname=request.getParameter("positionname");
//        String city=request.getParameter("city");
//        String cityResult3=request.getParameter("cityResult3");
//        String type=request.getParameter("type");
//        if(StringUtil.isNullObject(cityResult3)){
//            retmap.put("success",0);
//            retmap.put("msg","请选择所在城市");
//            return Object2Json.bean2Json2(retmap);
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//
////        String concttParam=StringUtil.getUrlParamsByMap(signMap);
////        String v=vertifySgin(concttParam,sign);
////        if(v!=null){
////            return v;
////        }
//
////        Memapply mem = memapplyMapper.getMemapply(memberid);
//
//        Memapply m = new Memapply();
//        String[] cityResul = cityResult3.split("-");
//        String[] citys = city.split(",");
//        m.setProvince(cityResul[0]);
//        m.setProvincecode(citys[0]);
//        m.setCity(cityResul[1]);
//        m.setCitycode(citys[1]);
////            m.setCounty(cityResul[2]);
////            m.setCountycode(citys[2]);
//        m.setMemberid(Integer.parseInt(memberid));
//        m.setName(name);
//        m.setPhonenum(phonenum);
//        m.setCompanyname(companyname);
//        m.setPositionname(positionname);
//        m.setType(Integer.parseInt(type));
//        memapplyMapper.insertmemapply(m);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        retmap.put("memberid", memberid);
//        retmap.put("memapplyid", m.getId());
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String personalsharepage(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");//会员id
//        String name=request.getParameter("name");//会员名称
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//
//        //老的app没有name,新的app有name,暂时注释
////        signMap.put("name",name);
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
////        String v=vertifySgin(concttParam,sign);
//        //老的app没有name,新的app有name,暂时注释
////        if(v!=null){
////            return v;
////        }
//
//        Member m = memberMapper.getMemberById(memberid);
//        String ss = PasswordUtil.generatePassword(concttParam);
//
//        WeChatConf conf = getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
//        Map shareInfor = wechatJssdkinitWithUrl(conf,request.getParameter("urld"));
//
//
//        shareInfor.put("shareurl",PropertyHolder.getProperty("sys_url")+PropertyHolder.getProperty("sys_name")+"/front/pagesmanage/mine/personalsharepage.html?memberid="+memberid );
//        shareInfor.put("sharedesc",m.getName()+"的个人主页");
//        shareInfor.put("sharetitle","个人主页");
//
//        String toAddName=StringUtil.obj2String(m.getName());
//        try{
//            toAddName=URLEncoder.encode(toAddName,"UTF-8");
//        }catch (Exception e){
//
//        }
//
//        retmap.put("shareInfor", shareInfor);
//        retmap.put("secret", ss);
//        retmap.put("toAddName", toAddName);
//        retmap.put("member",m);
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
    public String getphonenumcode(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        JsonConfig config = JSONConfig.getConfig();
        Map<String, Object> retmap = new HashMap<String, Object>();

        String phonenum=request.getParameter("phonenum");
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

        String id=request.getParameter("id");
        String avatar=request.getParameter("avatar");
        String user_nicename=request.getParameter("user_nicename");
        String mobile=request.getParameter("mobile");

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

        String memberid=request.getParameter("memberid");

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

        String memberid=request.getParameter("memberid");

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





        return null;
    }


//
//    @Override
//    public String getmemberbyid(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid = request.getParameter("memberid");
//        String sign = request.getParameter("sign");
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String ss = PasswordUtil.generatePassword(concttParam);
//        if(!ss.equals(sign)){
//            retmap.put("success",0);
//            retmap.put("msg","签名有误");
//            return Object2Json.bean2Json2(retmap);
//        }
//
//
//        retmap.put("success", 1);
//        retmap.put("msg","");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String updatedatecode(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String phonenum=request.getParameter("phonenum");
//        Validatecode vaa =new Validatecode();
//        vaa.setDn(phonenum);
//        validatecodeMapper.updateCheckCode(vaa);
//
//        retmap.put("success", 1);
//        retmap.put("msg","");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String getregister(HttpServletRequest request) throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String phonenum=request.getParameter("phonenum");
//        String verificationCode=request.getParameter("code");
//        String password=request.getParameter("password");
//        password =  PasswordUtil.generatePassword(password);
//        String memberid=request.getParameter("memberid");//推荐人id
//        String sign=request.getParameter("sign");
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String ss = PasswordUtil.generatePassword(concttParam);
//         if(!ss.equals(sign)){
//            retmap.put("success",0);
//            retmap.put("msg","签名有误");
//            return Object2Json.bean2Json2(retmap);
//        }
//
//        Member member = memberMapper.getMemberByphoneNum(phonenum);
//        if(member != null ){
//            retmap.put("success", 0);
//            retmap.put("msg", "手机号已注册");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//        }
//
//        String result=registCommon(phonenum,verificationCode,password,memberid,null,request);
//        return result;
//
//    }
//
//
//
//    /**
//    * @Desc 微信注册
//    * @author yurh
//    * @create 2017-11-22 15:57:33
//    **/
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String getwxregister(HttpServletRequest request) throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String phonenum=request.getParameter("phonenum");
//        String verificationCode=request.getParameter("code");
//        String password=request.getParameter("password");
//        String md5Password =  PasswordUtil.generatePassword(password);
//
//        //在注册时候直接把这微信的三个参数存储到数据库中
//        String unionid=request.getParameter("unionid");
//        String openname=request.getParameter("openname");
//        String openheadimg=request.getParameter("openheadimg");
//        String openidbyservicegzh=request.getParameter("openidbyservicegzh");
//
////        String memberid=request.getParameter("memberid");//推荐人id
//        Map<String, Object> signMap=new HashMap<String, Object>();
////        signMap.put("memberid",memberid);
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String ss = PasswordUtil.generatePassword(concttParam);
//
//
//        Member member = memberMapper.getMemberByphoneNum(phonenum);
//        if(member != null && !StringUtil.isNullObject(member.getOpenidbyservicegzh())){
//            retmap.put("success", 0);
//            retmap.put("msg", "手机号已注册");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//        }
//
//        if(member!=null && StringUtil.isNullObject(member.getOpenidbyservicegzh())){
//            memberMapper.updateOpenidAndImgByMemberid(String.valueOf(member.getId()),unionid,openname,openheadimg,openidbyservicegzh);
//            retmap.put("success", 1);
//            retmap.put("id", member.getId());
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//        String result=registCommon(phonenum,verificationCode,md5Password,"0",unionid,request);
//
//        com.alibaba.fastjson.JSONObject jsonObject1 = JSON.parseObject(result);
//        String memberid =  jsonObject1.getString("id");//memberid
//        memberMapper.updateOpenidAndImgByMemberid(memberid,unionid,openname,openheadimg,openidbyservicegzh);
//
//
//        return result;
//
//    }
//
//
//
//
//    //公共的注册方法
//    //phonenum:注册号码，verificationCode：注册验证码，password：密码，refereeid：推荐人id
//    public String registCommon(String phonenum,String verificationCode,String password,String refereeid,String unionid,HttpServletRequest request) throws Exception{
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        JsonConfig config = JSONConfig.getConfig();
//        JSONObject jsonObject = new JSONObject();
//
//        Member member = memberMapper.getMemberByphoneNum(phonenum);
//        if(member != null ){
//            retmap.put("success", 0);
//            retmap.put("msg", "手机号已注册");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//        }
//
//        Validatecode validatecode = memberMapper.getvalidateCode(verificationCode,phonenum);//当前有效的验证码
//
//        if(validatecode != null){
//            if(verificationCode.equals(validatecode.getCode())){
//
//                Date date = new Date();
//                long currenttime = date.getTime();
//                long recordtime = validatecode.getCtime().getTime();
//                long datetime = (currenttime - recordtime) / (1000);
//                if(datetime > 180){
//                    retmap.put("success", 0);
//                    retmap.put("msg", "此验证码过3分钟有效期");
//                    jsonObject = JSONObject.fromObject(retmap, config);
//                    return jsonObject.toString();
//                }
//                String ip = this.getIpAddr(request);
//                String city = GetPlaceByIp.getUserCity(ip);
//                String citycode = "";
//                Map map = null;
//                if(!StringUtil.isNullObject(city)){
//                    map=memberMapper.getCityCodeByCityname(city);//查询城市
//                }
//
//                if(map == null){
//                    city="";
//                    citycode = "";
//                }else{
//                    Object obj = map.get("citycode");
//                    if(obj != null){
//                        citycode = String.valueOf(obj);
//                    }else{
//                        city="";
//                        citycode = "";
//                    }
//                }
//
//                Member me = new Member();
//                me.setPhonenum(phonenum);
//                me.setPassword(password);
//                String name = "service_"+phonenum.substring(phonenum.length()-4,phonenum.length());
//                me.setName(name);
//                me.setCity(city);
//                me.setCitycode(citycode);
//                me.setRegistip(ip);
//                me.setCommonuuid(UUID.randomUUID().toString());
//                if(refereeid != null && !"".equals(refereeid)){
//                    me.setRefereeid(Integer.parseInt(refereeid));//推荐人id
//                }
//                memberMapper.addMemberphoneandpassword(me);
//
//                //增加用户账户信息(无法获知unionid,直接增加账户)
//                Basecommonmember record=new Basecommonmember();
//                record.setCommonuuid(me.getCommonuuid());
//                record.setPhonenum(phonenum);
//                if(!StringUtil.isNullObject(unionid)){
//                    record.setUnionid(unionid);
//                }
//                basecommonmemberMapper.insertSelective(record);
//
//                //申请加入环信聊天用户体系
//                try{
//
//                    Imusers imusers=new Imusers();
//                    imusers.setUsername("service"+me.getId());
//                    String res=imessageRequest.joinUsers(imusers);
//
//                    JSONObject resjobj=JSONObject.fromObject(res);
//                    if(resjobj.getInt("success")==0){
//                        throw(new Exception());
//                    }
//
//                }catch (Exception e){
//                    throw e;
//                }
//
//                retmap.put("success", 1);
//                retmap.put("id", me.getId());
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//
//            }else{
//                //验证码不相同
//                retmap.put("success", 0);
//                retmap.put("msg", "验证码错误");
//                jsonObject = JSONObject.fromObject(retmap, config);
//                return jsonObject.toString();
//            }
//
//        }else{
//            retmap.put("success", 0);
//            retmap.put("msg", "验证码错误");
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//    }
//
//
//    @Override
//    public String getactivitycomment(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//
//        String memberid=request.getParameter("memberid");//会员id
//
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        String content = request.getParameter("incontent");
//        if(StringUtil.isNullObject(content)){
//            retmap.put("success",0);
//            retmap.put("msg","请输入内容");
//            return Object2Json.bean2Json2(retmap);
//        }
//
//        Activitycomment n = new  Activitycomment();
//        n.setMemberid(Integer.parseInt(memberid));
//        n.setContent(content);
//        n.setActivityid(Integer.parseInt(activityid));
//        activitycommentMapper.addActivitycomment(n);
//        activityMapper.updatecommentnum(0,activityid);
//
//        Activitycomment m = activitycommentMapper.activitycomments(n.getId());
//        String publicKey= SignUtils.PRIVATEKEY;
//        Map<String, Object> signMp=new HashMap<String, Object>();
//        signMp.put("memberid",memberid);
//        String conctm=StringUtil.getUrlParamsByMap(signMp);
//
//        String ncstrsign=SignUtils.sign(conctm,publicKey,true);
//        m.setCommentidsign(ncstrsign);
//
//        retmap.put("name",m.getName());
//        retmap.put("headimg",m.getHeadimg());
//        retmap.put("contextPath",request.getContextPath());
//        retmap.put("content",m.getContent());
//        retmap.put("ctime",m.getCtime());
//        retmap.put("id",m.getId());
//        retmap.put("commentidsign",m.getCommentidsign());
//
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getActivityListcontent(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        String currentPage=request.getParameter("currentPage");
//        PageInfo pageInfo=PagerHelper.getPageInfo(PageInfo.PAGESIZE, currentPage);
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        List<Activitycomment> activitysignups=activitycommentMapper.getFenPageActivitycomment(pageInfo,activityid);
//
//        retmap.put("data", activitysignups);
//        retmap.put("page", pageInfo);
//        retmap.put("contextPath",request.getContextPath());
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String gettovoteprogam(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        List<Activityprogram> activityprograms=activityprogramMapper.gettovoteprogam(activityid);
//        List<Activityprogramticketresult> activityprogramticketresult=activityprogramticketresultMapper.getListByMemberid(activityid,memberid);
//
//        for(Activityprogram ap:activityprograms){
//            ap.setIsvote(new Integer(0));
//
//            Map<String, Object> signMp=new HashMap<String, Object>();
//            signMp.put("activityprogramid",ap.getId());
//            signMp.put("memberid",memberid);
//            String conctm=StringUtil.getUrlParamsByMap(signMp);
//
//            String ncstrsign=SignUtils.sign(conctm,SignUtils.PRIVATEKEY,true);
//            ap.setSignstr(ncstrsign);
//
//            for(Activityprogramticketresult at:activityprogramticketresult){
//                if(ap.getId().intValue()==at.getActivityprogramid().intValue()){
//                    ap.setIsvote(new Integer(1));
//                    break;
//                }
//            }
//        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//        retmap.put("data", activityprograms);
//        retmap.put("contextPath",request.getContextPath());
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//
//    @Override
//    @Transactional
//    public String sendvote(HttpServletRequest request){
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String activityprogramid=request.getParameter("activityprogramid");//节目id
//
//        String sign=request.getParameter("signstr");//签名
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//        signMap.put("activityprogramid",activityprogramid);
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        List<Activityprogramticketresult> activityprogramticketresult=activityprogramticketresultMapper.getListByMemberid(activityid,memberid);
//        if(activityprogramticketresult.size()>2){
//            retmap.put("success", 0);
//            retmap.put("msg", "只能投3票");
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//
//        }else {
//            Activityprogramticketresult record=new Activityprogramticketresult();
//            record.setActivityid(Integer.valueOf(activityid));
//            record.setActivityprogramid(Integer.valueOf(activityprogramid));
//            record.setMemberid(Integer.valueOf(memberid));
//            activityprogramticketresultMapper.insertSelective(record);
//
//            activityprogramMapper.updateticketnums(activityprogramid);
//        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String getcourseList(HttpServletRequest request) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        String memberid=request.getParameter("memberid");//会员id
//
//        String sign=request.getParameter("sign");//签名
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(concttParam,sign);
//        if(v!=null){
//            return v;
//        }
//
//        String currentPage=request.getParameter("currentPage");
//        PageInfo pageInfo=PagerHelper.getPageInfo(PageInfo.PAGESIZE, currentPage);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        List<Activity> activity = activityMapper.getFenPageActivitys(pageInfo,memberid);
//
//        String publicKey= SignUtils.PRIVATEKEY;
//        for(Activity a:activity){
//            String ncstr=a.getId().toString();
//            Map<String, Object> signMp=new HashMap<String, Object>();
//            signMp.put("activityid",ncstr);
//            signMp.put("memberid",memberid);
//            String conctm=StringUtil.getUrlParamsByMap(signMp);
//
//            String ncstrsign=SignUtils.sign(conctm,publicKey,true);
//            try {
//                a.setSign(URLEncoder.encode(ncstrsign,"UTF-8"));
//            }catch (Exception e){
//
//            }
//        }
//
//        retmap.put("data", activity);
//        retmap.put("page", pageInfo);
//        retmap.put("contextPath",request.getContextPath());
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//
//    }
//
//    @Override
//    public String getsignList(HttpServletRequest request) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Activity activity = activityMapper.selectByPrimaryKey(Integer.valueOf(activityid));
//
//        retmap.put("title",activity.getTitle());
//        retmap.put("activityimg",activity.getActivityimg());
//        retmap.put("place", activity.getPlace());
//        retmap.put("begintime", activity.getBegintime());
//        retmap.put("endtime", activity.getEndtime());
//        retmap.put("activitynum", activity.getActivitynum());
//        retmap.put("fee", activity.getFee());
//        retmap.put("privatefee", activity.getPrivatefee());
//        retmap.put("tutorfee", activity.getTutorfee());
//        retmap.put("publisher", activity.getPublisher());
//        retmap.put("introduce", activity.getIntroduce());
//        retmap.put("signupnum", activity.getSignupnum());
//        retmap.put("commentnum", activity.getCommentnum());
//
//        Activitysignuprecord activitysignup=activitysignuprecordMapper.getActivitysignuprecord(activityid,memberid);
//        if(activitysignup!=null){
//            retmap.put("ispay",1);
//        }else{
//            retmap.put("ispay",0);
//        }
//
//        Activitysignup asp=activitysignupMapper.getActivitysignupByMember(activityid,memberid);
//        if(asp!=null){
//            retmap.put("isneedpay", 1);
//        }else{
//            retmap.put("isneedpay",0);
//        }
//
//        retmap.put("issignup", activitysignup==null?0:1);
//        retmap.put("contextPath",request.getContextPath());
//        retmap.put("follownum", activity.getFollownum());
//
//        List<Activityprogram> activityprogram=activityprogramMapper.getActivityRankingAjax(activityid);
//        if(activityprogram.size()>0){
//            retmap.put("haveprogram",1);//是否存在投票节目
//        }else{
//            retmap.put("haveprogram",0);
//        }
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public String addsign(HttpServletRequest request) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        String activityid=request.getParameter("activityid");//活动id
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("activityidsign");//签名
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("activityid",activityid);
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        activitysignuprecordMapper.addsign(activityid,memberid);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String privateactivityPay(HttpServletRequest request) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memapplyid=request.getParameter("memapplyid");//
//        String memberid=request.getParameter("memberid");//会员id
//        String sign=request.getParameter("sign");//签名
//        String paytype=request.getParameter("paytype");//支付方式 1微信支付 2支付宝支付 3微信h5支付 4余额支付
//        String price=request.getParameter("price");//金额
//
//        try {
//            sign=URLDecoder.decode(sign,"UTF-8");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//
//        String str=StringUtil.getUrlParamsByMap(signMap);
//        String v=vertifySgin(str,sign);
//        if(v!=null){
//            return v;
//        }
//
//        Map tradenoMap=getTradeno(memapplyid,memberid,PayUtil.PLEDGEAPP,paytype,new BigDecimal(price));
//
//        if(tradenoMap!=null && tradenoMap.get("success").toString().equalsIgnoreCase("1")){
//            retmap.put("success", 1);
//            retmap.put("paytype",paytype);
//            retmap.put("data", tradenoMap);
//            retmap.put("msg", "");
//        }else{
//            retmap.put("success", 0);
//            retmap.put("msg", tradenoMap.get("msg").toString());
//        }
//
//        if(retmap.get("success").toString().equalsIgnoreCase("1") && paytype.equalsIgnoreCase("4")){//余额支付
//            memapplyMapper.updatepays(memapplyid);
//        }
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public String prePay(HttpServletRequest request) {
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memapplyid=request.getParameter("memapplyid");
//        String memberid=request.getParameter("memberid");//会员id
////        String sign=request.getParameter("membersign");//签名
//        int paytype=1;//支付方式 1微信支付 2支付宝支付 3微信h5支付 4余额支付
//        String price=request.getParameter("price");//充值金额
//
//        Pattern p = Pattern.compile("^[1-9]\\d*$");
//        Matcher mat = p.matcher(price);
//        if(!mat.find()){
//            retmap.put("success", 0);
//            retmap.put("msg", "充值金额非法");
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//
//        Memapply memapply=memapplyMapper.getMemapplyById(memapplyid);
//        if(memapply.getPay()==1){
//            retmap.put("success", 0);
//            retmap.put("msg", "您已支付");
//            config.setExcludes(new String[] {});
//            jsonObject = JSONObject.fromObject(retmap, config);
//            return jsonObject.toString();
//        }
//
//
//
////        try {
////            sign=URLDecoder.decode(sign,"UTF-8");
////        }catch (Exception e){
////            e.printStackTrace();
////        }
//
////        Map<String, Object> signMap=new HashMap<String, Object>();
////        signMap.put("memberid",memberid);
//
////        String str=StringUtil.getUrlParamsByMap(signMap);
////        String v=vertifySgin(str,sign);
////        if(v!=null){
////            return v;
////        }
//
//        Map tradenoMap=getTradeno(memapplyid,memberid,PayUtil.PLEDGEWECHAT,String.valueOf(paytype),new BigDecimal(price) );
//
//
//        Member member = memberMapper.getMemberOnlyById(memberid);
//
//        if(tradenoMap!=null && tradenoMap.get("success").toString().equalsIgnoreCase("1")){
//            retmap.put("success", 1);
//            retmap.put("paytype",paytype);
//            retmap.put("openid",member.getOpenidbyservicegzh());
//            retmap.put("data", tradenoMap);
//            retmap.put("msg", "");
//        }else{
//            retmap.put("success", 0);
//            retmap.put("msg", tradenoMap.get("msg").toString());
//        }
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//    @Override
//    public WeChatConf getWeChatConfByOrgId(int wechatconfOrgid) {
//
//
//        return userMapper.getWeChatConfByOrgId(wechatconfOrgid);
//    }
//
//    @Override
//    public String homepage(HttpServletRequest request) {
//        String memberid=request.getParameter("memberid");//会员id
//        String sign = request.getParameter("sign");
//
//        /*Map<String, Object> signMap=new HashMap<String, Object>();
//        signMap.put("memberid",memberid);
//        Map<String, Object> retmap = new HashMap<String, Object>();
//        String concttParam=StringUtil.getUrlParamsByMap(signMap);
//        String ss = PasswordUtil.generatePassword(concttParam);
//        if(!ss.equals(sign)){
//            retmap.put("success",0);
//            retmap.put("msg","签名有误");
//            return Object2Json.bean2Json2(retmap);
//        }*/
//
//        WeChatConf conf = userMapper.getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
//        String url= PropertyHolder.getProperty("sys_url")+PropertyHolder.getProperty("sys_name")+"/mobile/personalhomepage.do?memberid="+memberid;
//        String posturl=WeChat.getAuthUrlUserInfo(conf.getWxappid(),url);
//        System.out.println(posturl);
//
//        return posturl;
//    }
//
//    @Override
//    public String personalhomepage(HttpServletRequest request) {
//        String memberid=request.getParameter("memberid");//会员id
//        String code = request.getParameter("code");
//
//        WeChatConf conf = userMapper.getWeChatConfByOrgId(WeChat.WECHATCONF_ORGID);
//        UserInfo userinfo = WeChat.getUserInfoByAuth(conf, code);
//        int ownmemberid=0;
//        if(userinfo==null){
//            ownmemberid=0;
//        }else{
//            Member m = memberMapper.getMemberByOpenidByservicegzh(userinfo.getOpenid());
//
//            if(m!=null){
//                ownmemberid=m.getId();
//            }else{
//                ownmemberid=0;
//            }
//            request.setAttribute("type",1);
//        }
//        request.setAttribute("memberid",memberid);
//        request.setAttribute("ownmemberid",ownmemberid);
//
//        String posturl="/front/pagesmanage/mine/personalsharepage.html?memberid="+memberid+"&ownmemberid="+ownmemberid+"&type="+1;
//        //String posturl = "/front/pagesmanage/weixin/personalsharepage.jsp";
//        return posturl;
//    }
//
//    @Override
//    public Member getMemberByOpenid(String openid) {
//       return memberMapper.getMemberByOpenid(openid);
//    }
//
//    @Override
//    public Member getMemberByOpenidByservicegzh(String openidbyservicegzh){
//        return memberMapper.getMemberByOpenidByservicegzh(openidbyservicegzh);
//    }
//
//    @Override
//    public Member getMemberByUnionid(String unionid){
//        return memberMapper.getMemberByUnionid(unionid);
//    }
//
//    @Override
//    public Member getMemberById(String id){
//        return memberMapper.getMemberOnlyById(id);
//    };
//
//    @Override
//    public List<Member> getbusinesscardclip(Integer memberid) {
//        return memberMapper.getbusinesscardclip(memberid);
//    }
//
//    @Override
//    public String joinkeepe(String memberid, String ownmemberid) {
//        Map m=new HashMap();
//        List<Immessagefriends> g = immessagefriendsMapper.getimmessagefriends(memberid,ownmemberid);
//        if(g!=null&&g.size()>0){
//            m.put("success",0);
//            m.put("msg","已收藏");
//            return Object2Json.bean2Json2(m);
//        }else{
//            List<Immessagefriends> ids=new ArrayList<Immessagefriends>();
//
//            Immessagefriends immessagefriends=new Immessagefriends();
//            immessagefriends.setFromid(new Integer(memberid));
//            immessagefriends.setToid(new Integer(ownmemberid));
//            ids.add(immessagefriends);
//
//            immessagefriends=new Immessagefriends();
//            immessagefriends.setFromid(new Integer(ownmemberid));
//            immessagefriends.setToid(new Integer(memberid));
//            ids.add(immessagefriends);
//
//            immessagefriendsMapper.insertSelectiveList(ids);
//
//            String agreeRes=imessageRequest.agreeFriends(immessagefriends);
//            return agreeRes;
//        }
//
//    }
//
//    @Override
//    public String getbusinesscardclips(HttpServletRequest request) {
//
//        JSONObject jsonObject = new JSONObject();
//        JsonConfig config = JSONConfig.getConfig();
//
//        Map<String, Object> retmap = new HashMap<String, Object>();
//
//        String memberid=request.getParameter("memberid");//会员id
//        String currentPage=request.getParameter("currentPage");
//
//        PageInfo pageInfo=PagerHelper.getPageInfo(PageInfo.PAGESIZE, currentPage);
//
//        retmap.put("success", 1);
//        retmap.put("msg", "");
//
//
//        List<Member> activitysignups=memberMapper.getFenPagebusinesscardclip(pageInfo,memberid);
//
//        retmap.put("data", activitysignups);
//        retmap.put("page", pageInfo);
//        retmap.put("contextPath",request.getContextPath());
//
//
//
//        config.setExcludes(new String[] {});
//        jsonObject = JSONObject.fromObject(retmap, config);
//        return jsonObject.toString();
//    }
//
//
//    //=====================================================songyh   end=========================================================================================
//
//
//
//
//    public Map wechatJssdkinitWithUrl(WeChatConf weChatConf, String url){
//        Map<String,Object> ret= WeChat.signByTicket(weChatConf.getJsdkticket(),url);
//        ret.put("appid",weChatConf.getWxappid());
//        return ret;
//    }
//
//    /**
//     * 获取请求定制路径
//     * @return
//     */
//    public String getRequeUrI(boolean iswithparam,Map<String,String> keyMap,HttpServletRequest request){
//        String url=request.getScheme()+"://";
//        url+=request.getHeader("host");
//        url+=request.getRequestURI();
//        if(iswithparam&&request.getQueryString()!=null){
//            String[] array=request.getQueryString().split("&");
//            if(keyMap!=null&&array!=null&&array.length>0){
//                StringBuffer sb=new StringBuffer();
//                for(String tmp:array){
//                    String[] paramtmp=tmp.split("=");
//                    if(paramtmp!=null&&paramtmp.length==2){
//                        if(keyMap.get(paramtmp[0])==null){
//                            sb.append(tmp).append("&");
//                        }
//                    }
//                }
//                if(sb.length()>0){
//                    sb.append("1=1");
//                    url+="?"+sb.toString();
//                }
//            }else{
//                url+="?"+request.getQueryString();
//            }
//        }
//        return url;
//    }



}

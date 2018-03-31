package com.emi.roots.appinterface.service;


import com.emi.roots.wechat.bean.WeChatConf;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by yuronghao on 2017/8/14.
 */
public interface AppService {
    public String login(JSONObject jsonObject);

//    String followMemservice(JSONObject jobj);
//
//    String getNewsCast(JSONObject jobj);
//
//    String getIndustryNews(JSONObject jobj);
//
//    String getAdvertImg(JSONObject jobj);
//
//    String getCarouselImgs(JSONObject jobj);
//
//    String getFlashnews(JSONObject jobj);
//
//    String forgetPassword(JSONObject jobj) throws Exception;
//
//    String getPhonevalidatecode(JSONObject jobj, String securityCode);
//
//    String regist(JSONObject jobj, HttpServletRequest request)  throws Exception;
//
//    String getStrongest(JSONObject jobj);
//
//    String getIndustyList(JSONObject jobj);
//
//    String getMemserviceCommentDetail(JSONObject jobj);
//
//    String replyMemservice(JSONObject jobj);
//
//    String getMemberList(JSONObject jobj);
//
//    String publishMemservice(JSONObject jobj);
//
//    String getPositionList(JSONObject jobj);
//
//    String perfectAccount(JSONObject jobj);
//
//    String getMemserviceDetail(JSONObject jobj);
//
//    String getMemserviceList(JSONObject jobj);
//
//    String getFlashnewsList(JSONObject jobj);
//
//    String getNearbyMember(JSONObject jobj);
//
//    String cancelFollowMemservice(JSONObject jobj);
//
//    String cancelFollowMember(JSONObject jobj);
//
//    String getAllMemmouth(JSONObject jobj);
//
//    String getImpressionbaseList(JSONObject jobj);
//
//    String addMemimpression(JSONObject jobj);
//
//    String commentMember(JSONObject jobj);
//
//    String agreeMember(JSONObject jobj);
//
//    String getMemberDetail(JSONObject jobj);
//
//    String followMember(JSONObject jobj);
//
//    String getActivityList(JSONObject jobj);
//
//    String getMyJoinedActivityList(JSONObject jobj);
//
//
//    String addTopicComment(JSONObject jobj);
//
//    String replyCommentList(JSONObject jobj);
//
//    String getTopicList(JSONObject jobj);
//
//    String getTopicCommentList(JSONObject jobj);
//
//    String getTopicDetail(JSONObject jobj);
//
//    String cancelFollowTopic(JSONObject jobj);
//
//    String agreeTopiccomment(JSONObject jobj);
//
//    String publicTopic(JSONObject jobj);
//
//    String followTopic(JSONObject jobj);
//
//    String getTopictypeList(JSONObject jobj);
//
//    String getMyFollowedTopic(JSONObject jobj);
//
//
//    String getMyIntroduce(JSONObject jobj);
//
//    String getMySimpleIntroduce(JSONObject jobj);
//
//    String updateMyIntroduce(JSONObject jobj);
//
//    String updatePassword(JSONObject jobj);
//
//    String getMyPublishedTopic(JSONObject jobj);
//
//    String getMyFollowedNewsList(JSONObject jobj);
//
//    String getMyFollowedActivityList(JSONObject jobj);
//
//    String getMyFollowedServiceList(JSONObject jobj);
//
//    String getMyPublishedServiceList(JSONObject jobj);
//
//    String getMyRecommendedMemberList(JSONObject jobj);
//
//
//    String getMyFollowedMember(JSONObject jobj);
//
//    String getCityList(JSONObject jobj);
//
//    String setIspublic(JSONObject jobj);
//
//    String getMemservicetypeList(JSONObject jobj);
//
//    String getMemserviceflagList(JSONObject jobj);
//
//    String getMemserviceAuthNumAndUseNum(JSONObject jobj);
//
//    String getMemserviceCommentList(JSONObject jobj);
//
//    String delMemimgsById(JSONObject jobj);
//
//    String updateMemimgsByMemberid(JSONObject jobj);
//
//    String addMemberMouth(JSONObject jobj);
//
//    String getCommumicationBookPrivate(JSONObject jobj);
//
//    String getCommumicationBookService(JSONObject jobj);
//
//    String wxLogin(JSONObject jobj);
//
//    String wxPerfectDn(JSONObject jobj, HttpServletRequest request) throws Exception;
//
//    String getImFriends(JSONObject jobj) throws Exception;
//
//    String agreeFriends(JSONObject jobj) throws Exception;
//
//    String deleteFriends(JSONObject jobj) throws Exception;
//
//    String getFriendsList(JSONObject jobj) throws Exception;
//
//    String broadcastList(JSONObject jobj);
//
//    String getMyAccount(JSONObject jobj);
//
//    String getWxInfor(JSONObject jobj);
//
//    //=====================================================songyh   start=======================================================================================
//    String getActivityDetail(HttpServletRequest request);
//
//    String getAllJoinedActivityList(HttpServletRequest request);
//
//    String followActivity(HttpServletRequest request);
//
//    String cancelFollowActivity(HttpServletRequest request);
//
//    String joinActivity(HttpServletRequest request);
//
//    String getSureDetail(HttpServletRequest request);
//
//    String activityPay(HttpServletRequest request);
//
//    String rechargePay(HttpServletRequest request);
//
//    public JSONObject synNotiyPay(String tradeno, String success, String payuser, String state, String refundprice, WeChatConf conf);
//
//    String h5login(HttpServletRequest request);
//
//    String getNewscommentList(HttpServletRequest request);
//
//    String followNews(HttpServletRequest request);
//
//    String cancelFollowNews(HttpServletRequest request);
//
//    String agreelFollowComment(HttpServletRequest request);
//
//    String getnewsDetail(HttpServletRequest request);
//
//    String getcomment(HttpServletRequest request);
//
//    String followCommentcancel(HttpServletRequest request);
//
//    String addapplication(HttpServletRequest request);
//
//    String insertmemapply(HttpServletRequest request);
//
//    String personalsharepage(HttpServletRequest request);
//
//    String getphonenumcode(HttpServletRequest request);
//
//    String getmemberbyid(HttpServletRequest request);
//
//    String updatedatecode(HttpServletRequest request);
//
//    String getregister(HttpServletRequest request) throws Exception;
//
//    String getactivitycomment(HttpServletRequest request);
//
//    String getActivityListcontent(HttpServletRequest request);
//
//    String gettovoteprogam(HttpServletRequest request);
//
//    String sendvote(HttpServletRequest request);
//
//    String getcourseList(HttpServletRequest request);
//
//    String getsignList(HttpServletRequest request);
//
//    String addsign(HttpServletRequest request);
//
//    String getwxregister(HttpServletRequest request)  throws Exception;
//
//    String wxinsertmemapply(HttpServletRequest request);
//
//    String privateactivityPay(HttpServletRequest request);
//
//    String prePay(HttpServletRequest request);
//
//    WeChatConf getWeChatConfByOrgId(int wechatconfOrgid);
//
//    String homepage(HttpServletRequest request);
//
//    String personalhomepage(HttpServletRequest request);
//
////    Member getMemberByOpenid(String openid);
////
////    Member getMemberByOpenidByservicegzh(String openidbyservicegzh);
////
////    Member getMemberByUnionid(String unionid);
////
////    Member getMemberById(String id);
////
////    List<Member> getbusinesscardclip(Integer memberid);
//
//    String joinkeepe(String memberid, String ownmemberid);
//
//    String getbusinesscardclips(HttpServletRequest request);


    //=====================================================songyh   end=========================================================================================






}

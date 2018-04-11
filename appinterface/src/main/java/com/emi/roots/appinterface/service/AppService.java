package com.emi.roots.appinterface.service;


import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yuronghao on 2017/8/14.
 */
public interface AppService {
    public String login(JSONObject jsonObject);
//
    String getCarouselImgs(JSONObject jobj);
//
    String regist(JSONObject jobj, HttpServletRequest request)  throws Exception;
//
    String wxLogin(JSONObject jobj);
//
    String wxPerfectDn(JSONObject jobj, HttpServletRequest request) throws Exception;
//
    String getphonenumcode(JSONObject jobj, HttpServletRequest request);

    String updateUserinfo(JSONObject jobj, HttpServletRequest request);

    String followMeUsers(JSONObject jobj, HttpServletRequest request);

    String attentionUsers(JSONObject jobj, HttpServletRequest request);

    String getActivityDetails(JSONObject jobj, HttpServletRequest request);

    String getCompetitionDetails(JSONObject jobj, HttpServletRequest request);

    String getCommentList(JSONObject jobj, HttpServletRequest request);

    String applyCompetition(JSONObject jobj, HttpServletRequest request);

    String cancelLike(JSONObject jobj, HttpServletRequest request);

    String likePost(JSONObject jobj, HttpServletRequest request);

    String cancelLikeComment(JSONObject jobj, HttpServletRequest request);

    String likeComment(JSONObject jobj, HttpServletRequest request);

    String getPostsList(JSONObject jobj, HttpServletRequest request);

    String applyActivity(JSONObject jobj, HttpServletRequest request);

    String publishComment(JSONObject jobj, HttpServletRequest request);

    String publishTopic(JSONObject jobj, HttpServletRequest request);

    String xcxLogin(JSONObject jobj);

    String xcxPerfectDn(JSONObject jobj, HttpServletRequest request);






}

package com.emi.roots.mapper;

import com.emi.roots.entity.AzOauthUser;
import com.emi.roots.entity.AzUsers;
import com.emi.roots.entity.AzValidatecode;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AzUsersMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzUsers record);

    int insertSelective(AzUsers record);

    AzUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzUsers record);

    int updateByPrimaryKey(AzUsers record);

    AzUsers login(@Param("phoneNum") String phoneNum);

    AzOauthUser getAzOauthUserByOpenid(@Param("openid")String openid);

    void updateAzOauthUserWXinfo(@Param("id") Integer id, @Param("headimgurl") String headimgurl, @Param("nickname") String nickname, @Param("azOauthUser")AzOauthUser azOauthUser);


    AzValidatecode getvalidateCode(@Param("verificationCode") String verificationCode,@Param("phoneNum")String phoneNum);

    AzUsers getAzUsersByPhone(@Param("phoneNum") String phoneNum);

    void updateCheckCode(@Param("fjcode")AzValidatecode vaa);

    void insertCheckCode(@Param("fjcheckcode")AzValidatecode fjcheckcode);

    void updateUserinfo(@Param("id")String id, @Param("avatar")String avatar, @Param("user_nicename")String user_nicename, @Param("mobile")String mobile);

    List<AzUsers> getfollowMeUsers(@Param("memberid")String memberid);

    List<AzUsers> attentionUsers(@Param("memberid")String memberid);
}
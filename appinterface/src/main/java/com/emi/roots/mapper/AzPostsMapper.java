package com.emi.roots.mapper;

import com.emi.page.PageInfo;
import com.emi.roots.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AzPostsMapper extends SqlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AzPostsWithBLOBs record);

    int insertSelective(AzPostsWithBLOBs record);

    AzPostsWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AzPostsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AzPostsWithBLOBs record);

    int updateByPrimaryKey(AzPosts record);

    AzPosts getActivityDetails(@Param("matchid") String matchid,@Param("userid")String userid);

    List<AzSofAttachment> getMeterialsByids(@Param("meterialids")String meterialids);

    List<AzGoods> getgoodsByids(@Param("goodsids")String goodsids);

    List<AzUsers> getjoinmember(@Param("id")Long id);

    List<AzReply> getFenPageCommentList(@Param("page") PageInfo pageInfo, @Param("matchid") String matchid, @Param("userid")String userid);

    List<AzPosts> getPostsListHD(@Param("page") PageInfo pageInfo, @Param("param") String s, @Param("termid")int termid);
}
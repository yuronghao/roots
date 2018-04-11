package com.emi.roots.mapper;

import com.emi.roots.entity.AzSignUp;
import org.apache.ibatis.annotations.Param;

public interface AzSignUpMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzSignUp record);

    int insertSelective(AzSignUp record);

    AzSignUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzSignUp record);

    int updateByPrimaryKeyWithBLOBs(AzSignUp record);

    int updateByPrimaryKey(AzSignUp record);

    void deleteAzLike(@Param("matchid") String matchid, @Param("userid") String userid, @Param("tablename")String tablename);

    void updatePostLikeNumsub(@Param("matchid")String matchid);

    void updatePostLikeNumadd(@Param("matchid")String matchid);

    void updateCommentLikenumsub(@Param("matchid")String matchid);
}
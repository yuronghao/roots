package com.emi.roots.mapper;

import com.emi.roots.entity.AzUsers;
import org.apache.ibatis.annotations.Param;

public interface AzUsersMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzUsers record);

    int insertSelective(AzUsers record);

    AzUsers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzUsers record);

    int updateByPrimaryKey(AzUsers record);

    AzUsers login(@Param("phoneNum") String phoneNum);
}
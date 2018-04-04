package com.emi.roots.mapper;

import com.emi.roots.entity.AzFollow;

public interface AzFollowMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzFollow record);

    int insertSelective(AzFollow record);

    AzFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzFollow record);

    int updateByPrimaryKey(AzFollow record);
}
package com.emi.roots.mapper;

import com.emi.roots.entity.AzTermRelationships;

public interface AzTermRelationshipsMapper {
    int deleteByPrimaryKey(Long tid);

    int insert(AzTermRelationships record);

    int insertSelective(AzTermRelationships record);

    AzTermRelationships selectByPrimaryKey(Long tid);

    int updateByPrimaryKeySelective(AzTermRelationships record);

    int updateByPrimaryKey(AzTermRelationships record);
}
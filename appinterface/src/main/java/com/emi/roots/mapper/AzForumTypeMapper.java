package com.emi.roots.mapper;

import com.emi.roots.entity.AzForumType;

public interface AzForumTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzForumType record);

    int insertSelective(AzForumType record);

    AzForumType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzForumType record);

    int updateByPrimaryKey(AzForumType record);
}
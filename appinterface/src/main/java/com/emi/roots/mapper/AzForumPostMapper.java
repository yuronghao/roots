package com.emi.roots.mapper;

import com.emi.roots.entity.AzForumPost;

public interface AzForumPostMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzForumPost record);

    int insertSelective(AzForumPost record);

    AzForumPost selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzForumPost record);

    int updateByPrimaryKeyWithBLOBs(AzForumPost record);

    int updateByPrimaryKey(AzForumPost record);
}
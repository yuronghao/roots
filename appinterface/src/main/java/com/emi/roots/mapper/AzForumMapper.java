package com.emi.roots.mapper;

import com.emi.roots.entity.AzForum;

public interface AzForumMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzForum record);

    int insertSelective(AzForum record);

    AzForum selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzForum record);

    int updateByPrimaryKey(AzForum record);
}
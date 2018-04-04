package com.emi.roots.mapper;

import com.emi.roots.entity.AzPosts;
import com.emi.roots.entity.AzPostsWithBLOBs;

public interface AzPostsMapper extends SqlMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AzPostsWithBLOBs record);

    int insertSelective(AzPostsWithBLOBs record);

    AzPostsWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AzPostsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AzPostsWithBLOBs record);

    int updateByPrimaryKey(AzPosts record);
}
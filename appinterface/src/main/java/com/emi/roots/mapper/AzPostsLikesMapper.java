package com.emi.roots.mapper;

import com.emi.roots.entity.AzPostsLikes;

public interface AzPostsLikesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AzPostsLikes record);

    int insertSelective(AzPostsLikes record);

    AzPostsLikes selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AzPostsLikes record);

    int updateByPrimaryKey(AzPostsLikes record);
}
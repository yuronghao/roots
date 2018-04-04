package com.emi.roots.mapper;

import com.emi.roots.entity.AzLike;

public interface AzLikeMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzLike record);

    int insertSelective(AzLike record);

    AzLike selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzLike record);

    int updateByPrimaryKey(AzLike record);
}
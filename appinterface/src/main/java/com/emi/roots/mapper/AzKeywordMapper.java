package com.emi.roots.mapper;

import com.emi.roots.entity.AzKeyword;

public interface AzKeywordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzKeyword record);

    int insertSelective(AzKeyword record);

    AzKeyword selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzKeyword record);

    int updateByPrimaryKeyWithBLOBs(AzKeyword record);

    int updateByPrimaryKey(AzKeyword record);
}
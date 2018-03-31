package com.emi.roots.mapper;

import com.emi.roots.entity.AzAuthRule;

public interface AzAuthRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzAuthRule record);

    int insertSelective(AzAuthRule record);

    AzAuthRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzAuthRule record);

    int updateByPrimaryKey(AzAuthRule record);
}
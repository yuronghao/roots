package com.emi.roots.mapper;

import com.emi.roots.entity.AzCheckin;

public interface AzCheckinMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzCheckin record);

    int insertSelective(AzCheckin record);

    AzCheckin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzCheckin record);

    int updateByPrimaryKey(AzCheckin record);
}
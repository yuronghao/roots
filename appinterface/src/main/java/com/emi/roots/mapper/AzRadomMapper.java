package com.emi.roots.mapper;

import com.emi.roots.entity.AzRadom;

public interface AzRadomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzRadom record);

    int insertSelective(AzRadom record);

    AzRadom selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzRadom record);

    int updateByPrimaryKey(AzRadom record);
}
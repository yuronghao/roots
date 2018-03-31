package com.emi.roots.mapper;

import com.emi.roots.entity.AzLabel;

public interface AzLabelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzLabel record);

    int insertSelective(AzLabel record);

    AzLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzLabel record);

    int updateByPrimaryKey(AzLabel record);
}
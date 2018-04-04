package com.emi.roots.mapper;

import com.emi.roots.entity.AzPostDigg;

public interface AzPostDiggMapper extends SqlMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(AzPostDigg record);

    int insertSelective(AzPostDigg record);

    AzPostDigg selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzPostDigg record);

    int updateByPrimaryKey(AzPostDigg record);
}
package com.emi.roots.mapper;

import com.emi.roots.entity.AzWeixinMenu;

public interface AzWeixinMenuMapper {
    int deleteByPrimaryKey(Short id);

    int insert(AzWeixinMenu record);

    int insertSelective(AzWeixinMenu record);

    AzWeixinMenu selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(AzWeixinMenu record);

    int updateByPrimaryKey(AzWeixinMenu record);
}
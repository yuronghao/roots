package com.emi.roots.mapper;

import com.emi.roots.entity.AzWeixinOptionsLink;

public interface AzWeixinOptionsLinkMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzWeixinOptionsLink record);

    int insertSelective(AzWeixinOptionsLink record);

    AzWeixinOptionsLink selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzWeixinOptionsLink record);

    int updateByPrimaryKeyWithBLOBs(AzWeixinOptionsLink record);

    int updateByPrimaryKey(AzWeixinOptionsLink record);
}
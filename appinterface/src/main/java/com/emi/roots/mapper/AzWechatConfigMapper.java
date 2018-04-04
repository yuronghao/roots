package com.emi.roots.mapper;

import com.emi.roots.entity.AzWechatConfig;

public interface AzWechatConfigMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzWechatConfig record);

    int insertSelective(AzWechatConfig record);

    AzWechatConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzWechatConfig record);

    int updateByPrimaryKeyWithBLOBs(AzWechatConfig record);
}
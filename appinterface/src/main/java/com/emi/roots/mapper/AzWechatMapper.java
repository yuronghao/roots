package com.emi.roots.mapper;

import com.emi.roots.entity.AzWechat;

public interface AzWechatMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzWechat record);

    int insertSelective(AzWechat record);

    AzWechat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzWechat record);

    int updateByPrimaryKeyWithBLOBs(AzWechat record);

    int updateByPrimaryKey(AzWechat record);
}
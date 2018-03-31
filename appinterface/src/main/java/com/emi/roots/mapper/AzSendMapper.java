package com.emi.roots.mapper;

import com.emi.roots.entity.AzSend;

public interface AzSendMapper {
    int deleteByPrimaryKey(Short id);

    int insert(AzSend record);

    int insertSelective(AzSend record);

    AzSend selectByPrimaryKey(Short id);

    int updateByPrimaryKeySelective(AzSend record);

    int updateByPrimaryKeyWithBLOBs(AzSend record);

    int updateByPrimaryKey(AzSend record);
}
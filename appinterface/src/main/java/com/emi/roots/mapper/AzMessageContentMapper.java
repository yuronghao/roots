package com.emi.roots.mapper;

import com.emi.roots.entity.AzMessageContent;
import com.emi.roots.entity.AzMessageContentWithBLOBs;

public interface AzMessageContentMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzMessageContentWithBLOBs record);

    int insertSelective(AzMessageContentWithBLOBs record);

    AzMessageContentWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzMessageContentWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(AzMessageContentWithBLOBs record);

    int updateByPrimaryKey(AzMessageContent record);
}
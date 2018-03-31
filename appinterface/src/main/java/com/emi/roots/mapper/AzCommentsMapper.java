package com.emi.roots.mapper;

import com.emi.roots.entity.AzComments;

public interface AzCommentsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AzComments record);

    int insertSelective(AzComments record);

    AzComments selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AzComments record);

    int updateByPrimaryKeyWithBLOBs(AzComments record);

    int updateByPrimaryKey(AzComments record);
}
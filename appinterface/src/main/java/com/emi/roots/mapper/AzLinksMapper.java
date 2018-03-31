package com.emi.roots.mapper;

import com.emi.roots.entity.AzLinks;

public interface AzLinksMapper {
    int deleteByPrimaryKey(Long linkId);

    int insert(AzLinks record);

    int insertSelective(AzLinks record);

    AzLinks selectByPrimaryKey(Long linkId);

    int updateByPrimaryKeySelective(AzLinks record);

    int updateByPrimaryKeyWithBLOBs(AzLinks record);

    int updateByPrimaryKey(AzLinks record);
}
package com.emi.roots.mapper;

import com.emi.roots.entity.AzLinks;

public interface AzLinksMapper extends SqlMapper {
    int deleteByPrimaryKey(Long link_id);

    int insert(AzLinks record);

    int insertSelective(AzLinks record);

    AzLinks selectByPrimaryKey(Long link_id);

    int updateByPrimaryKeySelective(AzLinks record);

    int updateByPrimaryKeyWithBLOBs(AzLinks record);

    int updateByPrimaryKey(AzLinks record);
}
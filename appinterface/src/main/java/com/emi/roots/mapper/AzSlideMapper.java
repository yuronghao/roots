package com.emi.roots.mapper;

import com.emi.roots.entity.AzSlide;

public interface AzSlideMapper {
    int deleteByPrimaryKey(Long slideId);

    int insert(AzSlide record);

    int insertSelective(AzSlide record);

    AzSlide selectByPrimaryKey(Long slideId);

    int updateByPrimaryKeySelective(AzSlide record);

    int updateByPrimaryKeyWithBLOBs(AzSlide record);

    int updateByPrimaryKey(AzSlide record);
}
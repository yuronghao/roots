package com.emi.roots.mapper;

import com.emi.roots.entity.AzSlide;

public interface AzSlideMapper extends SqlMapper {
    int deleteByPrimaryKey(Long slide_id);

    int insert(AzSlide record);

    int insertSelective(AzSlide record);

    AzSlide selectByPrimaryKey(Long slide_id);

    int updateByPrimaryKeySelective(AzSlide record);

    int updateByPrimaryKeyWithBLOBs(AzSlide record);

    int updateByPrimaryKey(AzSlide record);
}
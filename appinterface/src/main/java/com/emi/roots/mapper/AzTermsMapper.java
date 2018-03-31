package com.emi.roots.mapper;

import com.emi.roots.entity.AzTerms;

public interface AzTermsMapper {
    int deleteByPrimaryKey(Long termId);

    int insert(AzTerms record);

    int insertSelective(AzTerms record);

    AzTerms selectByPrimaryKey(Long termId);

    int updateByPrimaryKeySelective(AzTerms record);

    int updateByPrimaryKeyWithBLOBs(AzTerms record);

    int updateByPrimaryKey(AzTerms record);
}
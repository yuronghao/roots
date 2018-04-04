package com.emi.roots.mapper;

import com.emi.roots.entity.AzTerms;

public interface AzTermsMapper extends SqlMapper {
    int deleteByPrimaryKey(Long term_id);

    int insert(AzTerms record);

    int insertSelective(AzTerms record);

    AzTerms selectByPrimaryKey(Long term_id);

    int updateByPrimaryKeySelective(AzTerms record);

    int updateByPrimaryKeyWithBLOBs(AzTerms record);

    int updateByPrimaryKey(AzTerms record);
}
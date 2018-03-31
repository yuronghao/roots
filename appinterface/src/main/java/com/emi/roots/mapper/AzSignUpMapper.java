package com.emi.roots.mapper;

import com.emi.roots.entity.AzSignUp;

public interface AzSignUpMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzSignUp record);

    int insertSelective(AzSignUp record);

    AzSignUp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzSignUp record);

    int updateByPrimaryKey(AzSignUp record);
}
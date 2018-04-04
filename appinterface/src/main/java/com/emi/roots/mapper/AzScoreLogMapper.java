package com.emi.roots.mapper;

import com.emi.roots.entity.AzScoreLog;

public interface AzScoreLogMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzScoreLog record);

    int insertSelective(AzScoreLog record);

    AzScoreLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzScoreLog record);

    int updateByPrimaryKey(AzScoreLog record);
}
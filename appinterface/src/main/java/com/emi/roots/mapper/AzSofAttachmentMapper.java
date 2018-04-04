package com.emi.roots.mapper;

import com.emi.roots.entity.AzSofAttachment;

public interface AzSofAttachmentMapper extends SqlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AzSofAttachment record);

    int insertSelective(AzSofAttachment record);

    AzSofAttachment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AzSofAttachment record);

    int updateByPrimaryKey(AzSofAttachment record);
}
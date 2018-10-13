package com.common.dao;

import java.util.List;

import com.common.pojo.Style;

public interface StyleDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Style record);

    int insertSelective(Style record);

    Style selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Style record);

    int updateByPrimaryKey(Style record);
    
    List<Style> getAllStyle();
}
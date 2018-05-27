/*
 * @(#) GoodsMapper.java 2018年05月27日
 *
 * Copyright (c), 2017 赵名阳（shining everyday.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.Goods;

@Repository
public interface GoodsDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);
    
    List<Goods> getAllGoods();
}
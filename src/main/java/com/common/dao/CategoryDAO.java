/*
 * @(#) CategoryMapper.java 2018年05月18日
 *
 * Copyright (c), 2016 深圳市万睿智能科技有限公司（Shenzhen Wan Rui Intelligent Technology Co., Ltd.），
 * 
 * 著作权人保留一切权利，任何使用需经授权。
*/
package com.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.dto.CategoryDTO;
import com.common.pojo.Category;

@Repository
public interface CategoryDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
    
    List<CategoryDTO> selectAll();
}
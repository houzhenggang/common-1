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
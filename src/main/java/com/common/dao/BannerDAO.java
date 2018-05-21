package com.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.Banner;

@Repository
public interface BannerDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);
    
    List<Banner> getAllBanner();
    
    int countRollingBanner();
}
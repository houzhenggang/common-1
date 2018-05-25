package com.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.pojo.Notice;

@Repository
public interface NoticeDAO {
    int deleteByPrimaryKey(Long id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
    
    List<Notice> getAllNotice();
}
package com.demo.dao;

import java.util.List;

import com.demo.entity.News;

public interface NewsMapper {

    int insertSelective(News record);

    int updateByPrimaryKeySelective(News record);

    List<News> selectAllNewsByType();
    
    News selectNewsByPrimary(String id);
    
    void updateNews(String id,String title,String context,String type,String clickrate);
    
    void deleteNewsByPrimaryId(String id);
}
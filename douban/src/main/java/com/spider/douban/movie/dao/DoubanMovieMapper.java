package com.spider.douban.movie.dao;

import com.spider.douban.movie.model.DoubanMovie;
import com.spider.douban.movie.model.DoubanMovieExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoubanMovieMapper {
    int countByExample(DoubanMovieExample example);

    int deleteByExample(DoubanMovieExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DoubanMovie record);

    int insertSelective(DoubanMovie record);

    List<DoubanMovie> selectByExample(DoubanMovieExample example);

    DoubanMovie selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DoubanMovie record, @Param("example") DoubanMovieExample example);

    int updateByExample(@Param("record") DoubanMovie record, @Param("example") DoubanMovieExample example);

    int updateByPrimaryKeySelective(DoubanMovie record);

    int updateByPrimaryKey(DoubanMovie record);
}
package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.pojo.Shopping;
import com.pojo.T_movie;
import com.pojo.User;
import com.pojo.UserRecord;

public interface MovieDao {
	
	
	
	/**
	 * 根据电影id查找电影信息
	 * @param movieId
	 * @return 
	 */
	public T_movie querryMovieById(@Param("movieId")Integer movieId) ;
	
	
	/**
	 * 根据电影名字查找电影信息 并 增加电影点击量
	 * @param movieName
	 * @return
	 */
	public T_movie querryMovieByName(@Param("movieName")String movieName);
	
	
	/**
	 * 分页 获得指定数量的电影的信息
	 * @param rowBounds
	 * @return
	 */
	public List<Map<String ,Object>> querryMovieByRowBounds(RowBounds rowBounds);
	
	
	/** 根据电影id 增加电影点击量
	 * @param movieId
	 */
	public void addClickVol(@Param("movieId")Integer movieId);
}

package com.service.Impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MovieDao;
import com.pojo.T_movie;
import com.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService{
	@Autowired
	private MovieDao movieDao;

	/**
	 * 根据电影id查找电影信息
	 * @param movieId
	 * @return 
	 */
	public T_movie querryMovieById(Integer movieId) {
		T_movie movie=movieDao.querryMovieById(movieId);
		addClickVol(movieId);
		return movie;
	}

	/**
	 * 根据电影名字查找电影信息 并 增加电影点击量
	 * @param movieName
	 * @return
	 */
	public T_movie querryMovieByName(String movieName) {
		T_movie movie=movieDao.querryMovieByName(movieName);
		return movie;
	}

	/**
	 * 分页 获得指定数量的电影的信息
	 * @param rowBounds
	 * @return
	 */
	public List<Map<String ,Object>> querryMovieByRowBounds(RowBounds rowBounds) {
		List<Map<String ,Object>> list=movieDao.querryMovieByRowBounds(rowBounds);
		return list;
	}

	/** 根据电影id 增加电影点击量
	 * @param movieId
	 */
	public void addClickVol(Integer movieId) {
		movieDao.addClickVol(movieId);
		
	}
}

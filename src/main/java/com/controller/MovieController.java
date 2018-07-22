package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.T_movie;
import com.service.MovieService;
import com.utils.EnumUtil;
import com.utils.MessageUtil;

@Controller
@RequestMapping("/Movie")
public class MovieController {
	@Autowired
	private MovieService movieService;
	
	
	/**
	 * 根据电影id查找电影信息
	 * @param movieId
	 * @return 
	 * 
	 */
	@ResponseBody
	@RequestMapping("/querryMovieById")
	public MessageUtil querryMovieById(Integer movieId) {
		T_movie movie=movieService.querryMovieById(movieId);
		return new MessageUtil(EnumUtil.OK,movie,"查找成功");
	}
	
	
	/**
	 * 根据电影名字查找电影信息 并 增加电影点击量
	 * @param movieName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/querryMovieByName")
	public MessageUtil querryMovieByName(String movieName) throws IOException {
		
		
		String name=new String(movieName.getBytes("ISO-8859-1"),"utf-8");
		
		T_movie movie=movieService.querryMovieByName(name);
		return new MessageUtil(EnumUtil.OK,movie,"查找成功");
	}
	
	
	/**
	 * 分页 获得指定数量的电影的信息
	 * @param rowBounds
	 * @return 
	 * eg:{"code":1,"data":list,"msg":"查找成功"}
	 */
	@ResponseBody
	@RequestMapping("/querryMovieByRowBounds")
	public MessageUtil querryMovieByRowBounds(String SpageNum) throws IOException {
		if(SpageNum==null) {
			return new MessageUtil(EnumUtil.NOT_DATA,null,"参数不能为空");
		}
		
		Integer pageNum = null;
		try {
			pageNum=Integer.valueOf(SpageNum);
		}catch(Exception e) {
			return new MessageUtil(EnumUtil.PARAM_ERROR,null,"请输入正确的参数");
		}
		
		int offset=(pageNum-1)*30;
		System.out.println(offset);
		RowBounds rowBounds=new RowBounds(offset, 30);
		List<Map<String ,Object>> list=movieService.querryMovieByRowBounds(rowBounds);
		return new MessageUtil(EnumUtil.OK,list,"查找成功");
	}
		
	
}

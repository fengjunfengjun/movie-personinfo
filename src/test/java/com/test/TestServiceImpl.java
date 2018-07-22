package com.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pojo.T_movie;
import com.pojo.User;
import com.service.MovieService;
import com.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})//记得加classpath
public class TestServiceImpl {
	@Resource
	private  UserService userService;
	@Resource
	private  MovieService movieService;
	
	@Test
	public void testUserService() throws Exception {
		User user=userService.queryUser(1);
		System.out.println(user.getTel());
		
	}
	
	@Test
	public void testMovieService() {
		T_movie movie=movieService.querryMovieById(1);
		System.out.println(movie.getMovieName());
	}

}

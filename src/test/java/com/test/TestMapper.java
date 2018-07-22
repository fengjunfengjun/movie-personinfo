package com.test;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dao.MovieDao;
import com.dao.UserDao;
import com.pojo.T_movie;
import com.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class TestMapper {
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private MovieDao movieDao;
	
	@Test
	public void testMovieDao() {
		T_movie movie=movieDao.querryMovieById(1);
		System.err.println(movie.getMovieName());
		
	}
	
	@Test
	public void testUserDao() throws Exception {
		User user=userDao.findUser(1);
		System.out.println(user.getTel());
	}
}

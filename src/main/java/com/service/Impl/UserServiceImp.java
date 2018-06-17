package com.service.Impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.pojo.Shopping;
import com.pojo.User;
import com.pojo.UserRecord;
import com.service.UserService;
import com.utils.HttpClientUtil;
import com.utils.JsonUtils;

@Service
public class UserServiceImp implements UserService{
	@Autowired
	private UserDao userDao;

	public User queryUser(String id) throws Exception {

         User user=userDao.findUser(Integer.parseInt(id));
		return user;
	}

	
	public int updateUserInfo(Integer id,String userName, Integer  sex, String living, Integer  qq, Integer tel, String email) throws Exception {
		int n=userDao.updateUserInfo(id, userName, sex, living, email, qq, tel);
		
		return n;
	}


	@Override

	public int uploadportrait(Integer id,String portrait) throws Exception {
		int n=userDao.uploadportrait(id, portrait);
		return n;
		
		
	}


	@Override
	public UserRecord selectHistory(Integer id) throws Exception {
		UserRecord userRecord=userDao.selectHistory(id);
		return userRecord;
	}


	
	public int buyVip(Integer userId) {
		int n=userDao.buyVip(userId);
		System.out.println(n);
		return n;
	}


	@Override
	public Shopping queryShoppingCart(Integer userId) {
		Shopping shopCart=userDao.querryShoppingCart(userId);
		return shopCart;
	}


	@Override
	public int payForGoods(Integer userId, Integer shoppingId) {
		int n=userDao.payForGoods(userId, shoppingId);
		return n;
	}


	@Override
	public int addGoods(Integer userId, String goodName, Integer goodPrice) {
		int n=userDao.addGoods(userId, goodName, goodPrice);
		return n;
	}


	@Override
	public User getUserByToken(HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		String token=null;
		for(int i=0;i<cookies.length;i++) {
			if(cookies[i].getName().equals("TOKEN")) {
				token=cookies[i].getValue();
				break;
			}
			else {
				continue;
			}
		}
		if(token==null) {
			return null;
		}
		System.out.println(token);
		String json=HttpClientUtil.doGet("http://localhost:8081//getUserInfoByToken//"+token);
			
		
			User user=JsonUtils.jsonToPojo(json, User.class);
	
		return user;
	}




}

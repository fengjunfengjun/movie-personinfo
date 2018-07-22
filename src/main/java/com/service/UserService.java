package com.service;



import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.pojo.Shopping;
import com.pojo.User;
import com.pojo.UserRecord;

public interface UserService {
	//查询个人信息
	public User queryUser(Integer id) throws Exception;
	//修改个人信息
	public int updateUserInfo(User user) throws Exception;
	//上传图片
	public int uploadportrait(Integer id,String portrait)throws Exception;
	//查看播放记录
	public UserRecord selectHistory( Integer id)throws Exception;
	public int  userRegister(User user)throws Exception;
	public Integer userlogin(String userName,String userPassword)throws Exception;
	public int  buyVip(Integer userId);
	//购物车
	public Shopping queryShoppingCart(Integer userId);
	public int payForGoods( Integer userId, Integer shoppingId);
	public int addGoods( Integer userId, String goodName, Integer goodPrice);
	
	//public User getUserByToken(HttpServletRequest request);
}
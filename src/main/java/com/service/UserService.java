package com.service;



import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.pojo.Shopping;
import com.pojo.User;
import com.pojo.UserRecord;

public interface UserService {
	//查询个人信息
	public User queryUser(String id) throws Exception;
	//修改个人信息
	public int updateUserInfo(Integer  id,String userName, Integer  sex, String living, Integer  qq, Integer  tel, String email) throws Exception;
	//上传图片
	public int uploadportrait(Integer id,String portrait)throws Exception;
	//查看播放记录
	public UserRecord selectHistory( Integer id)throws Exception;
	
	public int  buyVip(Integer userId);
	//购物车
	public Shopping queryShoppingCart(Integer userId);
	public int payForGoods( Integer userId, Integer shoppingId);
	public int addGoods( Integer userId, String goodName, Integer goodPrice);
	
	public User getUserByToken(HttpServletRequest request);
}
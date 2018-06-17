package com.dao;

import org.apache.ibatis.annotations.Param;

import com.pojo.Shopping;
import com.pojo.User;
import com.pojo.UserRecord;

public interface UserDao {
	public User findUser(@Param("id") Integer id) throws Exception;
	
	public int updateUserInfo(@Param("id") Integer id,@Param("userName")String userName,@Param("sex")Integer sex,@Param("living")String living,@Param("email")String email,@Param("qq")Integer qq,@Param("tel")Integer tel) throws Exception;
	public int uploadportrait(@Param("id") Integer id,@Param("portrait")String portrait)throws Exception;
	
	public UserRecord selectHistory(@Param("id") Integer id);
	
	public int buyVip(@Param("userId") Integer userId);
	
	public Shopping querryShoppingCart(@Param("userId") Integer userId);
	
	public int payForGoods(@Param("userId") Integer userId,@Param("shoppingId") Integer shoppingId);
	public int addGoods(@Param("userId") Integer userId,@Param("goodName") String goodName,@Param("goodPrice") Integer goodPrice);
}

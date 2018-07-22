package com.dao;

import org.apache.ibatis.annotations.Param;

import com.pojo.Shopping;
import com.pojo.User;
import com.pojo.UserRecord;

/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface UserDao {
	
	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User findUser(@Param("id") Integer id) throws Exception;
	
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int updateUserInfo(User user) throws Exception;
	
	/**
	 * 修改头像
	 * @param id
	 * @param portrait
	 * @return
	 * @throws Exception
	 */
	public int uploadportrait(@Param("id") Integer id,@Param("portrait")String portrait)throws Exception;
	
	/**
	 * 用户观影记录查询
	 * @param id
	 * @return
	 */
	public UserRecord selectHistory(@Param("id") Integer id);

	public int buyVip(@Param("userId") Integer userId);
	
	/**用户注册
	 * @param user
	 * @return
	 */
	public int  userRegister(User user);
	
	public String userNameCheck(@Param("userName")String userName);
	
	/**
	 * 用户登录
	 * @param userName
	 * @param userPassword
	 * @return
	 */
	public Integer  userlogin(@Param("userName")String userName,@Param("userPassword")String userPassword);
	
	public Shopping querryShoppingCart(@Param("userId") Integer userId);
	public int payForGoods(@Param("userId") Integer userId,@Param("shoppingId") Integer shoppingId);
	public int addGoods(@Param("userId") Integer userId,@Param("goodName") String goodName,@Param("goodPrice") Integer goodPrice);
}

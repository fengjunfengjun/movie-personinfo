package com.controller;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aspectj.UserExecption;
import com.pojo.Shopping;
import com.pojo.User;
import com.pojo.UserRecord;
import com.service.UserService;
import com.utils.EnumUtil;
import com.utils.IPTimeStamp;
import com.utils.MessageUtil;

@Controller

public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/findUser")
	@ResponseBody
	public  MessageUtil queryUser(HttpServletRequest request) throws Exception {
		
		Integer userId=(Integer) request.getSession().getAttribute("user");
		if(userId==null) {
			return new MessageUtil(EnumUtil.NOT_LOGIN,null,"用户未登录") ;
		}
		User user=userService.queryUser(userId);
		return new MessageUtil(EnumUtil.OK,user,"获取个人信息成功");
	}
	
	/**
	 * 用户注册 
	 * @param userName
	 * @param userPassword
	 * @param qq
	 * @param living
	 * @param sex
	 * @param tel
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userRegister")
	@ResponseBody
	public MessageUtil userRegister(String userName,String userPassword,String qq,String living,Integer sex,String tel,String email) throws Exception {
			User user=new User();//非空检验 字符串检验 空白检验 
			 if(userName==null||"".equals(userName)||userPassword==null||"".equals(userPassword)) {
				 return new  MessageUtil(EnumUtil.NOT_DATA,null,"用户名或密码不能为空");
			 }
			 
			Integer Iqq=null;
			Integer Itel=null;

			if(qq!=null) {
				try{
					Iqq=Integer.valueOf(qq);
				}catch(Exception e) {
					return new MessageUtil(EnumUtil.PARAM_ERROR,null,"qq不能包含字符");
				}
			}
				
				
			if(tel!=null) {
				try{
					Itel=Integer.valueOf(tel);
				}catch(Exception e) {
					return new MessageUtil(EnumUtil.PARAM_ERROR,null,"tel不能包含字符");
				}
			}
			if(email!=null) {
				if(!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
					return new MessageUtil(EnumUtil.PARAM_ERROR,null,"email格式有误");
				}
			}
			user.setUserName(userName);
			user.setUserPassword(userPassword);
			user.setEmail(email);
			user.setLiving(living);
			user.setQq(Iqq);
			user.setSex(sex);
			user.setTel(Itel);
			
		int n=userService.userRegister(user);
		if(n==1) {
			return new MessageUtil(EnumUtil.OK,null,"注册成功") ;
		}
		else if(n==-1) {
	
			return new MessageUtil(EnumUtil.SAME_NAME,null,"注册失败，用户名已经存在") ;
		}
		
			return new MessageUtil(EnumUtil.FAILURE,null,"注册失败") ;
		
	}
	
	/**
	 * 用户登录 （参数request 不用传 后台自己获取）
	 * @param userName
	 * @param userPassword
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userLogin")
	@ResponseBody
	public MessageUtil userLogin(String userName,String userPassword,HttpServletRequest request) throws Exception {
		 if(userName==null||"".equals(userName)||userPassword==null||"".equals(userPassword)) {
			 return new  MessageUtil(EnumUtil.NOT_DATA,null,"用户名或密码不能为空");
		 }
		 
		Integer userId=userService.userlogin(userName, userPassword);
		if(userId!=null) {
		
			request.getSession().setAttribute("user", userId);
			return new MessageUtil(EnumUtil.OK,null,"登录成功") ;
		}
			return new MessageUtil(EnumUtil.FAILURE,null,"登录失败") ;
		
	}
	
	/**
	 * 用户信息修改
	 * @param qq
	 * @param living
	 * @param sex
	 * @param tel
	 * @param email
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateUserInfo")	
	@ResponseBody
	public MessageUtil updateUserInfo(String qq,String living,Integer sex,String tel,String email,HttpServletRequest request) throws Exception {
		Integer userId=(Integer) request.getSession().getAttribute("user");
		
		if(userId==null) {
			return new MessageUtil(EnumUtil.NOT_LOGIN,null,"用户未登录") ;
		}
		
		Integer Iqq=null;
		Integer Itel=null;
		User user=new User();
		if(qq!=null) {
			try{
				Iqq=Integer.valueOf(qq);
			}catch(Exception e) {
				return new MessageUtil(EnumUtil.PARAM_ERROR,null,"qq不能包含字符");
			}
		}
			
			
		if(tel!=null) {
			try{
				Itel=Integer.valueOf(tel);
			}catch(Exception e) {
                	return new MessageUtil(EnumUtil.PARAM_ERROR,null,"tel不能包含字符");
			}
		}
		if(!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) {
			return new MessageUtil(EnumUtil.PARAM_ERROR,null,"email格式有误");
		}
			user.setEmail(email);
			user.setLiving(living);
			user.setQq(Iqq); 
			user.setSex(sex);
			user.setTel(Itel);
			user.setUserId(userId);
			int n=userService.updateUserInfo(user);
			if(n==1) {
				return new MessageUtil(EnumUtil.OK,null,"修改成功") ;
			}
		return new MessageUtil(EnumUtil.FAILURE,null,"修改失败") ;
	}
	
	
	
	/**
	 * 修改头像
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/uploadportrait")
	@ResponseBody
	public MessageUtil  uploadportrait(MultipartFile file,HttpServletRequest request) throws Exception {
		Integer userId=(Integer) request.getSession().getAttribute("user");
		
		if(userId==null) {
			return new MessageUtil(EnumUtil.NOT_LOGIN,null,"用户未登录") ;
		}
		
		String fileName=file.getOriginalFilename();
		String suffix=fileName.substring(fileName.lastIndexOf('.'));
		
		
		IPTimeStamp isp=new IPTimeStamp(InetAddress.getLocalHost().getHostAddress());
		String newName=isp.getIPTimeRand();
		fileName=newName+suffix;
		System.out.println(fileName);
		file.getContentType();
		String path=request.getServletContext().getRealPath("/img/");
		
			
			System.out.println(path);
			File dest=new File(path,fileName);
			
			if(!dest.getParentFile().exists()) {
			System.out.println(dest.getParentFile().mkdir());
			}
			
		try {
			file.transferTo(new File(path+File.separator+fileName));
			System.out.println(path+File.separator+fileName);
			userService.uploadportrait(userId,fileName);
			return  new MessageUtil(EnumUtil.OK,fileName,"修改头像成功") ;
		} catch (IllegalStateException | IOException e) {
			e.getMessage();
			e.printStackTrace();
			return  new MessageUtil(EnumUtil.FAILURE,null,"修改失败") ;
			
		}
		
		
	
	}
	
	
	@RequestMapping("/selectRecord")
	@ResponseBody
	public MessageUtil selectRecord(HttpServletRequest request) throws Exception {
		Integer userId=(Integer) request.getSession().getAttribute("user");
		
		if(userId==null) {
			return new MessageUtil(EnumUtil.NOT_LOGIN,null,"用户未登录") ;
		}
		UserRecord userRecord=userService.selectHistory(userId);
		return new MessageUtil(EnumUtil.OK,userRecord,"获取成功");
	}
	
	@RequestMapping("/buyVip")
	@ResponseBody
	public MessageUtil buyVip(HttpServletRequest request) {
		Integer userId=(Integer) request.getSession().getAttribute("user");
		if(userId==null) {
			return new MessageUtil(EnumUtil.NOT_LOGIN,null,"用户未登录") ;
		}
		if(userService.buyVip(userId)==1) {
			return new MessageUtil(EnumUtil.OK,null,"支付成功");
		}
		else {
			return new MessageUtil(EnumUtil.FAILURE,null,"支付失败");
		}
	}
	@RequestMapping("/querryShoppingCart")
	@ResponseBody
	public MessageUtil queryShoppingCart(HttpServletRequest request) {
Integer userId=(Integer) request.getSession().getAttribute("user");
		
		if(userId==null) {
			return new MessageUtil(EnumUtil.NOT_LOGIN,null,"用户未登录") ;
		}
		Shopping shopCart=userService.queryShoppingCart(userId); 
	  return new MessageUtil(EnumUtil.OK,shopCart,"查询成功");
	
	}
	
	@RequestMapping("/payForGoods")
	@ResponseBody
	public MessageUtil payForGoods(Integer shoppingId,HttpServletRequest request) {
		
		Integer userId=(Integer) request.getSession().getAttribute("user");
		if(userId==null) {
			return new MessageUtil(EnumUtil.NOT_LOGIN,null,"用户未登录") ;
		}
		if(userService.payForGoods(1, shoppingId)==1) {
			return new MessageUtil(EnumUtil.OK,null,"支付成功");
		}
		else {
			 return new MessageUtil(EnumUtil.OK,null,"支付失败");
		}
		
	
	}
	@RequestMapping("/addGoods")
	@ResponseBody
	public MessageUtil addGoods(Integer userId, String goodName, Integer goodPrice) {
		
		if(userService.addGoods(1, "tanl", 2)==1) {
			return new MessageUtil(EnumUtil.OK,null,"添加成功");
		}
		else {
			 return new MessageUtil(EnumUtil.OK,null,"添加失败");
		}
		
	
	}
	
	
	
	@RequestMapping("/test2")
	public void test(HttpServletRequest request) {
	
		
		System.out.println("asdfsad");
		
	/*
		System.out.println(request.getServletContext().getResourcePaths(""));
		System.out.println(request.getServletContext().getResourcePaths("/images"));
		System.out.println(request.getServletContext().getContextPath());
		System.out.println(path);
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println(basePath);
		System.out.println(request.getRequestDispatcher("/"));
		System.out.println(request.getServletContext().getRealPath("/"));*/
		
	}
	
	@RequestMapping("/personInfo")
	public String personInfo() {
		return "personInfo";
	}
	
	@ExceptionHandler(UserExecption.class)
	public String error(UserExecption e) {
		
		return "error";
	}
	
	@ExceptionHandler(Exception.class)
	public String error2(Exception e) {
		
		return "error2";
	}
}

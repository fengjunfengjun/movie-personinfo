package com.controller;


import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
		
		User user=userService.queryUser("1");
		return new MessageUtil(EnumUtil.OK,user,"获取个人信息成功");
	}
	@RequestMapping("/updateUserInfo")
	
	@ResponseBody
	public MessageUtil updateUserInfo(String userName, Integer qq, String living,Integer sex, Integer tel, String email) throws Exception {
		int id=1;
		System.out.println("id:"+id);
		System.out.println("userName:"+userName);
		System.out.println("sex:"+sex);
		System.out.println("living:"+living);
		System.out.println("qq:"+qq);
		System.out.println("tel:"+tel);
		System.out.println("email:"+email);
		try {
			int n=userService.updateUserInfo(id, userName, sex, living, qq, tel, email);
		}catch(Exception e){
			return new MessageUtil(EnumUtil.FAILURE,null,"修改失败") ;
		}
		
		return new MessageUtil(EnumUtil.OK,null,"修改成功") ;
	}
	
	@RequestMapping("/uploadportrait")
	@ResponseBody
	public MessageUtil  uploadportrait(MultipartFile file,HttpServletRequest request) throws Exception {
		String fileName=file.getOriginalFilename();
		String suffix=fileName.substring(fileName.lastIndexOf('.'));
		
		System.out.println(suffix);
		IPTimeStamp isp=new IPTimeStamp(InetAddress.getLocalHost().getHostAddress());
		String newName=isp.getIPTimeRand();
		fileName=newName+suffix;
		System.out.println(fileName);
		file.getContentType();
		String path=request.getServletContext().getRealPath("/images/");
		
			
			System.out.println(path);
			File dest=new File(path,fileName);
			
			if(!dest.getParentFile().exists()) {
			System.out.println(dest.getParentFile().mkdir());
			}
			
		try {
			file.transferTo(new File(path+File.separator+fileName));
			userService.uploadportrait(1,fileName);
			return  new MessageUtil(EnumUtil.OK,fileName,"修改头像成功") ;
		} catch (IllegalStateException | IOException e) {
			e.getMessage();
			e.printStackTrace();
			return  new MessageUtil(EnumUtil.FAILURE,null,"修改失败") ;
			
		}
		
		
	
	}
	
	@RequestMapping("/selectRecord")
	@ResponseBody
	public MessageUtil selectRecord() throws Exception {
		UserRecord userRecord=userService.selectHistory(1);
		return new MessageUtil(EnumUtil.OK,userRecord,"获取成功");
	}
	
	@RequestMapping("/buyVip")
	@ResponseBody
	public MessageUtil buyVip() {
		
		
		if(userService.buyVip(1)==1) {
			return new MessageUtil(EnumUtil.OK,null,"支付成功");
		}
		else {
			return new MessageUtil(EnumUtil.FAILURE,null,"支付失败");
		}
	}
	@RequestMapping("/querryShoppingCart")
	@ResponseBody
	public MessageUtil queryShoppingCart() {
		
		Shopping shopCart=userService.queryShoppingCart(1); 
	  return new MessageUtil(EnumUtil.OK,shopCart,"查询成功");
	
	}
	
	@RequestMapping("/payForGoods")
	@ResponseBody
	public MessageUtil payForGoods(Integer userId, Integer shoppingId) {
		
		if(userService.payForGoods(1, 1)==1) {
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
	
	
	
	@RequestMapping("/test")
	public void test(HttpServletRequest request) {
		 File f=new File("a.txt");
		String path = request.getContextPath();
	
		System.out.println(request.getServletContext().getResourcePaths(""));
		System.out.println(request.getServletContext().getResourcePaths("/images"));
		System.out.println(request.getServletContext().getContextPath());
		System.out.println(path);
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		System.out.println(basePath);
		System.out.println(request.getRequestDispatcher("/"));
		System.out.println(request.getServletContext().getRealPath("/"));
		
	}
	
	@RequestMapping("/personInfo")
	public String personInfo() {
		return "personInfo";
	}
}

package com.aspectj;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;


@Aspect
public class UserAspect {
	@Pointcut("execution(* com.service.Impl.UserServiceImp.*(..))")
	public void print() {
		
	}
	
	@Before("print()")
	public void before() {
		System.out.println("before");
	}
	
	@After("print()")
	public void after() {
		
	}
	
	@AfterThrowing(value="print()",throwing="e")
	public void AfterThrowing(Exception e)throws Exception {
		
	
	
		throw  new UserExecption("系统出现未知错误");
		
	}
	
	@AfterReturning(value="print()" )
	public void AfterReturning() {
		
	}
	
	
}

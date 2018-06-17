package com.utils;

public class test {
	public static void main(String[] args) {
	String result=null;
		result=HttpClientUtil.doGet("http://localhost:8081/test/1");
		System.out.println("test中的json"+result);
		
		
	}
}

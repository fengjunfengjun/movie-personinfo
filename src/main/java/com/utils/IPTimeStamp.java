package com.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IPTimeStamp {
	private SimpleDateFormat sdf=null;
	private String ip=null;
	public IPTimeStamp() {
		
	}
	public IPTimeStamp(String ip) {
		this.ip=ip;
	}
	private String addZero(String str,int len) {
		StringBuffer s=new StringBuffer();
		s.append(str);
		while(s.length()<len) {
			s.insert(0, "0");		
		}
		return s.toString();
	}
	public String  getDate() {
		this.sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		return sdf.format(new Date());
	}
	public String getTimeStamp() {
		this.sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return this.sdf.format(new Date());
	}
	public String getIPTimeRand() {
		StringBuffer buf=new StringBuffer();
		if(this.ip!=null) {
			String s[]=this.ip.split("\\.");
			for(int i=0;i<3;i++) {
				buf.append(addZero(s[i], 3));
			}
			
			
		}
		buf.append(getTimeStamp());
		Random r=new Random();
		for(int i=0;i<3;i++) {
			buf.append(r.nextInt(10));//生成大于0小于10的随机数
		}
		return buf.toString();
	}
	public static void main(String[] args) throws UnknownHostException {
		InetAddress add=InetAddress.getLocalHost();
		String ip=InetAddress.getLocalHost().getHostAddress();
		//String ip=add.toString();
		System.out.println(ip);
		
		IPTimeStamp it=new IPTimeStamp(ip);
		System.out.println(it.getDate());
		System.out.println(it.getTimeStamp());
		System.out.println(it.getIPTimeRand());
		
	}
}


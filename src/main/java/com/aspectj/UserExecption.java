package com.aspectj;

public class UserExecption extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserExecption(String msg) {
		super(msg);
	}
	public UserExecption() {
		
	}
	public UserExecption(Throwable t) {
		super(t);
	}
}

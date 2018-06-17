package com.utils;

public class UserException extends RuntimeException {
	public String message;  
    public UserException(String message){  
        super(message);  
        this.message = message;  
    }  
  
    @Override  
    public String getMessage() {  
        return message;  
    }  
  
    public void setMessage(String message) {  
        this.message = message;  
    }  
}

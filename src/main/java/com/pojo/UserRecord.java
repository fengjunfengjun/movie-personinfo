package com.pojo;

import java.sql.Date;
import java.util.List;

public class UserRecord {
	private int userId;
	private Date playTime;
	public Date getPlayTime() {
		return playTime;
	}
	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}
	private List<Movie>movies;
	public int getUserId() {
		return userId;
	}
	public List<Movie> getMovies() {
		return movies;
	}
	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}

package com.pojo;

public class T_movie {
	//电影名
	private String movieName;
	//电影类型
	private String category;
	//电影id
	private Integer movieId;
	//电影简介
	private String intro;
	//电影的发行国家
	private String country;
	//上映时间
	private String release_time;
	// 是否为vip电影
	private Integer isVipMovie;
	//电影点击量
	private Integer clickVolume;
	//评分
	private double score;
	//电影图片的url
	private String movieImg; 
	
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRelease_time() {
		return release_time;
	}
	public void setRelease_time(String release_time) {
		this.release_time = release_time;
	}
	public Integer getIsVipMovie() {
		return isVipMovie;
	}
	public void setIsVipMovie(Integer isVipMovie) {
		this.isVipMovie = isVipMovie;
	}
	public Integer getClickVolume() {
		return clickVolume;
	}
	public void setClickVolume(Integer clickVolume) {
		this.clickVolume = clickVolume;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getMovieImg() {
		return movieImg;
	}
	public void setMovieImg(String movieImg) {
		this.movieImg = movieImg;
	}
	
}

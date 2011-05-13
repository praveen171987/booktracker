package com.softwaresmithy;

import java.util.Date;

public class BookJB {
	private long _id;
	private String isbn;
	private String volume_id;
	private String title;
	private String author;
	private Date pubDate;
	private Date addDate;
	private Date dueDate;
	private Date closedDate;
	private String state;
	//Not stored in DB
	private String thumbUrl;
	
	public BookJB(Long _id, String isbn, String volume_id, String title, String author, Date pubDate, Date addDate, Date dueDate, Date closedDate, String state){
		if(_id != null){
			this._id = _id;
		}
		this.isbn = isbn;
		this.volume_id = volume_id;
		this.title = title;
		this.author = author;
		this.pubDate = pubDate;
		this.addDate = addDate;
		this.dueDate = dueDate;
		this.closedDate = closedDate;
		this.state = state;
		

	}
	public BookJB(String isbn, String volume_id, String title, String author){
		this(null, isbn, volume_id, title, author, null, null, null, null, null);
	}
	public BookJB() {
	}
	public long get_id() {
		return _id;
	}
	public void set_id(long _id) {
		this._id = _id;
	}	
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getVolume_id() {
		return volume_id;
	}
	public void setVolume_id(String volume_id) {
		this.volume_id = volume_id;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	public Date getAddDate() {
		return addDate;
	}
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	public Date getClosedDate() {
		return closedDate;
	}
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public String getThumbUrl() {
		return thumbUrl;
	}
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
}

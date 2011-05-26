package com.softwaresmithy;

import java.util.Date;

/**
 * A simple javabean for doing psuedo ORM with the SQLite database.
 * @author SZY4ZQ
 *
 */
public class BookJB {
	/**
	 * Generated primary key.
	 */
	 //TODO: get rid of the setter?
	private long _id;
	/**
	 * ISBN Number.
	 */
	private String isbn;
	/**
	 * Metadata provider specific identifier (named after Google's volume #).
	 */
	private String volumeId;
	/**
	 * The work's title (if multiple, formatted for display).
	 */
	private String title;
	/**
	 * The work's author (if multiple, formatted for display).
	 */
	private String author;
	/**
	 * The work's publication date.
	 */
	private Date pubDate;
	/**
	 * The date of the work's addition to the list.
	 */
	private Date addDate;
	/**
	 * The date the work is due back to the library (if applicable).
	 */
	private Date dueDate;
	/**
	 * The date the work was marked closed by the user.
	 */
	private Date closedDate;
	/**
	 * The string representation of a LibStatus.STATUS enum value.
	 */
	private String state;
	/**
	 * A url to retrieve the thumbnail of the book cover.
	 * NOTE: Not persisted in DB
	 */
	private String thumbUrl;
	
	/**
	 * Constructor with all params.
	 * @param id primary key
	 * @param isbn ISBN number
	 * @param volumeId unique identifier from metadata provider
	 * @param title work's title
	 * @param author work's author
	 * @param pubDate work's publication date
	 * @param addDate date work was added to DB
	 * @param dueDate date work is due to library
	 * @param closedDate date work was marked closed by user
	 * @param state LibStatus.STATUS text value
	 */
	public BookJB(Long id, String isbn, String volumeId, String title, String author, Date pubDate, Date addDate, Date dueDate, Date closedDate, String state){
		if(id != null){
			this._id = id;
		}
		this.isbn = isbn;
		this.volumeId = volumeId;
		this.title = title;
		this.author = author;
		this.pubDate = pubDate;
		this.addDate = addDate;
		this.dueDate = dueDate;
		this.closedDate = closedDate;
		this.state = state;
		

	}
	/**
	 * Convenience constructor with common metadata.
	 * @param isbn ISBN number
	 * @param volumeId unique identifier from metadata provider
	 * @param title work's title
	 * @param author work's author
	 */
	public BookJB(String isbn, String volumeId, String title, String author){
		this(null, isbn, volumeId, title, author, null, null, null, null, null);
	}
	/**
	 * Default constructor.
	 */
	public BookJB() {
	}
	/**
	 * 
	 * @return primary key from DB
	 */
	public long get_id() {
		return _id;
	}
	/**
	 * 
	 * @param id primary key
	 */
	public void set_id(long id) {
		this._id = id;
	}
	/**
	 * 
	 * @return ISBN Number
	 */
	public String getIsbn() {
		return isbn;
	}
	/**
	 * 
	 * @param isbn ISBN Number
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	/**
	 * 
	 * @return Work's title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 
	 * @param title Work's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 
	 * @return Work's author
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 
	 * @param author Work's author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * 
	 * @return LibStatus.STATUS String value
	 */
	public String getState() {
		return state;
	}
	/**
	 * 
	 * @param state LibStatus.STATUS String value
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 
	 * @return MetadataProvider specific unique ID
	 */
	public String getVolumeId() {
		return volumeId;
	}
	/**
	 * 
	 * @param volumeId MetadataProvider specific unique ID
	 */
	public void setVolumeId(String volumeId) {
		this.volumeId = volumeId;
	}
	/**
	 * 
	 * @return Work's original publication date
	 */
	public Date getPubDate() {
		return pubDate;
	}
	/**
	 * 
	 * @param pubDate Work's original publication date
	 */
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	/**
	 * 
	 * @return date work was added to DB
	 */
	public Date getAddDate() {
		return addDate;
	}
	/**
	 * 
	 * @param addDate date work was added to DB
	 */
	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}
	/**
	 * 
	 * @return date work was marked closed by user
	 */
	public Date getClosedDate() {
		return closedDate;
	}
	/**
	 * 
	 * @param closedDate date work was marked closed by user
	 */
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}
	/**
	 * 
	 * @param dueDate date work is due to library
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * 
	 * @return date work is due to library
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * 
	 * @return URL to retrieve book cover thumbnail
	 */
	public String getThumbUrl() {
		return thumbUrl;
	}
	/**
	 * 
	 * @param thumbUrl URL to retrieve book cover thumbnail
	 */
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
}

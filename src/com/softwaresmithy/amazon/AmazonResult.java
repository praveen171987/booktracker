package com.softwaresmithy.amazon;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.a2s.model.AlternateVersion;
import com.amazonaws.a2s.model.Item;
import com.amazonaws.a2s.model.Tag;

public class AmazonResult {
	private String ISBN;
	private String title;
	private List<String> author;
	private double rating = Double.NaN;
	private List<Tag> tags;
	private String pubDate;
	private int pages = -1;
	private String smallImageUrl;
	private String mediumImageUrl;
	private String largeImageUrl;
	private List<String> alternateVersions;
	private String detailUrl;
	public List<String> getAlternateVersions() {
		return alternateVersions;
	}
	public String getAlternateVersionsAsString() {
		return listToCSV(alternateVersions);
	}
	public void setAlternateVersions(List<String> alternateVersions) {
		this.alternateVersions = alternateVersions;
	}
	public AmazonResult(Item item){
		if(item.isSetASIN())
			this.setISBN(item.getASIN());
		if(item.isSetItemAttributes()){
			if(item.getItemAttributes().isSetAuthor())
				this.setAuthor(item.getItemAttributes().getAuthor());
			if(item.getItemAttributes().isSetTitle())
				this.setTitle(item.getItemAttributes().getTitle());
			if(item.getItemAttributes().isSetPublicationDate())
				this.setPubDate(item.getItemAttributes().getPublicationDate());
			if(item.getItemAttributes().isSetNumberOfPages())
				this.setPages(item.getItemAttributes().getNumberOfPages().intValue());
				
		}
		if(item.isSetCustomerReviews())
			this.setRating(item.getCustomerReviews().getAverageRating().doubleValue());
		if(item.isSetTags())
			this.setTags(item.getTags().getTag());
		if(item.isSetImageSets()){
			this.setSmallImageUrl(item.getSmallImage().getURL());
			this.setMediumImageUrl(item.getMediumImage().getURL());
			this.setLargeImageUrl(item.getLargeImage().getURL());
		}
		if(item.isSetAlternateVersions()){
			List<String> isbns = new ArrayList<String>();
			List<AlternateVersion> temp = item.getAlternateVersions().getAlternateVersion();
			for(int i=0;i<temp.size();i++){
				isbns.add(temp.get(i).getASIN());
			}
			this.setAlternateVersions(isbns);
		}
		if(item.isSetDetailPageURL()){
			this.setDetailUrl(item.getDetailPageURL());
		}
	}
	
	public AmazonResult() {
	}
	public CallableStatement prepareCS(CallableStatement cs) throws SQLException {
		cs.setString(1, this.getISBN());
		cs.setString(2, this.getTitle());
		if(Double.isNaN(this.getRating())){
			cs.setNull(3,java.sql.Types.NUMERIC);
		}else{
			cs.setBigDecimal(3, new BigDecimal(this.getRating()));
		}
		cs.setString(4, this.getPubDate());
		if(this.getPages() == -1){
			cs.setNull(5, java.sql.Types.NUMERIC);
		}else {
			cs.setInt(5, this.getPages());
		}
		cs.setString(6, this.getSmallImageUrl());
		cs.setString(7, this.getMediumImageUrl());
		cs.setString(8, this.getLargeImageUrl());
		if(this.getAuthor() == null){
			cs.setNull(9, java.sql.Types.BLOB);
		}else {
			cs.setString(9, listToCSV(this.getAuthor()));
		}
		if(this.getAlternateVersions() == null){
			cs.setNull(10, java.sql.Types.BLOB);
		}else {
			cs.setString(10, listToCSV(this.getAlternateVersions()));
		}
		if(this.getTags() == null){
			cs.setNull(11, java.sql.Types.BLOB);
		}else {
			cs.setString(11, taglistToCSV(this.getTags()));
		}
		return cs;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String isbn) {
		ISBN = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getAuthor() {
		return author;
	}
	public String getAuthorAsString() {
		return listToCSV(author);
	}
	public void setAuthor(List<String> author) {
		this.author = author;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public String getTagsAsString() {
		return taglistToCSV(tags);
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public String getSmallImageUrl() {
		return smallImageUrl;
	}
	public void setSmallImageUrl(String smallImageUrl) {
		this.smallImageUrl = smallImageUrl;
	}
	public String getMediumImageUrl() {
		return mediumImageUrl;
	}
	public void setMediumImageUrl(String mediumImageUrl) {
		this.mediumImageUrl = mediumImageUrl;
	}
	public String getLargeImageUrl() {
		return largeImageUrl;
	}
	public void setLargeImageUrl(String largeImageUrl) {
		this.largeImageUrl = largeImageUrl;
	}
	public String listToCSV(List<String> list){
		String temp = "";
		for(int i=0;i<list.size();i++){
			temp += list.get(i).replace("'", "\\'");
			if(i<list.size()-1)temp+=",";
		}
		return temp;
	}
	public String taglistToCSV(List<Tag> list){
		String temp = "";
		if(list == null) return temp;
		for(int i=0;i<list.size();i++){
			temp += list.get(i).getName().replace("'", "\\'");
			if(i<list.size()-1)temp+=",";
		}
		return temp;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
}

package com.softwaresmithy.amazon;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;

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
	public List<String> getAlternateVersions() {
		return alternateVersions;
	}
	public void setAlternateVersions(List<String> alternateVersions) {
		this.alternateVersions = alternateVersions;
	}
	AmazonResult(Item item){
		if(item.isSetASIN())
			this.setISBN(item.getASIN());
		if(item.isSetItemAttributes()){
			this.setAuthor(item.getItemAttributes().getAuthor());
			this.setTitle(item.getItemAttributes().getTitle());
			this.setPubDate(item.getItemAttributes().getPublicationDate());
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
	}
	public Document getDocument() {
		Document doc = new Document();
		if(this.getISBN()!= null)
			doc.add(new Field("ISBN",this.getISBN(),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getTitle()!=null)
			doc.add(new Field("title",this.getTitle(),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getAuthor()!=null){
			List<String> temp = this.getAuthor();
			for(int i=0;i<temp.size();i++){
				doc.add(new Field("author",temp.get(i),Field.Store.YES,Field.Index.UN_TOKENIZED));
			}
		}
		if(this.getRating()!= Double.NaN)
			doc.add(new Field("rating",Double.toString(this.getRating()),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getTags()!= null){
			List<Tag> temp = this.getTags();
			for(int i=0;i<temp.size();i++){
				doc.add(new Field("tag",temp.get(i).getName(),Field.Store.YES,Field.Index.UN_TOKENIZED));
			}
		}
		if(this.getPubDate() != null)
			doc.add(new Field("pubDate",this.getPubDate(),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getPages() != -1)
			doc.add(new Field("pages",Integer.toString(this.getPages()),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getSmallImageUrl()!= null)
			doc.add(new Field("smallImageUrl",this.getSmallImageUrl(),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getMediumImageUrl()!= null)
			doc.add(new Field("mediumImageUrl",this.getMediumImageUrl(),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getLargeImageUrl()!= null)
			doc.add(new Field("largeImageUrl",this.getLargeImageUrl(),Field.Store.YES,Field.Index.UN_TOKENIZED));
		if(this.getAlternateVersions()!= null){
			List<String> temp = this.getAlternateVersions();
			for(int i=0;i<temp.size();i++){
				doc.add(new Field("alternateVersion", temp.get(i),Field.Store.YES, Field.Index.UN_TOKENIZED));
			}
		}
		return doc;
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
}

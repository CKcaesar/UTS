package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class ItemBean extends Bean {

	Integer authorId;
	Integer itemNumber;
	
	String id;
	String isbn;
	String author;
	String title;
	String publisher;
	
	public final Integer getItemNumber() {
		return itemNumber;
	}

	public final String getId() {
		return id;
	}

	public final void setId (String id) {
		this.id = id;
	}

	public final void setItemNumber(Integer itemNumber) {
		this.itemNumber = itemNumber;
	}
	
	public final String getIsbn() {
		return isbn;
	}
	
	public final void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public final String getAuthor() {
		return author;
	}
	
	public final void setAuthor(String author) {
		this.author = author;
	}
	
	public final String getTitle() {
		return title;
	}
	
	public final void setTitle(String title) {
		this.title = title;
	}
	
	public final String getPublisher() {
		return publisher;
	}
	
	public final void setPublisher(String publisher) {
		this.publisher = publisher;
	}
		
	public final Integer getAuthorId() {
		return authorId;
	}

	public final void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public final void clear() {
		this.authorId = null;
		this.itemNumber = null;
		this.id = null;
		this.isbn = null;
		this.author = null;
		this.title = null;
		this.publisher = null;
	}

}

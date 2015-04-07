package edu.scs.carleton.comp.ls.view.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import edu.scs.carleton.comp.ls.book.Title;

@ManagedBean
@RequestScoped
public class TitleBean extends Bean {

	private Integer onHand;
	private Integer authorId;
	private Integer publisherId;
	
	private String isbn;
	private String author;
	private String title;
	private String publisher;

	private Boolean selected;
	private Boolean checkedOut;
	
	public TitleBean () {
		this.onHand = new Integer (0);
		this.authorId = new Integer (0);
		this.publisherId = new Integer (0);
		
		this.isbn = "";
		this.author = "";
		this.title = "";
		this.selected = false;
		this.checkedOut = false;		
	}

	public final boolean isSelected() {
		return selected;
	}

	public final void setSelected(boolean selected) {
		this.selected = selected;
	}

	public final boolean isCheckedOut() {
		return checkedOut;
	}

	public final void setCheckedOut(boolean checkedOut) {
		this.checkedOut = checkedOut;
	}

	public TitleBean (Title title) {
		this.isbn = title.getIsbn();
		this.title = title.getTitle();
		this.author = title.getAuthor().getFamilyName() + ", " + title.getAuthor().getGivenName();
		this.authorId = title.getAuthor().getAuthorID();
		this.publisher = title.getPublisher().getName();
		this.publisherId = title.getPublisher().getPublisherID();
	}
	
	public final int getOnHand() {
		return onHand;
	}
	
	public final void setOnHand(int onHand) {
		this.onHand = onHand;
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

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
		
	
	public final Integer getAuthorId() {
		return authorId;
	}

	public final void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public final Integer getPublisherId() {
		return publisherId;
	}

	public final void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}

	public final void clear () {
		
		this.authorId = null;
		this.publisherId = null;
		
		this.onHand = null;
		this.isbn = null;
		this.author = null;
		this.title = null;
		this.publisher = null;
		
		this.selected = null;
		this.checkedOut = null;
		
	}
	
}

package edu.scs.carleton.comp.ls.view.beans;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class LoanBean extends Bean {

	private Integer loanId;
	private Integer itemNumber;
	private Integer numOfRenewals;
	
	private String isbn;
	private String title;
	private String author;
	private Date dateBorrowed;
	private Date dueDate;

	private Double amountDue;

	private Boolean renewItem;
	
	public final boolean isRenewItem() {
		return renewItem;
	}

	public final void setRenewItem(boolean renewItem) {
		this.renewItem = renewItem;
	}

	public final boolean isReturnItem() {
		return returnItem;
	}

	public final void setReturnItem(boolean returnItem) {
		this.returnItem = returnItem;
	}

	private boolean returnItem;

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	
	public final int getItemNumber() {
		return itemNumber;
	}

	public final void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public final int getNumOfRenewals() {
		return numOfRenewals;
	}

	public final void setNumOfRenewals(int numOfRenewals) {
		this.numOfRenewals = numOfRenewals;
	}

	public final String getIsbn() {
		return isbn;
	}

	public final void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final String getAuthor() {
		return author;
	}

	public final void setAuthor(String author) {
		this.author = author;
	}

	public final Date getDateBorrowed() {
		return dateBorrowed;
	}

	public final void setDateBorrowed(Date dateBorrowed) {
		this.dateBorrowed = dateBorrowed;
	}

	public final Date getDueDate() {
		return dueDate;
	}

	public final void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public final double getAmountDue() {
		return amountDue;
	}

	public final void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}

	public final void clear () {
		this.loanId = null;
		this.itemNumber = null;
		this.numOfRenewals = null;
		this.isbn = null;
		this.title = null;
		this.author = null;
		this.dateBorrowed = null;
		this.dueDate = null;
		this.amountDue = null;
		this.renewItem = null;
	}
}

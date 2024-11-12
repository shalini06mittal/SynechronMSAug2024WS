package com.demo.MSBookCatalogService.model;


public class OrderWrapper {

	String bookid;
	String datetime;
	
	public OrderWrapper() {
		// TODO Auto-generated constructor stub
	}

	public OrderWrapper(String bookid, String datetime) {
		super();
		this.bookid = bookid;
		this.datetime = datetime;
	}

	public String getBookid() {
		return bookid;
	}

	public void setBookid(String bookid) {
		this.bookid = bookid;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	@Override
	public String toString() {
		return "OrderWrapper [bookid=" + bookid + ", datetime=" + datetime + "]";
	}
	
	
}

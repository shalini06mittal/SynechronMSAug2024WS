package com.demo.MSBookCatalogService.model;


public class BookOrder {

	String bookid;
	String datetime;
	
	public BookOrder() {
		// TODO Auto-generated constructor stub
	}

	public BookOrder(String bookid, String datetime) {
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

package com.demo.MSBookOrderService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class BookOrder {

	@Id
	private int orderno;
	private String email;
	private String bookid;
	private String datetime;
	public BookOrder() {
		// TODO Auto-generated constructor stub
	}
	public BookOrder(int orderno, String email, String bookid, String datetime) {
		super();
		this.orderno = orderno;
		this.email = email;
		this.bookid = bookid;
		this.datetime = datetime;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
		return "BookOrder [orderno=" + orderno + ", email=" + email + ", bookid=" + bookid + ", datetime=" + datetime
				+ "]";
	}
	
}


package com.demo.MSBookCatalogService.model;

public class BookCatalog {
	private String title;
	private String description;
	private double price;
	private String datetime;


	public BookCatalog() {
		// TODO Auto-generated constructor stub
	}

	public BookCatalog(String email, String booktitle, String bookdescription, double price, String datetime) {

		this.title = booktitle;
		this.description = bookdescription;
		this.price = price;
		this.datetime = datetime;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String booktitle) {
		this.title = booktitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String bookdescription) {
		this.description = bookdescription;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}


	
}

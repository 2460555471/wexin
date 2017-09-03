package DoubanApi;

import java.io.UnsupportedEncodingException;

public class Book {
	private String book_ISBN;
	private String book_name;
	private String book_author;
	private String book_press_name;
	private String book_press_year;;
	private String book_price;
	private String book_pic_url;
	private String book_keywords;
	private String book_abstract;

	public String getBook_ISBN() {
		return book_ISBN;
	}
	public void setBook_ISBN(String book_ISBN) {
		this.book_ISBN = book_ISBN;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public String getBook_author() {
		return book_author;
	}
	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}
	public String getBook_press_name() {
		return book_press_name;
	}
	public void setBook_press_name(String book_press_name) {
		this.book_press_name = book_press_name;
	}
	public String getBook_press_year() {
		return book_press_year;
	}
	public void setBook_press_year(String book_press_year) {
		this.book_press_year = book_press_year;
	}
	public String getBook_price() {
		return book_price;
	}
	public void setBook_price(String book_price) {
		this.book_price = book_price;
	}
	public String getBook_pic_url() {
		return book_pic_url;
	}
	public void setBook_pic_url(String book_pic_url) {
		this.book_pic_url = book_pic_url;
	}
	public String getBook_keywords() {
		return book_keywords;
	}
	public void setBook_keywords(String book_keywords) {
		this.book_keywords = book_keywords;
	}
	public String getBook_abstract() {
		return book_abstract;
	}
	public void setBook_abstract(String book_abstract) {
		this.book_abstract = book_abstract;
	}
	public void Print() throws UnsupportedEncodingException
	{
		System.out.println(this.book_ISBN);
		System.out.println(this.book_name);
		System.out.println(this.book_author);
		System.out.println(this.book_press_name);
		System.out.println(this.book_press_year);
		System.out.println(this.book_price);
		System.out.println(this.book_pic_url);
		System.out.println(this.book_keywords);
		System.out.println(new String(this.book_abstract.getBytes("gbk")));
	}
	
	
}

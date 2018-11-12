package com.books.app.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="books")
public class Book {
	
	@Id
	@Column(name="bookid")
	private int bookId;
	@Column(name="title")
	private String title;
	@Column(name="price")
	private double price;
	@Column(name="vol")
	private int volume;
	@Column(name="pdate")
	@DateTimeFormat(pattern ="dd-MM-yyyy")
	private LocalDate publistDt;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="stitle" , nullable=false)
	private Subject subject;
	
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public LocalDate getPublistDt() {
		return publistDt;
	}
	public void setPublistDt(LocalDate publistDt) {
		this.publistDt = publistDt;
	}
	
	

}

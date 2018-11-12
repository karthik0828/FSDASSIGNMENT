package com.books.app.service;

import java.util.List;

import com.books.app.exception.BookAppException;
import com.books.app.model.Book;
import com.books.app.model.Subject;


public interface IBookAppService {

	String add(Book book, String subtitle) throws BookAppException;

	void delete(int bookid) throws BookAppException;

	void deleteSubject(Subject subject) throws BookAppException;

	Book search(int bookid) throws BookAppException;

	Subject searchSubject(String subtitle) throws BookAppException;
	
	List<Book> view() throws BookAppException;

	List<Subject> searchSubjectByDuration(int durationInHours) throws BookAppException;

	Book searchByTitle(String title) throws BookAppException;


}

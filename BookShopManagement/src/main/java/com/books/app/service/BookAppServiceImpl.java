package com.books.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.books.app.dao.IBookRepository;
import com.books.app.dao.ISubjectRepository;
import com.books.app.exception.BookAppException;
import com.books.app.model.Book;
import com.books.app.model.Subject;

@Service
public class BookAppServiceImpl implements IBookAppService {

	@Autowired
	private ISubjectRepository subjRepo;
	@Autowired
	private IBookRepository bookRepo;

	@Transactional
	@Override
	public String add(Book book, String subtitle) throws BookAppException {

		Subject subject = null;
		if (book.getSubject() != null) {
			List<Subject> subjectList = subjRepo.findAll();
			for (Subject subj : subjectList) {
				if (subj.getSubtitle() != null && subj.getSubtitle().equalsIgnoreCase(subtitle)) {
					subject = subj;
				}
			}
			if (subject == null) {
				subject = new Subject();
				subject.setSubtitle(subtitle);
				subjRepo.save(subject);
			}
		}
		book.setSubject(subject);
		Book sbook = bookRepo.save(book);
		return sbook.getTitle();
	}
    
	@Transactional
	@Override
	public void delete(int bookid) throws BookAppException {
		bookRepo.deleteById(bookid);
	}

	@Transactional
	@Override
	public void deleteSubject(Subject subject) throws BookAppException {		
		Subject subj = searchSubject(subject.getSubtitle());			
		Set<Book> books = subj.getReferences();
		for (Book book : books) {
			if(book != null) {
				delete(book.getBookId());
			}			
		}
		subjRepo.delete(subj);
	}

	
	@Override
	public Book search(int bookid) throws BookAppException {
		Optional<Book> optBook = bookRepo.findById(bookid);
		Book book = optBook.isPresent() ? optBook.get() : null;
		return book;
	}

	@Override
	public Subject searchSubject(String subtitle) throws BookAppException {
		Subject subject = null;
		List<Subject> subjectList = subjRepo.findAll();
		for (Subject subj : subjectList) {
			if (subj.getSubtitle() != null && subj.getSubtitle().equalsIgnoreCase(subtitle)) {
				subject = subj;
			}
		}
		return subject;
	}
	
	@Override
	public List<Book> view() throws BookAppException{
		return bookRepo.findAll();
	}

	@Override
	public List<Subject> searchSubjectByDuration(int durationInHours) throws BookAppException {
		
		List<Subject> selSubjects = new ArrayList<Subject>();
		List<Subject> subjectList = subjRepo.findAll();
		for (Subject subj : subjectList) {
			if (subj.getDurationInHours() != 0 && subj.getDurationInHours() == durationInHours) {
				selSubjects.add(subj);
			}
		}
		return selSubjects;
	}

	@Override
	public Book searchByTitle(String title) throws BookAppException {
		Book sbook =null;
		List<Book> books = bookRepo.findAll();
		if(books != null && !books.isEmpty()) {
			for (Book book : books) {
				if(book != null && book.getTitle().equalsIgnoreCase(title)) {
					sbook = search(book.getBookId());
				}
			}
		}
		return sbook;
	}


}

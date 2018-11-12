package com.books.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.books.app.dao.IBookRepository;
import com.books.app.model.Book;
import com.books.app.model.Subject;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class BookRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;	

    @Autowired
    private IBookRepository bookRepository;
    
    @Test
    public void whenFindById_thenReturnBook() {
        // given
		Subject sub = new Subject();
		sub.setSubtitle("Subject 1");
		entityManager.persist(sub);
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Book 1");
    	book.setPrice(10.00);
		book.setVolume(1);
		book.setPublistDt(LocalDate.now());
		book.setSubject(sub);		
        entityManager.persist(book);
        entityManager.flush();
     
        // when
        Optional<Book> optbook = bookRepository.findById(book.getBookId());
        Book sbook = optbook.get();
     
        // then
        assertThat(sbook.getTitle())
          .isEqualTo(book.getTitle());
    }
    
    @Test
    public void saveBook() {
        // given
		Subject sub = new Subject();
		sub.setSubtitle("Subject 1");
		entityManager.persist(sub);
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Book 1");
    	book.setPrice(10.00);
		book.setVolume(1);
		book.setPublistDt(LocalDate.now());
		book.setSubject(sub);		
        entityManager.persist(book);
        entityManager.flush();
     
        // when
        Book sbook = bookRepository.save(book);
       
     
        // then
        assertThat(sbook.getTitle())
          .isEqualTo(book.getTitle());
    }
    
    @Test
    public void deleteBook_ById() {
        // given
		Subject sub = new Subject();
		sub.setSubtitle("Subject 1");
		entityManager.persist(sub);
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Book 1");
    	book.setPrice(10.00);
		book.setVolume(1);
		book.setPublistDt(LocalDate.now());
		book.setSubject(sub);		
        entityManager.persist(book);
        entityManager.flush();
     
        // when
        bookRepository.deleteById(book.getBookId());
       
     
        // then
        assertThat(bookRepository.findById(book.getBookId())).isEmpty();
          
    }
    
    @Test
    public void whenFindAll_thenReturnBookList() {
        // given
		Subject sub1 = new Subject();
		sub1.setSubtitle("Subject 1");
		entityManager.persist(sub1);
        Book book = new Book();
        book.setBookId(1);
        book.setTitle("Book 1");
    	book.setPrice(10.00);
		book.setVolume(1);
		book.setPublistDt(LocalDate.now());
		book.setSubject(sub1);		
        entityManager.persist(book);
        entityManager.flush();
        
		Subject sub2 = new Subject();
		sub2.setSubtitle("Subject 2");
		entityManager.persist(sub2);
        Book book2 = new Book();
        book2.setBookId(2);
        book2.setTitle("Book 2");
    	book2.setPrice(20.00);
		book2.setVolume(2);
		book2.setPublistDt(LocalDate.now());
		book2.setSubject(sub2);		
        entityManager.persist(book2);
        entityManager.flush();
     
        // when
        List<Book> bookList = bookRepository.findAll();
             
        // then
        assertThat(bookList.get(0).getTitle())
          .isEqualTo("Book 1");
    }

}

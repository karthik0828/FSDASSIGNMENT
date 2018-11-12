package com.books.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.books.app.dao.ISubjectRepository;
import com.books.app.model.Book;
import com.books.app.model.Subject;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SubjectRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;	

    @Autowired
    private ISubjectRepository subjectRepository;
    
    @Test
    public void whenFindById_thenReturnSubject() {
        // given
		Subject sub = new Subject();
		sub.setSubtitle("Subject 1");
		sub.setSubjectId(1);
		sub.setDurationInHours(20);	
		Set<Book> references = new HashSet<Book>();
		sub.setReferences(references);
		entityManager.persist(sub);
		entityManager.flush();
     
        // when
        Optional<Subject> optSubject = subjectRepository.findById(sub.getSubtitle());
        Subject subj = optSubject.get();
     
        // then
        assertThat(sub.getSubtitle())
          .isEqualTo(subj.getSubtitle());
    }
    
    @Test
    public void saveSubject() {
        // given
		Subject sub = new Subject();
		sub.setSubtitle("Subject 1");
		sub.setSubjectId(1);
		sub.setDurationInHours(20);	
		Set<Book> references = new HashSet<Book>();
		sub.setReferences(references);
		entityManager.persist(sub);
		entityManager.flush();
     
        // when
        Subject subj = subjectRepository.save(sub);
       
     
        // then
        assertThat(sub.getSubtitle())
          .isEqualTo(subj.getSubtitle());
    }
    
    @Test
    public void deleteSubject_ById() {
        // given
		Subject sub = new Subject();
		sub.setSubtitle("Subject 1");
		sub.setSubjectId(1);
		sub.setDurationInHours(20);	
		Set<Book> references = new HashSet<Book>();
		sub.setReferences(references);
		entityManager.persist(sub);
		entityManager.flush();
     
     
        // when
		subjectRepository.deleteById(sub.getSubtitle());
       
     
        // then
        assertThat(subjectRepository.findById(sub.getSubtitle())).isEmpty();
          
    }
    
    @Test
    public void whenFindAll_thenReturnSubjectList() {
        // given
		Subject sub1 = new Subject();
		sub1.setSubtitle("Subject 1");
		sub1.setSubjectId(1);
		sub1.setDurationInHours(10);	
		Set<Book> references1 = new HashSet<Book>();
		sub1.setReferences(references1);
		entityManager.persist(sub1);
		entityManager.flush();
        
		Subject sub2 = new Subject();
		sub2.setSubtitle("Subject 2");
		sub2.setSubjectId(2);
		sub2.setDurationInHours(20);	
		Set<Book> references2 = new HashSet<Book>();
		sub2.setReferences(references2);
		entityManager.persist(sub2);
		entityManager.flush();
     
        // when
        List<Subject> subjList = null;
        subjList = subjectRepository.findAll();
        for (Subject subject : subjList) {
        	System.out.println(subject.getSubtitle());
		}
        
             
        // then
        assertThat(subjList.stream().filter(sub -> "Subject 1".equalsIgnoreCase(sub.getSubtitle())).findFirst().isPresent())
          .isEqualTo(true);
    }

}

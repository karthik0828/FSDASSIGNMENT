package com.books.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.app.model.Subject;


public interface ISubjectRepository extends JpaRepository<Subject,String>{

}

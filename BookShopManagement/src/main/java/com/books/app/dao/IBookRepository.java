package com.books.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.books.app.model.Book;


public interface IBookRepository extends JpaRepository<Book,Integer>{

}

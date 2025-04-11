package com.example.bookstore.service;

import com.example.bookstore.model.filter.BookFilter;
import com.example.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<Book> getAll(BookFilter filter, Pageable pageable);

    Book getById(Long id);

    Book save(Book book);

    Book update(Long id, Book book);

    void delete(Long id);
}
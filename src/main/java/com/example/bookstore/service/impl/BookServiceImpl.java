package com.example.bookstore.service.impl;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.filter.BookFilter;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;


    @Override
    public Page<Book> getAll(BookFilter filter, Pageable pageable) {
        Specification<Book> spec = filter.toSpecification();
        return bookRepository.findAll(spec, pageable);
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book bookDetails) {
        Book book = getById(id);

        book.setVendorCode(bookDetails.getVendorCode());
        book.setTitle(bookDetails.getTitle());
        book.setYear(bookDetails.getYear());
        book.setBrand(bookDetails.getBrand());
        book.setStock(bookDetails.getStock());
        book.setPrice(bookDetails.getPrice());

        return bookRepository.save(book);
    }

    @Override
    public void delete(Long id) {
        Book book = getById(id);
        bookRepository.delete(book);
    }
}
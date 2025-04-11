package com.example.bookstore.controller;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.filter.BookFilter;
import com.example.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookWebController {
    
    private final BookService bookService;

    @GetMapping
    public String listBooks(
            @ModelAttribute BookFilter filter,
            Pageable pageable,
            Model model) {
        Page<Book> books = bookService.getAll(filter, pageable);
        
        model.addAttribute("books", books);
        model.addAttribute("currentPage", pageable.getPageNumber());
        model.addAttribute("totalPages", books.getTotalPages());
        model.addAttribute("title", filter.title());
        model.addAttribute("brand", filter.brand());
        model.addAttribute("year", filter.year());
        
        return "books/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getById(id);
        model.addAttribute("book", book);
        return "books/form";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        if (book.getId() == null) {
            bookService.save(book);
        } else {
            bookService.update(book.getId(), book);
        }
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
package com.example.libarary.library.service;

import com.example.libarary.library.model.Author;
import com.example.libarary.library.model.Book;
import com.example.libarary.library.model.dto.BookDto;
import com.example.libarary.library.model.enumerations.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> findByName(String name);
    Optional<Book> findById(Long id);
    void deleteById(Long id);
    List<Book> findAll();
    Optional<Book> markAsTaken(Long id);
    Optional<Book> save(BookDto bookDto);
    Optional<Book> edit(Long id, BookDto bookDto);
    Page<Book> findAllWithPagination(Pageable pageable);



}

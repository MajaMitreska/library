package com.example.libarary.library.service.impl;

import com.example.libarary.library.model.Author;
import com.example.libarary.library.model.Book;
import com.example.libarary.library.model.dto.BookDto;
import com.example.libarary.library.model.enumerations.Category;
import com.example.libarary.library.model.exeptions.AuthorNotFound;
import com.example.libarary.library.model.exeptions.BookNotFoundException;
import com.example.libarary.library.model.exeptions.NoCopiesOfBookException;
import com.example.libarary.library.repository.AuthorRepository;
import com.example.libarary.library.repository.BookRepository;
import com.example.libarary.library.repository.CountryRepository;
import com.example.libarary.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFound(authorId));
        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, author, availableCopies);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new AuthorNotFound(authorId));
        book.setAuthor(author);
        book.setName(name);
        book.setAvailableCopies(availableCopies);
        book.setCategory(category);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {

        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        book.setCategory(bookDto.getCategory());

        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFound(bookDto.getAuthor()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {

        this.bookRepository.deleteByName(bookDto.getName());
        Author author = this.authorRepository.findById(bookDto.getAuthor()).orElseThrow(() -> new AuthorNotFound(bookDto.getAuthor()));
        Book book = new Book(
                bookDto.getName(),
                bookDto.getCategory(),
                author,
                bookDto.getAvailableCopies());

        this.bookRepository.save(book);
        return Optional.of(book);
    }


    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = this.bookRepository.findAll();
        books.sort(Comparator.comparing(Book::getName));
        return books;
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Integer copies = book.getAvailableCopies();
        if (copies > 0) {
            book.setAvailableCopies(copies - 1);
        } else {
            throw new NoCopiesOfBookException(book.getName());
        }
        return Optional.of(this.bookRepository.save(book));
    }
}

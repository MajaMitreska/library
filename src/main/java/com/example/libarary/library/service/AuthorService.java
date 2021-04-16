package com.example.libarary.library.service;

import com.example.libarary.library.model.Author;
import com.example.libarary.library.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findByName(String name);
    Optional<Author> findById(Long id);
    List<Author> findAll();
    Optional<Author> save(String name, String surname, Long countryId);
    void deleteById(Long id);
    Optional<Author> save(AuthorDto authorDto);
}

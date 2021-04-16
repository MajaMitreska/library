package com.example.libarary.library.service;

import com.example.libarary.library.model.Author;
import com.example.libarary.library.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    Optional<Country> findByName(String name);
    Optional<Country> findById(Long id);
    List<Country> findAll();
    Optional<Country> save(String name, String continent);
    void deleteById(Long id);
}

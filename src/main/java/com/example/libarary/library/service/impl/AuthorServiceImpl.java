package com.example.libarary.library.service.impl;

import com.example.libarary.library.model.Author;
import com.example.libarary.library.model.Book;
import com.example.libarary.library.model.Country;
import com.example.libarary.library.model.dto.AuthorDto;
import com.example.libarary.library.model.exeptions.CountryNotFoundException;
import com.example.libarary.library.repository.AuthorRepository;
import com.example.libarary.library.repository.CountryRepository;
import com.example.libarary.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }


    @Override
    public Optional<Author> findByName(String name) {
        return this.authorRepository.findByName(name);
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        Author author = new Author(name, surname, country);
        return Optional.of(this.authorRepository.save(author));
    }



    @Override
    public void deleteById(Long id) {
        this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        this.authorRepository.deleteByName(authorDto.getName());
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(()-> new CountryNotFoundException(authorDto.getCountry()));
        Author author = new Author(
                authorDto.getName(),
                authorDto.getSurname(),
                country
        );
        this.authorRepository.save(author);
        return Optional.of(author);
    }
}

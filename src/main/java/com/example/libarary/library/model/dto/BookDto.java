package com.example.libarary.library.model.dto;

import com.example.libarary.library.model.Author;
import com.example.libarary.library.model.enumerations.Category;
import lombok.Data;

import javax.persistence.*;

@Data
public class BookDto {

    private String name;


    private Category category;

    private Long author;

    private Integer availableCopies;

    public BookDto(String name, Category category, Long author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public BookDto(){

    }
}

package com.example.libarary.library.service.impl;

import com.example.libarary.library.model.enumerations.Category;
import com.example.libarary.library.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Override
    public List<Category> findAll() {
        return Arrays.asList(Category.values());
    }
}

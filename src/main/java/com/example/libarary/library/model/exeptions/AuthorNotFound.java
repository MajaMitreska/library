package com.example.libarary.library.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AuthorNotFound extends RuntimeException {
    public AuthorNotFound(Long authorId) {
        super(String.format("Author with id: %d is not found",authorId));
    }
}


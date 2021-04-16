package com.example.libarary.library.model.exeptions;

public class NoCopiesOfBookException extends RuntimeException {
    public NoCopiesOfBookException(String name) {
        super(String.format("There are no copies of the book: %s", name));
    }
}

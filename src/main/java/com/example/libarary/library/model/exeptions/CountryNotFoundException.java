package com.example.libarary.library.model.exeptions;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long countryId) {
        super(String.format("Country with id: %d not found", countryId));
    }
}

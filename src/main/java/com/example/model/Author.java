package com.example.model;

public record Author(
        Integer id,
        Integer idBook,
        String firstName,
        String lastName
) {
}

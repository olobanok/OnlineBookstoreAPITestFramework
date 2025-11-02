package com.example.api;

import io.restassured.response.ValidatableResponse;

public class AuthorsClient extends BaseClient {

    private static final String AUTHORS_URL = "/com/example/api/v1/Authors";
    private static final String AUTHORS_ID_URL = "/com/example/api/v1/Authors/{id}";
    private static final String AUTHORS_BOOK_ID_URL = "/com/example/api/v1/Authors/authors/books/{idBook}";

    public ValidatableResponse list() {
        return givenBase().when().get(AUTHORS_URL).then();
    }

    public ValidatableResponse getById(Object id) { // Object so tests can pass invalid ids too
        return givenBase().when().get(AUTHORS_ID_URL, id).then();
    }

    public ValidatableResponse getByBookId(Object id) { // Object so tests can pass invalid ids too
        return givenBase().when().get(AUTHORS_BOOK_ID_URL, id).then();
    }

    public ValidatableResponse create(Object body) {
        return givenBase().body(body).when().post(AUTHORS_URL).then();
    }

    public ValidatableResponse update(Object id, Object body) {
        return givenBase().body(body).when().put(AUTHORS_ID_URL, id).then();
    }

    public ValidatableResponse delete(Object id) {
        return givenBase().when().delete(AUTHORS_ID_URL, id).then();
    }

}

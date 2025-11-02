package com.example.api;

import io.restassured.response.ValidatableResponse;

public class BooksClient extends BaseClient {

    private static final String BOOKS_URL = "/api/v1/Books";
    private static final String BOOKS_ID_URL = "/api/v1/Books/{id}";

    public ValidatableResponse list() {
        return givenBase().when().get(BOOKS_URL).then();
    }

    public ValidatableResponse getById(Object id) { // Object so tests can pass invalid ids too
        return givenBase().when().get(BOOKS_ID_URL, id).then();
    }

    public ValidatableResponse create(Object body) {
        return givenBase().body(body).when().post(BOOKS_URL).then();
    }

    public ValidatableResponse update(Object id, Object body) {
        return givenBase().body(body).when().put(BOOKS_ID_URL, id).then();
    }

    public ValidatableResponse delete(Object id) {
        return givenBase().when().delete(BOOKS_ID_URL, id).then();
    }
}

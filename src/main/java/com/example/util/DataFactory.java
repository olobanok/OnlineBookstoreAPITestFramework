package com.example.util;

import com.github.javafaker.Faker;
import com.example.model.Author;
import com.example.model.Book;

import java.time.LocalDateTime;
import java.util.Random;

public class DataFactory {

    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    private DataFactory() {}

    public static Book randomBook(Integer id) {
        return new Book(
                id,
                FAKER.book().title(),
                FAKER.lorem().sentence(8),
                FAKER.number().numberBetween(50, 800),
                FAKER.lorem().paragraph(),
                LocalDateTime.now().withNano(0).minusMonths(RANDOM.nextInt(1, 100)).toString()
        );
    }

    public static Author randomAuthor(Integer id, Integer idBook) {
        return new Author(
                id,
                idBook,
                FAKER.name().firstName(),
                FAKER.name().lastName()
        );
    }
}

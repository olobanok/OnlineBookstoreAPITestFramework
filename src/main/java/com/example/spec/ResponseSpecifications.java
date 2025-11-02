package com.example.spec;

import com.example.config.ConfigManager;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;

public class ResponseSpecifications {

    private static final String TITLE_JSON_PATH = "title";
    private static final String ERROR_JSON_PATH = "errors[''][0]";

    private ResponseSpecifications() {
    }

    private static long timeoutMs() {
        return TimeUnit.SECONDS.toMillis(ConfigManager.getConfig().timeoutSeconds());
    }

    public static final ResponseSpecification OK_200 =
            new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .expectResponseTime(Matchers.lessThan(timeoutMs()))
                    .build();

    public static final ResponseSpecification NOT_FOUND_404 =
            new ResponseSpecBuilder()
                    .expectStatusCode(404)
                    .expectBody(TITLE_JSON_PATH, equalTo("Not Found"))
                    .build();

    public static final ResponseSpecification BAD_REQUEST_400 =
            new ResponseSpecBuilder()
                    .expectStatusCode(400)
                    .expectBody(TITLE_JSON_PATH, equalTo("One or more validation errors occurred."))
                    .build();

    public static final ResponseSpecification BAD_REQUEST_EMPTY_BODY_400 =
            new ResponseSpecBuilder()
                    .expectStatusCode(400)
                    .expectBody(TITLE_JSON_PATH, equalTo("One or more validation errors occurred."))
                    .expectBody(ERROR_JSON_PATH, equalTo("A non-empty request body is required."))
                    .build();



}

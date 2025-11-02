package com.example.api;

import com.example.config.ConfigManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseClient {

    private final RequestSpecification req;

    protected BaseClient() {
        req = new RequestSpecBuilder()
                .setBaseUri(ConfigManager.getConfig().baseUrl())
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .addFilter(new AllureRestAssured())
                .log(LogDetail.URI)
                .build();
    }

    protected RequestSpecification givenBase() { return given().spec(req); }

}

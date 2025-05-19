package ru.netology.rest;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;

public class AccountsSchemaTest {

    @Test
    void accountsSchemaValidationTest() {
        given()
                .baseUri("http://localhost:9999")
                .when()
                    .get("/api/v1/demo/accounts")
                .then()
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("accounts.schema.json"));

    }

    @Test
    void currencyValidationPositiveTest() {
        given()
                .baseUri("http://localhost:9999")
                .when()
                    .get("/api/v1/demo/accounts")
                .then()
                .body("currency", hasItems("RUB", "USD"));
    }

    @Test
    void currencyValidationNegativeTest() {
        given()
                .baseUri("http://localhost:9999")
                .when()
                .get("/api/v1/demo/accounts")
                .then()
                .body("currency", hasItem("RUR"));
    }
}

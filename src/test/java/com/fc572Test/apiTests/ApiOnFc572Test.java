package com.fc572Test.apiTests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiOnFc572Test {

    static String pageUrl="";

    @BeforeAll
    public static void setUp() {
        com.fc572Test.selenium.pom.ConfigFileReader configFileReader = new com.fc572Test.selenium.pom.ConfigFileReader();
        pageUrl = configFileReader.getApplicationUrl() + "/api/resource/";
    }

    @Test
    void getRequestTest() {
        String id = "12345";
        given()
                .when()
                .get(pageUrl + id)
                .then()
                .statusCode(200)
                .body("id", equalTo("12345"))
                .body("message", equalTo("Sample data associated with ID 12345"));
    }

    @Test
    void postRequestTest() {
        given()
                .when()
                .post(pageUrl)
                .then()
                .statusCode(201)
                .body("id", equalTo("12345"));
    }
}

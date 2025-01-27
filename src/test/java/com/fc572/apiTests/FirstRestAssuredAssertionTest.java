package com.fc572.apiTests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FirstRestAssuredAssertion {

    @Test
    void getRequestReturns200() {
        given()
                .when()
                .get("https://ur5pc7yr1j.execute-api.eu-west-2.amazonaws.com/dev/httpcodes/200")
                .then()
                .statusCode(200);
    }

    @Test
    void getRequestReturns100() {
        given()
                .when()
                .get("https://ur5pc7yr1j.execute-api.eu-west-2.amazonaws.com/dev/httpcodes/100")
                .then()
                .statusCode(100)
                .post()
    }
}

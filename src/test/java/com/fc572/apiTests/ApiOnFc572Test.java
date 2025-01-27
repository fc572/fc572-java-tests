package com.fc572.apiTests;

import com.fc572.playwright.ConfigFileReader;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import static io.restassured.RestAssured.given;

public class ApiOnFc572 {

    @BeforeAll
    public static void setUp() {
        com.fc572.selenium.pom.ConfigFileReader configFileReader = new com.fc572.selenium.pom.ConfigFileReader();
        String pageUrl = configFileReader.getApplicationUrl() + ";
    }

    @Test
    void getRequestTest() {
        given()
                .when()
                .get(ConfigFileReader)
                .then()
                .statusCode(200);
    }
}

package com.fc572.selenium.pom;

import com.fc572.selenium.pom.pages.LoginPage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPageTest {

    private static WebDriver webDriver;
    private static LoginPage loginPage;
    private static ConfigFileReader configFileReader;

    @BeforeAll
    public static void setup() {
        configFileReader = new ConfigFileReader();
        webDriver = new ChromeDriver();
        String pageUrl = configFileReader.getApplicationUrl() + "/htmls/loginPage.html";
        webDriver.navigate().to(pageUrl);
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
    }

    @Test
    @DisplayName("Login page")
    public void checkLogin() {
        loginPage.login("Neo", "redpill");
        assertTrue(loginPage.verifyLogin());
    }

    @Test
    @DisplayName("Login page reset")
    public void checkResetButton() {
        checkLogin();
        loginPage.reset();
        assertTrue(loginPage.verifyReset());
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

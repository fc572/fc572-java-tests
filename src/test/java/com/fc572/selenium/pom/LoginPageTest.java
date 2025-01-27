package com.fc572.selenium.pom;

import com.fc572.selenium.pom.pages.LoggedInPage;
import com.fc572.selenium.pom.pages.LoginPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPageTest {

    private static WebDriver webDriver;
    private static LoginPage loginPage;
    private static LoggedInPage loggedInPage;
    private static ConfigFileReader configFileReader;

    @BeforeEach
    public void setup() {
        configFileReader = new ConfigFileReader();
        webDriver = new ChromeDriver();
        String pageUrl = configFileReader.getApplicationUrl() + "/loginPage.html";
        webDriver.navigate().to(pageUrl);
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
        loggedInPage = new LoggedInPage(webDriver);
    }


    @Test
    @DisplayName("Login page")
    public void checkLoginSuccess() {
        loginPage.login("Neo", "redpill");
        assertTrue(loggedInPage.verifyLogin());
    }


    @Test
    @DisplayName("Login page fails")
    public void checkLoginFails() {
        loginPage.login("Neo", "bluePill");
        assertTrue(loginPage.verifyFailedLogin());
    }

    @Test
    @DisplayName("Login page reset")
    public void checkResetButton() {
        checkLoginFails();
        loginPage.reset();
        assertTrue(loginPage.verifyReset());
    }

    @AfterEach
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

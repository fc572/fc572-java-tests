package com.fc572.selenium.pom;

import com.fc572.selenium.pom.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginPageTest {

    private WebDriver webDriver;
    private LoginPage loginPage;
    private ConfigFileReader configFileReader;

    @BeforeEach
    public void setup() {
        configFileReader = new ConfigFileReader();
        webDriver = new ChromeDriver();
        String pageUrl = configFileReader.getApplicationUrl() + "/htmls/loginPage.html";
        webDriver.navigate().to(pageUrl);
        webDriver.manage().window().maximize();
        loginPage = new LoginPage(webDriver);
    }

    @Test
    public void checkLogin() {
        loginPage.login("Neo", "redpill");
        assertTrue(loginPage.verifyLogin());
    }

    @Test
    public void checkResetButton() {
        checkLogin();
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

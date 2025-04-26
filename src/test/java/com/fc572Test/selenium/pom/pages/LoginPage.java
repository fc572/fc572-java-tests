package com.fc572Test.selenium.pom.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final Logger logger = LogManager.getLogger();
    WebDriver webDriver;

    @FindBy(id = "field_uname")
    WebElement field_uname;

    @FindBy(id = "field_pwd")
    WebElement field_pwd;

    @FindBy(id = "loginBtn")
    WebElement loginbtn;

    @FindBy(id = "resetBtn")
    WebElement resetbtn;

    @FindBy(id = "demo")
    WebElement textHolder;

    WebDriverWait waitForMe;

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitForMe = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
    }

    public void login(String username, String password) {
        field_uname.sendKeys(username);
        field_pwd.sendKeys(password);
        loginbtn.click();
    }

    public boolean verifyFailedLogin() {
        try {
            waitForMe.until(ExpectedConditions.visibilityOf(textHolder));
        } catch (Exception e) {
            logger.error("Exception: ", e);
        }
        return textHolder.getText().contains("Please enter the correct user name and password");
    }

    public void reset() {
        resetbtn.click();
    }

    public boolean verifyReset() {
        return textHolder.getText().equalsIgnoreCase("");
    }
}

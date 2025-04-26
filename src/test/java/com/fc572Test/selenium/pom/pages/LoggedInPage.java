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

public class LoggedInPage {

    private static final Logger logger = LogManager.getLogger();
    WebDriver webDriver;

    @FindBy(tagName = "h1")
    WebElement header;

    @FindBy(id = "whiteRabbit")
    WebElement textHolder;

    WebDriverWait waitForMe;

    public LoggedInPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitForMe = new WebDriverWait(this.webDriver, Duration.ofSeconds(5));
    }

    public boolean verifyLogin() {
        try {
            waitForMe.until(ExpectedConditions.visibilityOf(textHolder));
        } catch (Exception e) {
            logger.error("Exception: ", e);
        }
        System.out.println("textHolder.getText " + textHolder.getText());
        return textHolder.getText().equals("Welcome Neo. You have followed the white rabbit...");
    }
}

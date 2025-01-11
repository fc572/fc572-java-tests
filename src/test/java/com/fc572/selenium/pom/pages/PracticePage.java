package com.fc572.selenium.pom.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticePage {

    private static final Logger logger = LogManager.getLogger();
    WebDriver webDriver;

    @FindBy(linkText = "linkToPageTwo")
    WebElement fullLinkText;

    @FindBy(partialLinkText = "linkToPa")
    WebElement partialLinkText;

    @FindBy(xpath = "//label[@for='linkToPageTwo']")
    WebElement linkText;

    WebDriverWait waitForMe;

    public PracticePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitForMe = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
    }

    public void goOntoPageTwoUsingFullLinkText() {
        fullLinkText.click();
    }

    public void goOntoPageTwoUsingPartialLinkText() {
        partialLinkText.click();
    }

    public String getTextOfElementLinkText() {
        return linkText.getText();
    }

    public void waitForPageToLoad() {
        try {
            waitForMe.until(ExpectedConditions.visibilityOf(linkText));
        } catch (Exception elementNotFound) {
            logger.error("The practice page didn't load on time", elementNotFound);
        }
    }
}

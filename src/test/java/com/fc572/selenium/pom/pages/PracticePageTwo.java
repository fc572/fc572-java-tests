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

public class PracticePageTwo {

    private static final Logger logger = LogManager.getLogger();

    WebDriver webDriver;
    WebDriverWait waitForMe;

    public PracticePageTwo(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        waitForMe = new WebDriverWait(this.webDriver, Duration.ofSeconds(3));
    }

    @FindBy(className = "link-test-container")
    WebElement linkTestContainer;

    @FindBy(id = "return-link")
    WebElement returnLink;

    public String getPageTwoText() {
        return linkTestContainer.getText();
    }

    public void clickReturnLink() {
        returnLink.click();
    }

    public void waitForPageToLoad() {
        try {
            waitForMe.until(ExpectedConditions.visibilityOf(returnLink));
        } catch (Exception e) {
            logger.error("Exception: ", e);
        }
    }
}

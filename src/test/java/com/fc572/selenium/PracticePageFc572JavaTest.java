package com.fc572.selenium;

import com.fc572.selenium.pom.ConfigFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class PracticePageFc572JavaTest {

    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger();

    @BeforeEach
    public void setUp() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        webDriver = new ChromeDriver();
        String pageUrl= configFileReader.getApplicationUrl()+"/htmls/testPlaygroundPage.html";
        webDriver.navigate().to(pageUrl);
        webDriver.manage().window().maximize();
    }

    @AfterEach
    public void destroyTest() {
        webDriver.close();
    }

    @Test
    public void thisIsATest() {
        WebElement findMeById = webDriver.findElement(By.id("fieldId"));
        WebElement findMeByName = webDriver.findElement(By.name("fieldName"));
        WebElement findMeByClassName = webDriver.findElement(By.className("fieldClass"));
        WebElement findMeByLinkText = webDriver.findElement(By.linkText("linkToPageTwo"));
        WebElement findMeByPartialLinkText = webDriver.findElement(By.partialLinkText("linkToPa"));
        WebElement findMeByCssSelector = webDriver.findElement(By.cssSelector("section.main_container ul li:nth-child(1)"));
        WebElement findMeByCssSelector2 = webDriver.findElement(By.cssSelector("section.main_container ul:nth-child(2) > li:nth-child(1) > ul:nth-child(1) > li:nth-child(1) > ol:nth-child(1) > li:nth-child(2)"));
        WebElement findMeByCssSelector3 = webDriver.findElement(By.cssSelector("section.main_container ul:nth-child(2) > li:nth-child(2) > ul:nth-child(1) > li:nth-child(2)"));
        WebElement findMeByXPath = webDriver.findElement(By.xpath("//input[@type='checkbox' and @value='HappyTesting']"));

        try{
            findMeById.sendKeys("Found By.id");
        }catch (Exception elementNotFound){
            logger.error("The element identified by field id was not found");
        }
        findMeByName.sendKeys("Found By.Name");
        findMeByClassName.click();
        assertTrue(findMeByLinkText.getText().equalsIgnoreCase("linkToPageTwo"));
        assertTrue(findMeByPartialLinkText.getText().equalsIgnoreCase("linkToPageTwo"));
        assertTrue(findMeByCssSelector.getText().contains("Item 1"));
        assertTrue(findMeByCssSelector2.getText().contains("Sub Sub Item 1.2"));
        assertTrue(findMeByCssSelector3.getText().contains("Sub Item 2.3"));
        findMeByXPath.click();
        try{
            webDriver.findElement(By.xpath("//input[@name='pickMe' and @value='HappyTesting']")).click();
        }catch (Exception elementNotFound){
            logger.error(String.valueOf(elementNotFound));
            fail();
        }
    }
}

package com.fc572.selenium.pom;

import com.fc572.selenium.pom.pages.PracticePage;
import com.fc572.selenium.pom.pages.PracticePageTwo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForthAndBackTest {

    private static WebDriver webDriver;
    private static PracticePage practicePage;
    private static PracticePageTwo practicePageTwo;
    private static final Logger logger = LogManager.getLogger();

    @BeforeAll
    public static void setUpClass() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        webDriver = new ChromeDriver();
        String pageUrl = configFileReader.getApplicationUrl() + "/testPlaygroundPage.html";
        webDriver.navigate().to(pageUrl);
        webDriver.manage().window().maximize();
        practicePage = new PracticePage(webDriver);
        practicePageTwo = new PracticePageTwo(webDriver);
    }

    @AfterAll
    public static void tearDownClass() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @ParameterizedTest
    @MethodSource("linkNavigationDataProvider")
    @DisplayName("Navigating the pages")
    public void testForthAndBack(Runnable navigationMethod) {
        logger.info("Starting test for navigating forth and back");

        navigationMethod.run();
        practicePageTwo.waitForPageToLoad();
        assertTrue(practicePageTwo.getPageTwoText().contains("You have clicked a link and landed here."));

        practicePageTwo.clickReturnLink();
        practicePage.waitForPageToLoad();
        assertEquals("Find the link and click it", practicePage.getTextOfElementLinkText(),
                "Expected text does not match.");

        logger.info("Test completed successfully");
    }

    private static Stream<Arguments> linkNavigationDataProvider() {
        return Stream.of(
                Arguments.of((Runnable) practicePage::goOntoPageTwoUsingFullLinkText),
                Arguments.of((Runnable) practicePage::goOntoPageTwoUsingPartialLinkText)
        );
    }
}

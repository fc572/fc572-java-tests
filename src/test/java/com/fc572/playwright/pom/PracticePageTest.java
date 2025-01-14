package com.fc572.playwright.pom;

import com.fc572.playwright.ConfigFileReader;
import com.fc572.playwright.utils.BrowserFactory;
import com.fc572.playwright.utils.ScreenshotUtil;
import com.fc572.pom.PracticePage;
import com.fc572.pom.PracticePageTwo;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PracticePageTest {
    private static Playwright playwright;
    private static Browser browser;
    private Page page;
    private PracticePage practicePage;
    private PracticePageTwo practicePageTwo;
    private static String pageUrl;

    @BeforeAll
    static void setUpAll() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        browser = BrowserFactory.getBrowser();
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        pageUrl = configFileReader.getApplicationUrl() + "/htmls/testPlaygroundPage.html";
    }

    @BeforeEach
    void setUp() {
        page = browser.newPage();
        practicePage = new PracticePage(page);
    }

    @Test
    @DisplayName("Verify entering text and button click on the Practice Page")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Practice Page Testing")
    void testEnterTextAndButtonClick() {
        practicePage.navigateTo(pageUrl);  // Replace with actual URL or path
        practicePage.enterTextById("Testing ID field");
        practicePage.enterTextByName("Testing name field");
        practicePage.clickButton();
        assertTrue(practicePage.getDemoText().equalsIgnoreCase("Well done, you found me using the By.className selector"));
    }

    @Test
    @DisplayName("Verify going forth and back from Practice Page")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Practice Page Testing")
    void testLinkNavigation() {
        practicePage.navigateTo(pageUrl);  // Replace with actual URL or path
        practicePage.clickLinkToPageTwo();
        practicePageTwo = new PracticePageTwo(page);
        assertTrue(practicePageTwo.getTitle().equalsIgnoreCase("Page to Test Links"));
        ScreenshotUtil.takeScreenshot(page, "On the second page");
        practicePageTwo.clickReturnLink();
        assertTrue(practicePage.getTitle().equalsIgnoreCase("Web Page for Testing"));
        ScreenshotUtil.takeScreenshot(page, "Returned on the first page");
    }

    @Test
    @DisplayName("Verify radio and check boxes selection on Practice Page")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Practice Page Testing")
    void testCheckboxAndRadioButtonSelection() {
        practicePage.navigateTo(pageUrl);
        practicePage.selectCheckbox();
        practicePage.selectRadioButton();
        assertTrue(page.isChecked("input#chooseMe"));
        assertTrue(page.isChecked("input[type='radio'][value='HappyTesting']"));
    }

    @AfterEach
    void tearDown() {
        page.close();
    }

    @AfterAll
    static void tearDownAll() {
        browser.close();
        playwright.close();
    }
}

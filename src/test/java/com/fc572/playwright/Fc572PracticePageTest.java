package com.fc572.playwright;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.Arrays;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Execution(ExecutionMode.SAME_THREAD)
public class Fc572PracticePageTest {

    protected static Playwright playwright;
    protected static Browser browser;
    protected static BrowserContext browserContext;
    private static String pageUrl;

    Page page;

    @BeforeAll
    static void setUpBrowser() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        pageUrl = configFileReader.getApplicationUrl() + "/testPlaygroundPage.html";
        playwright = Playwright.create();
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions().setHeadless(true)
                        .setArgs(Arrays.asList("--no-sandbox", "--disable-extensions", "--disable-gpu"))
        );
    }

    @BeforeEach
    void setUp() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterEach
    void closeContext() {
        browserContext.close();
    }

    @AfterAll
    static void tearDown() {
        browser.close();
        playwright.close();
    }

    @DisplayName("Locating element by id and name attribute")
    @Nested
    class LocatingElements {

        @BeforeEach
        void openTestPage() {
            page.navigate(pageUrl);
        }

        @DisplayName("By id")
        @Test
        void locateElementByID() {
            page.locator("#fieldId").fill("Francesco");
            assertThat(page.locator("#fieldId")).hasValue("Francesco");
        }

        @DisplayName("By field name attribute")
        @Test
        void locateElementByName() {
            page.locator("[name='fieldName']").fill("Francesco");
            assertThat(page.locator("[name='fieldName']")).hasValue("Francesco");

        }

        @DisplayName("Locating elements by locator and text")
        @Test
        void locateAButtonByText() {
            page.locator("button:has-text('button')").click();
            page.locator("button:text-is('I am a button')").click();
        }

        @DisplayName("Locating elements Aria role")
        @Test
        void locateAButtonByAriaRole() {
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("I am a button")).click();
        }
    }
}
package com.fc572.playwright.utils;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

public class BrowserFactory {
    private static Playwright playwright;
    private static Browser browser;

    public static synchronized Browser getBrowser() {
        if (playwright == null) {
            playwright = Playwright.create();
            String browserType = System.getProperty("browser");
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless"));

            browser = switch (browserType.toLowerCase()) {
                case "firefox" -> playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                case "webkit" -> playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
                default -> playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(isHeadless));
            };

        }
        return browser;
    }

    public static void closeAll() {
        if (browser != null) {
            browser.close();
            browser = null;
        }
        if (playwright != null) {
            playwright.close();
            playwright = null;
        }
    }
}
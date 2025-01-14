package com.fc572.playwright.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtil {

    private static final String SCREENSHOT_DIR = "build/allure-results/screenshots/";

    /**
     * Takes a screenshot and saves it to the specified directory.
     *
     * @param page           The Playwright page object.
     * @param screenshotName Name of the screenshot file.
     */
    public static void takeScreenshot(Page page, String screenshotName) {
        try {
            // Ensure the directory exists
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(screenshotPath);

            // Capture the screenshot
            byte[] screenshotBytes = page.screenshot();

            // Save the screenshot to the file
            Path filePath = screenshotPath.resolve(screenshotName + ".png");
            Files.write(filePath, screenshotBytes);

            // Attach the screenshot to the Allure report
            Allure.addAttachment(screenshotName, "image/png", new ByteArrayInputStream(screenshotBytes), ".png");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to capture screenshot: " + e.getMessage());
        }
    }
}

package com.fc572.playwright.utils;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class ScreenshotOnFailure implements TestWatcher {

    private static Page page;
    private static final String SCREENSHOT_DIR = "build/allure-results/screenshots/fails";
    private static final Logger logger = LogManager.getLogger();

    public ScreenshotOnFailure() {
        // No-arg constructor required by JUnit
    }

    public static void setPage(Page page) {
        ScreenshotOnFailure.page = page;
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {

        logger.info("Test Failed for test {}: ", context.getDisplayName());

        try {
            // Ensure the directory exists
            Path screenshotPath = Paths.get(SCREENSHOT_DIR);
            Files.createDirectories(screenshotPath);

            // Capture screenshot
            byte[] screenshotBytes = page.screenshot();

            // Create screenshot file path
            String screenshotName = context.getDisplayName().replace(" ", "_") + System.currentTimeMillis() + "_failure.png";
            Path filePath = screenshotPath.resolve(screenshotName);
            Files.write(filePath, screenshotBytes);

            // Attach to Allure report
            Allure.addAttachment("Screenshot on Failure", "image/png", new ByteArrayInputStream(screenshotBytes), ".png");

            System.out.println("Screenshot saved: " + filePath.toAbsolutePath());
        } catch (Exception e) {
            logger.error("The following exception occurred while attempting to take a screenshot: ", e);
        }
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        // No action needed on test success
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        // No action needed on test abort
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        // No action needed on test disable
    }
}


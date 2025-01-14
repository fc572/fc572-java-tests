package com.fc572.pom;

import com.microsoft.playwright.Page;

public class BasePage {

    protected Page page;

    public BasePage(Page page) {
        this.page = page;
    }

    // Common methods, e.g., navigating to a URL
    public void navigateTo(String url) {
        page.navigate(url);
    }

    public String getTitle() {
        return page.title();
    }
}

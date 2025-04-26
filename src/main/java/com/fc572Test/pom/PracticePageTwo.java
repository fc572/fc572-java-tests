package com.fc572Test.pom;

import com.microsoft.playwright.Page;

public class PracticePageTwo extends BasePage {

    private final String returnLinkSelector = "#return-link";

    public PracticePageTwo(Page page) {
        super(page);
    }

    public void clickReturnLink() {
        page.click(returnLinkSelector);
    }
}

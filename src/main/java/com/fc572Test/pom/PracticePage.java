package com.fc572Test.pom;

import com.microsoft.playwright.Page;

public class PracticePage extends BasePage {


    private final String idFieldSelector = "#fieldId";
    private final String nameFieldSelector = "input[name='fieldName']";
    private final String buttonSelector = ".fieldClass";
    private final String demoTextSelector = "#demo";
    private final String linkToPageTwoSelector = "a[href='two.html']";
    private final String checkboxXPath = "//input[@type='checkbox' and @id='chooseMe']";
    private final String radioButtonSelector = "input[type='radio'][value='HappyTesting']";


    public PracticePage(Page page) {
        super(page);
    }


    public void enterTextById(String text) {
        page.fill(idFieldSelector, text);
    }

    public void enterTextByName(String text) {
        page.fill(nameFieldSelector, text);
    }

    public void clickButton() {
        page.click(buttonSelector);
    }

    public String getDemoText() {
        return page.innerText(demoTextSelector);
    }

    public void clickLinkToPageTwo() {
        page.click(linkToPageTwoSelector);
    }

    public void selectCheckbox() {
        page.locator(checkboxXPath).check();
    }

    public void selectRadioButton() {
        page.click(radioButtonSelector);
    }
}
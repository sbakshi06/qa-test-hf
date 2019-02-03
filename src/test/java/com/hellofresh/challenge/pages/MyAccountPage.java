package com.hellofresh.challenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MyAccountPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "account")
    private WebElement accountNameBlock;

    @FindBy(className = "info-account")
    private WebElement welcomeMessageBlock;

    @FindBy(className = "logout")
    private WebElement logoutButton;

    @FindBy(css = "h1")
    private WebElement heading;

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public String getHeading() {
        return wait.until(ExpectedConditions.visibilityOf(heading)).getText();
    }

    public String getAccountName() {
        return accountNameBlock.getText();
    }

    public String getAccountWelcomeMessage() {
        return welcomeMessageBlock.getText();
    }

    public boolean isLogoutDisplayed() {
        return logoutButton.isDisplayed();
    }

    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }

}

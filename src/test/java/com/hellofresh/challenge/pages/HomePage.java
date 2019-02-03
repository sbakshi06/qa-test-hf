package com.hellofresh.challenge.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "login")
    private WebElement loginButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public SignInPage clickOnSignInButton() {
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
        return new SignInPage(driver);
    }


}

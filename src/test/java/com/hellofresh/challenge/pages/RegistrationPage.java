package com.hellofresh.challenge.pages;

import com.hellofresh.challenge.model.UserDetail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "id_gender2")
    private WebElement genderField;

    @FindBy(id = "customer_firstname")
    private WebElement firstnameField;

    @FindBy(id = "customer_lastname")
    private WebElement lastnameField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "days")
    private WebElement dayField;

    @FindBy(id = "months")
    private WebElement monthField;

    @FindBy(id = "years")
    private WebElement yearField;

    @FindBy(id = "company")
    private WebElement companyField;

    @FindBy(id = "address1")
    private WebElement address1Field;

    @FindBy(id = "address2")
    private WebElement address2Field;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id ="id_state")
    private WebElement stateField;

    @FindBy(id = "postcode")
    private WebElement postcodeField;

    @FindBy(id = "other")
    private WebElement otherField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "phone_mobile")
    private WebElement phonemobileField;

    @FindBy(id = "alias")
    private WebElement aliasField;

    @FindBy(css = "h1")
    private WebElement heading;

    @FindBy(id = "submitAccount")
    private WebElement submitAccountButton;

    @FindBy(className = "account")
    private WebElement accountNameBlock;

    @FindBy(className = "info-account")
    private WebElement welcomeMessageBlock;

    @FindBy(className = "logout")
    private WebElement logoutButton;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver,this);
    }

    public void createUser(UserDetail userDetail) {
        wait.until(ExpectedConditions.visibilityOf(genderField)).click();
        firstnameField.sendKeys(userDetail.getName());
        lastnameField.sendKeys(userDetail.getSurname());
        passwordField.sendKeys(userDetail.getPassword());

        Select select = new Select(dayField);
        select.selectByValue(userDetail.getBirthDate());

        select = new Select(monthField);
        select.selectByValue(userDetail.getBirthMonth());

        select = new Select(yearField);
        select.selectByValue(userDetail.getBirthYear());

        companyField.sendKeys(userDetail.getCompany());
        address1Field.sendKeys(userDetail.getAddress1());
        address2Field.sendKeys(userDetail.getAddress2());
        cityField.sendKeys(userDetail.getCity());

        select = new Select(stateField);
        select.selectByVisibleText(userDetail.getState());

        postcodeField.sendKeys(userDetail.getPostCode());
        otherField.sendKeys(userDetail.getOther());
        phoneField.sendKeys(userDetail.getPhone());
        phonemobileField.sendKeys(userDetail.getMobile());
        aliasField.sendKeys(userDetail.getAlias());
        submitAccountButton.click();

    }

    public String getPageHeading() {
        return wait.until(ExpectedConditions.visibilityOf(heading)).getText();
    }

    public String getAccountName() {
        return accountNameBlock.getText();
    }

    public String getWelcomeMessage() {
        return welcomeMessageBlock.getText();
    }

    public boolean checkLogoutButton() {
        return logoutButton.isDisplayed();
    }

    public String getCurrentPageURL() {
        return driver.getCurrentUrl();
    }

}

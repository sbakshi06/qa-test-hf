package com.hellofresh.challenge.pages;

import com.hellofresh.challenge.base.TestBase;
import com.hellofresh.challenge.model.UserDetail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.hellofresh.challenge.constants.TestConstant.EXISTING_USER_EMAIL_KEY;
import static com.hellofresh.challenge.constants.TestConstant.EXISTING_USER_PASSWORD_KEY;


public class SignInPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(className = "login")
    private WebElement loginButton;

    @FindBy(id = "email_create")
    private WebElement createNewUserEmailField;

    @FindBy(id = "SubmitCreate")
    private WebElement createNewUserSubmitButton;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "passwd")
    private WebElement passwordField;

    @FindBy(id = "SubmitLogin")
    private WebElement submitLoginButton;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public RegistrationPage getRegistrationPage(UserDetail userDetail) {
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();
        createNewUserEmailField.sendKeys(userDetail.getEmail());
        createNewUserSubmitButton.click();
        return new RegistrationPage(driver);
    }

    public MyAccountPage existingUserLogin() {

        emailField.sendKeys(TestBase.PROPS.getProperty(EXISTING_USER_EMAIL_KEY));
        passwordField.sendKeys(TestBase.PROPS.getProperty(EXISTING_USER_PASSWORD_KEY));
        submitLoginButton.click();
        return new MyAccountPage(driver);

    }

}


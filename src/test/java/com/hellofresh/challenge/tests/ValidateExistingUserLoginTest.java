package com.hellofresh.challenge.tests;

import com.hellofresh.challenge.base.TestBase;
import com.hellofresh.challenge.base.TestFailureListener;
import com.hellofresh.challenge.pages.HomePage;
import com.hellofresh.challenge.pages.MyAccountPage;
import com.hellofresh.challenge.pages.SignInPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.hellofresh.challenge.constants.TestConstant.FULL_NAME_KEY;

@Listeners({TestFailureListener.class})
public class ValidateExistingUserLoginTest extends TestBase {
    private MyAccountPage myAccountPage;

    @BeforeTest
    public void setup() {
        initWebDriver();
        HomePage homePage = new HomePage(driver);
        SignInPage signInPage = homePage.clickOnSignInButton();
        myAccountPage = signInPage.existingUserLogin();
    }

    @Test
    public void validateExistingUserLoginFlow() throws Exception {
        try {
            validatePageHeading();
            validateAccountName();
            validateWelcomeMessage();
            logoutButtonDisplayed();
            validateCurrentPageURL();
        } catch (Exception e) {
            LOGGER.error("Exception occurred in Existing User Login Test : ", e);
            throw e;
        }
    }

    private void validatePageHeading() {
        Assert.assertEquals(myAccountPage.getHeading(), "MY ACCOUNT");
        LOGGER.info("Page heading is validated for existing user");
    }

    private void validateAccountName() {
        String expectedAccountName = PROPS.getProperty(FULL_NAME_KEY);
        Assert.assertEquals(myAccountPage.getAccountName(), expectedAccountName);
        LOGGER.info("Account name is validated for existing user");
    }

    private void validateWelcomeMessage() {
        Assert.assertTrue(myAccountPage.getAccountWelcomeMessage().contains("Welcome to your account."));
        LOGGER.info("Welcome message is validated for existing user");
    }

    private void logoutButtonDisplayed() {
        Assert.assertTrue(myAccountPage.isLogoutDisplayed());
        LOGGER.info("Logout button is diplayed for existing user");
    }

    private void validateCurrentPageURL() {
        Assert.assertTrue(myAccountPage.getCurrentPageURL().contains("controller=my-account"));
        LOGGER.info("Current page URL is validated for existing user");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}

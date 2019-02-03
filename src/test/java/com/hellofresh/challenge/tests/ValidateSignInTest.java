package com.hellofresh.challenge.tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hellofresh.challenge.base.TestBase;
import com.hellofresh.challenge.base.TestFailureListener;
import com.hellofresh.challenge.model.UserDetail;
import com.hellofresh.challenge.pages.HomePage;
import com.hellofresh.challenge.pages.RegistrationPage;
import com.hellofresh.challenge.pages.SignInPage;
import com.hellofresh.challenge.utils.ConversionUtil;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Date;

@Listeners({TestFailureListener.class})
public class ValidateSignInTest extends TestBase {

    private SignInPage signInPage;
    private UserDetail userDetail;
    private RegistrationPage registrationPage;

    @BeforeTest
    public void setup() throws IOException {
        initWebDriver();
        HomePage homePage = new HomePage(driver);
        signInPage = homePage.clickOnSignInButton();
        userDetail = ConversionUtil.convertFileContentToObject("jsons/newuser.json",
                new TypeReference<UserDetail>() {
                });
    }

    @Test
    public void validateSignIn() throws Exception {
        try {
            createNewUser();
            validatePageHeading();
            validateAccountName();
            validateWelcomeMessage();
            validateLogoutButtonIsDisplayed();
            validateCurrentPageUrl();
        } catch (Exception e) {
            LOGGER.error("Exception occurred in SignIn Test : ", e);
            throw e;
        }
    }

//    @Test
//    public void sampleFailedTestForScreenshotUtility() {
//        Assert.assertEquals(true, false);
//    }

    private void createNewUser() {
        String timestamp = String.valueOf(new Date().getTime());
        String email = StringUtils.join("hf_challenge_", timestamp, "@hf", timestamp.substring(7), ".com");
        userDetail.setEmail(email);
        registrationPage = signInPage.getRegistrationPage(userDetail);
        registrationPage.createUser(userDetail);
        LOGGER.info("New User is created");
    }

    private void validatePageHeading() {
        Assert.assertEquals(registrationPage.getPageHeading(), "MY ACCOUNT");
        LOGGER.info("New User Heading Page is Validated");
    }

    private void validateAccountName() {
        Assert.assertEquals(registrationPage.getAccountName(), StringUtils.join(userDetail.getName(), StringUtils.SPACE, userDetail.getSurname()));
        LOGGER.info("New User " + userDetail.getName() + " Account name is validated ");
    }

    private void validateWelcomeMessage() {
        Assert.assertTrue(registrationPage.getWelcomeMessage().contains("Welcome to your account."));
        LOGGER.info("New User Welcome message is validated");
    }

    private void validateLogoutButtonIsDisplayed() {
        Assert.assertTrue(registrationPage.checkLogoutButton());
        LOGGER.info("New User logout button visibility is validated");
    }

    private void validateCurrentPageUrl() {
        Assert.assertTrue(registrationPage.getCurrentPageURL().contains("controller=my-account"));
        LOGGER.info("New User current page URL is validated");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

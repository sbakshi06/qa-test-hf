package com.hellofresh.challenge.tests;

import com.hellofresh.challenge.base.TestBase;
import com.hellofresh.challenge.base.TestFailureListener;
import com.hellofresh.challenge.pages.HomePage;
import com.hellofresh.challenge.pages.OrderConfirmationPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestFailureListener.class})
public class ValidateCartCheckoutTest extends TestBase {
    private OrderConfirmationPage orderConfirmationPage;

    @BeforeTest
    public void setup() {
        initWebDriver();
        new HomePage(driver).clickOnSignInButton().existingUserLogin();
        orderConfirmationPage = new OrderConfirmationPage(driver);
    }

    @Test
    public void validateOrderSuccessFlow() throws Exception {
        try {
            placeOrder();
            validateHeading();
            validateShippingButtonVisiblity();
            validatePaymentButtonVisibility();
            validateOrderConfirmationMessage();
            validateCurrentURL();
        } catch (Exception e) {
            LOGGER.error("Exception occurred in Order Success Test : ", e);
            throw e;
        }
    }

    private void placeOrder() {
        orderConfirmationPage.placeAnOrder();
        LOGGER.info("Order is placed with existing user account");
    }

    private void validateHeading() {
        Assert.assertEquals(orderConfirmationPage.getHeading(), "ORDER CONFIRMATION");
        LOGGER.info("Order Confirmation message is validated for placed order");
    }

    private void validateShippingButtonVisiblity() {
        Assert.assertTrue(orderConfirmationPage.getShippingButtonVisibility());
        LOGGER.info("Shipping option is visible in order confirmation page");
    }

    private void validatePaymentButtonVisibility() {
        Assert.assertTrue(orderConfirmationPage.getPaymentButtonVisibility());
        LOGGER.info("Payment option is visible in order confirmation page");
    }

    private void validateOrderConfirmationMessage() {
        Assert.assertTrue(orderConfirmationPage.getConfirmationMessage().contains("Your order on My Store is complete."));
        LOGGER.info("Successful completion of order message is validated");
    }

    private void validateCurrentURL() {
        Assert.assertTrue(orderConfirmationPage.getCurrentURL().contains("controller=order-confirmation"));
        LOGGER.info("Current URL is validated in order confirmation page");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

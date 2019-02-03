package com.hellofresh.challenge.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderConfirmationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(linkText = "Women")
    private WebElement womenTab;

    @FindBy(xpath = "//a[@title='Faded Short Sleeve T-shirts']/ancestor::li")
    private WebElement tShirtLink;

    @FindBy(name = "Submit")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']")
    private WebElement proceedToCheckoutLink;

    @FindBy(xpath = "//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']")
    private WebElement proceedToCheckoutCartLink;

    @FindBy(name = "processAddress")
    private WebElement addressLink;

    @FindBy(id = "uniform-cgv")
    private WebElement uniformLink;

    @FindBy(name = "processCarrier")
    private WebElement processCarrierLink;

    @FindBy(className = "bankwire")
    private WebElement bankWireLink;

    @FindBy(xpath = "//*[@id='cart_navigation']/button")
    private WebElement cartNavigationButton;

    @FindBy(css = "h1")
    private WebElement heading;

    @FindBy(xpath = "//li[@class='step_done step_done_last four']")
    private WebElement shippingButton;

    @FindBy(xpath = "//li[@id='step_end' and @class='step_current last']")
    private WebElement paymentButton;

    @FindBy(xpath = "//*[@class='cheque-indent']/strong")
    private WebElement orderConfirmationMessageBlock;

    public OrderConfirmationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public void placeAnOrder() {
        wait.until(ExpectedConditions.visibilityOf(womenTab)).click();
        tShirtLink.click();
        tShirtLink.click();
        wait.until(ExpectedConditions.visibilityOf(submitButton)).click();
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutLink)).click();
        wait.until(ExpectedConditions.visibilityOf(proceedToCheckoutCartLink)).click();
        wait.until(ExpectedConditions.visibilityOf(addressLink)).click();
        wait.until(ExpectedConditions.visibilityOf(uniformLink)).click();
        processCarrierLink.click();
        wait.until(ExpectedConditions.visibilityOf(bankWireLink)).click();
        wait.until(ExpectedConditions.visibilityOf(cartNavigationButton)).click();
    }

    public String getHeading() {
        return wait.until(ExpectedConditions.visibilityOf(heading)).getText();
    }

    public boolean getShippingButtonVisibility() {
        return shippingButton.isDisplayed();
    }

    public boolean getPaymentButtonVisibility() {
        return paymentButton.isDisplayed();
    }

    public String getConfirmationMessage() {
        return orderConfirmationMessageBlock.getText();
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }
}

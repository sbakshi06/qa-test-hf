package com.hellofresh.challenge.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.hellofresh.challenge.constants.TestConstant.*;

public class WebDriverFactory {

    public static WebDriver getDriver(String driverType) {
        WebDriver driver;
        if (driverType.equals(CHROME)) {
            System.setProperty(CHROME_DRIVER_KEY, System.getProperty(USER_DIR_KEY) + TestBase.PROPS.getProperty(CHROME_DRIVER_PATH_KEY));
            driver = new ChromeDriver();
        } else if (driverType.equals(IE)) {
            System.setProperty(IE_DRIVER_KEY, System.getProperty(USER_DIR_KEY) + TestBase.PROPS.getProperty(IE_DRIVER_PATH_KEY));
            driver = new InternetExplorerDriver();
        } else {
            System.setProperty(FIREFOX_DRIVER_KEY, System.getProperty(USER_DIR_KEY) + TestBase.PROPS.getProperty(FIREFOX_DRIVER_PATH_KEY));
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability("marionatte", false);
            FirefoxOptions options = new FirefoxOptions();
            options.merge(desiredCapabilities);
            driver = new FirefoxDriver(options);
        }
        return driver;
    }

}

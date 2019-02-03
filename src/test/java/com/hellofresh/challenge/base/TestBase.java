package com.hellofresh.challenge.base;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static com.hellofresh.challenge.constants.TestConstant.*;

public class TestBase {

    protected WebDriver driver;

    public static Properties PROPS;
    public static Logger LOGGER = Logger.getLogger(TestBase.class.getName());

    static {
        try {
            ClassLoader classLoader = TestBase.class.getClassLoader();
            PropertyConfigurator.configure(classLoader.getResource(LOG4J_PROPS_PATH));
            PROPS = new Properties();
            FileInputStream ip = new FileInputStream(classLoader.getResource(TEST_PROPS_PATH).getFile());
            PROPS.load(ip);
            LOGGER.info("Test properties loaded!");
        } catch (IOException e) {
            LOGGER.error("Failed to load Test Properties", e);
        }
    }

    public void initWebDriver() {
        initWebDriver(PROPS.getProperty(BROWSER_KEY), PROPS.getProperty(URL_KEY));
    }

    public void initWebDriver(String driverType, String baseUrl) {
        try {
            driver = WebDriverFactory.getDriver(driverType);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(baseUrl);
            LOGGER.info(driverType + " Web Driver is Initialised!");
        } catch (Exception e) {
            LOGGER.error("Failed to initialize Web Driver for " + driverType, e);
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected void failed(WebDriver driver, String testName) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File(StringUtils.join(SCREENSHOT_PATH, testName, UNDERSCORE, System.currentTimeMillis(), PNG_EXT)));
        } catch (IOException e) {
            LOGGER.error("Failed to capture screenshot for test failure : " + testName, e);
        }
    }


}


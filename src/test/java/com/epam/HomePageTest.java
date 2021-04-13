package com.epam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class HomePageTest {

    public String baseUrl = "https://epam.com/";
    public WebDriver driver;


    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("86.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(),
                capabilities);
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void visitHomePage() {
        driver.get(baseUrl);
        WebElement message = driver.findElement(By.className("title-slider__slide-row"));
        Assert.assertTrue(message.isDisplayed());
    }

    @Test
    public void checkTitleMessage() {
        driver.get(baseUrl);
        WebElement message = driver.findElement(By.className("title-slider__slide-row"));
        Assert.assertEquals(message.getText(), "Engineering the Future");
    }

    @AfterMethod
    public void close() {
        driver.close();
    }
}

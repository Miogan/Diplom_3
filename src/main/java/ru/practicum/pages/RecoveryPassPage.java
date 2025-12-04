package ru.practicum.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.practicum.pages.LoginPage.LOGIN_PAGE_ADDRESS;

public class RecoveryPassPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public static final String RECOVERY_PAGE_ADDRESS = "https://stellarburgers.education-services.ru/forgot-password";
    public static final By LOCATOR_LINK_LOGIN_TO_ACCOUNT = By.cssSelector("a.Auth_link__1fOlj[href='/login']");

    public RecoveryPassPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openRecoveryPage() {
        driver.get(RECOVERY_PAGE_ADDRESS);
    }

    public void clickButtonLoginAccount() {
        driver.findElement(LOCATOR_LINK_LOGIN_TO_ACCOUNT).click();
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_ADDRESS));
    }
}

package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.practicum.pages.HomePage.HOME_PAGE_ADDRESS;


public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    public static final String LOGIN_PAGE_ADDRESS = "https://stellarburgers.education-services.ru/login";
    private static final By LOCATOR_EMAIL_FIELD = By.xpath("//label[contains(text(),'Email')]/../input");
    private static final By LOCATOR_PASSWORD_FIELD = By.cssSelector("input[name='Пароль']");
    private static final By LOCATOR_LOGIN_BUTTON = By.xpath("//button[text()='Войти']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openLogPage() {
        driver.get(LOGIN_PAGE_ADDRESS);
    }
    public void fillingInUserEmail(String email) {
        driver.findElement(LOCATOR_EMAIL_FIELD).sendKeys(email);
    }

    public void fillingInUserPassword(String password) {
        driver.findElement(LOCATOR_PASSWORD_FIELD).sendKeys(password);
    }
    public void clickButtonLogin() {
        driver.findElement(LOCATOR_LOGIN_BUTTON).click();
        wait.until(ExpectedConditions.urlToBe(HOME_PAGE_ADDRESS));
    }
}

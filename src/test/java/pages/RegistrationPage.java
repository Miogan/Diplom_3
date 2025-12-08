package pages;


import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static pages.LoginPage.LOGIN_PAGE_ADDRESS;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public static final String REGISTRATION_PAGE_ADDRESS = "https://stellarburgers.education-services.ru/register";
    // Поля
    private static final By LOCATOR_NAME_FIELD = By.cssSelector("input[name='name']");
    private static final By LOCATOR_EMAIL_FIELD = By.xpath("//label[contains(text(),'Email')]/../input");
    private static final By LOCATOR_PASSWORD_FIELD = By.cssSelector("input[name='Пароль']");
    private static final By LOCATOR_BUTTON_REGISTRATION = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_medium__3zxIa");
    private static final By LOCATOR_ANY_ERROR = By.cssSelector("p.input__error.text_type_main-default");
    private static final By LOCATOR_LINK_LOGIN = By.cssSelector("a.Auth_link__1fOlj");
    private static final String TEXTERRORPASSWORD = "Некорректный пароль";

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Открываем  страницу регистрации
    @Step("Открытие страницы регистрации")
    public void openRegPage() {
        driver.get(REGISTRATION_PAGE_ADDRESS);
    }
    // Заполняем форму регистрации
    @Step("Заполнение поля Имя")
    public void fillingInUserName(String name) {
        driver.findElement(LOCATOR_NAME_FIELD).sendKeys(name);
    }

    @Step("Заполнение поля электронной почты")
    public void fillingInUserEmail(String email) {
        driver.findElement(LOCATOR_EMAIL_FIELD).sendKeys(email);
    }

    @Step("Заполнение поля пароля")
    public void fillingInUserPassword(String password) {
        driver.findElement(LOCATOR_PASSWORD_FIELD).sendKeys(password);
    }

    // Метод для проверки ошибки пароля
    @Step("Проверка корректности текста ошибки")
    public void checkPasswordError() {
        String errorText = getErrorMessageText();
        assertTrue("Некорректный пароль", errorText.contains(TEXTERRORPASSWORD));
    }

    // Нажимаем кнопку "Зарегистрироваться"
    @Step("Клик по кнопке регистрации")
    public void clickButtonRegistrationUncorrectPass() {
        driver.findElement(LOCATOR_BUTTON_REGISTRATION).click();
        String actualUrl = driver.getCurrentUrl();
        wait.until(ExpectedConditions.urlToBe(REGISTRATION_PAGE_ADDRESS));
        assertEquals("После неуспешной авторизации пользователь не остался на странице регистрации", REGISTRATION_PAGE_ADDRESS, actualUrl);
    }

    @Step("Клик по кнопке регистрации")
    public void clickButtonRegistration() {
        driver.findElement(LOCATOR_BUTTON_REGISTRATION).click();
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_ADDRESS));
        String actualUrl = driver.getCurrentUrl();
        assertEquals("!После регистрации пользователь переходит не на страницу авторизации", LOGIN_PAGE_ADDRESS, actualUrl);
    }

    @Step("Клик по ссылке авторизации")
    public void clickLinkLogin() {
        driver.findElement(LOCATOR_LINK_LOGIN).click();
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_ADDRESS));
        String actualUrl = driver.getCurrentUrl();
        assertEquals("При попытке войти через страницу регистрации открывается не страница авторизации", LOGIN_PAGE_ADDRESS, actualUrl);

    }
    public String getErrorMessageText() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.presenceOfElementLocated(LOCATOR_ANY_ERROR));
        return driver.findElement(LOCATOR_ANY_ERROR).getText();
    }
}

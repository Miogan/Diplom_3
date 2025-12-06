package ru.practicum;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.RecoveryPassPage;
import pages.RegistrationPage;

@Epic("Авторизация")
@Feature("Проверка авторизации с разных страниц")
public class LoginUserTest {
    private String name;
    private String email;
    private String password;

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Login home page")
    @Feature("Авторизация с домашней страницы")
    public void testLoginHomePage() throws InstantiationException {
        String name = "Иван";
        String email = "test_" + System.currentTimeMillis() + "@example.com";
        String password = RandomStringUtils.randomAlphanumeric(7);

        // Регистрируемся
        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();
        regPage.fillingInUserName(name);
        regPage.fillingInUserEmail(email);
        regPage.fillingInUserPassword(password);
        regPage.clickButtonRegistration();

        //вход по кнопке «Войти в аккаунт» на главной;
        var homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickButtonLoginToAccount();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(email);
        logPage.fillingInUserPassword(password);
        logPage.clickButtonLogin();
    }

    @Test
    @DisplayName("Login from home page personal account")
    @Feature("Авторизация с домашней страницы через персональный аккаунт")
    public void testLoginPersonalAccountPage() throws InstantiationException {
        String name = "Иван";
        String email = "test_" + System.currentTimeMillis() + "@example.com";
        String password = RandomStringUtils.randomAlphanumeric(7);

        // Регистрируемся
        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();
        regPage.fillingInUserName(name);
        regPage.fillingInUserEmail(email);
        regPage.fillingInUserPassword(password);
        regPage.clickButtonRegistration();

        //вход по кнопке «Личный кабинет» на главной;
        var homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickLinkPersonalAccount();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(email);
        logPage.fillingInUserPassword(password);
        logPage.clickButtonLogin();

    }

    @Test
    @DisplayName("Login from registration page personal account")
    @Feature("Авторизация с домашней страницы через персональный аккаунт")
    public void testLoginRegFormPage() throws InstantiationException {
        String name = "Иван";
        String email = "test_" + System.currentTimeMillis() + "@example.com";
        String password = RandomStringUtils.randomAlphanumeric(7);

        // Регистрируемся
        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();
        regPage.fillingInUserName(name);
        regPage.fillingInUserEmail(email);
        regPage.fillingInUserPassword(password);
        regPage.clickButtonRegistration();

        //вход по кнопке «Войти» на странице регистрации;
        regPage.openRegPage();
        regPage.clickLinkLogin();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(email);
        logPage.fillingInUserPassword(password);
        logPage.clickButtonLogin();

    }

    @Test
    @DisplayName("Login from recovery page personal account")
    @Feature("Авторизация с домашней страницы через персональный аккаунт")
    public void testLoginRecoveryPassPage() throws InstantiationException {
        String name = "Иван";
        String email = "test_" + System.currentTimeMillis() + "@example.com";
        String password = RandomStringUtils.randomAlphanumeric(7);

        // Регистрируемся
        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();
        regPage.fillingInUserName(name);
        regPage.fillingInUserEmail(email);
        regPage.fillingInUserPassword(password);
        regPage.clickButtonRegistration();

        //вход по кнопке «Войти» на странице восстановления пароля;
        var recPage = new RecoveryPassPage(driver);
        recPage.openRecoveryPage();
        recPage.clickButtonLoginAccount();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(email);
        logPage.fillingInUserPassword(password);
        logPage.clickButtonLogin();

    }
}

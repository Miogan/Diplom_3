package ru.practicum;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import pages.LoginPage;
import pages.RecoveryPassPage;
import pages.RegistrationPage;
import practicum.steps.UserSteps;

@Epic("Авторизация")
@Feature("Проверка авторизации с разных страниц")
public class LoginUserTest extends BaseTest{
    private String name;
    private String email;
    private String password;
    private UserSteps userSteps = new UserSteps();
    private User user;

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Before
    public void setUp() {
        user = new User();
        user.setEmail(System.currentTimeMillis()+ "@mail.ru");
        user.setPassword("pas"  + System.currentTimeMillis());
        user.setName(RandomStringUtils.randomAlphanumeric(5));
        userSteps
                .createUser(user);
    }

    @Test
    @DisplayName("Login home page")
    @Feature("Авторизация с домашней страницы")
    public void testLoginHomePage() throws InstantiationException  {

        // Регистрируемся
        WebDriver driver = factory.getDriver();

        //вход по кнопке «Войти в аккаунт» на главной;
        var homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickButtonLoginToAccount();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(user.getEmail());
        logPage.fillingInUserPassword(user.getPassword());
        logPage.clickButtonLogin();
    }

    @Test
    @DisplayName("Login from home page personal account")
    @Feature("Авторизация с домашней страницы через персональный аккаунт")
    public void testLoginPersonalAccountPage() throws InstantiationException {
        // Регистрируемся
        WebDriver driver = factory.getDriver();

        //вход по кнопке «Личный кабинет» на главной;
        var homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickLinkPersonalAccount();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(user.getEmail());
        logPage.fillingInUserPassword(user.getPassword());
        logPage.clickButtonLogin();

    }

    @Test
    @DisplayName("Login from registration page registration")
    @Feature("Авторизация со страницы регистрации")
    public void testLoginRegFormPage() throws InstantiationException {

        // Регистрируемся
        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();

        //вход по кнопке «Войти» на странице регистрации;
        regPage.openRegPage();
        regPage.clickLinkLogin();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(user.getEmail());
        logPage.fillingInUserPassword(user.getPassword());
        logPage.clickButtonLogin();

    }

    @Test
    @DisplayName("Login from recovery page personal account")
    @Feature("Авторизация с домашней страницы через персональный аккаунт")
    public void testLoginRecoveryPassPage() throws InstantiationException {

        // Регистрируемся
        WebDriver driver = factory.getDriver();

        //вход по кнопке «Войти» на странице восстановления пароля;
        var recPage = new RecoveryPassPage(driver);
        recPage.openRecoveryPage();
        recPage.clickButtonLoginAccount();
        var logPage = new LoginPage(driver);
        logPage.fillingInUserEmail(user.getEmail());
        logPage.fillingInUserPassword(user.getPassword());
        logPage.clickButtonLogin();

    }

    @After
    @DisplayName("Clean user")
    @Feature("Удаление пользователя")
    // Прибираем за собой
    public void tearDown(){
        if (user == null) {
            String nameUser = userSteps.loginUser(user)
                    .extract().body().path("name");
            if (nameUser == null) {
                user.setName(nameUser);
                userSteps.deleteUser(user);
            }
        }
    }
}

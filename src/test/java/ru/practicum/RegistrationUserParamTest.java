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
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;
import practicum.steps.UserSteps;

@Epic("Авторизация")
@Feature("Проверка процесса авторизации")
@RunWith(Parameterized.class)
public class RegistrationUserParamTest {

    private String name;
    private String email;
    private String password;
    private UserSteps userSteps = new UserSteps();
    private User user;

    @Rule
    public DriverFactory factory = new DriverFactory();

    public RegistrationUserParamTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Before
    public void setUp() {
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
    }

    @Parameterized.Parameters(name = "Имя: {0}, Емэил {1}, Пароль: {2}")
    public static Object[][] getOrderDetails() {
        return new Object[][]{
                {RandomStringUtils.randomAlphanumeric(7),System.currentTimeMillis() + "@mail.ru", RandomStringUtils.randomAlphanumeric(4) + "56"},
                {RandomStringUtils.randomAlphanumeric(7), System.currentTimeMillis() + "@gmail.ru", RandomStringUtils.randomAlphanumeric(4) + "ra"},
        };
    }

    @Test
    @DisplayName("Login user with correct data")
    @Feature("Авторизация с корректными данными")
    public void testNonFoundAFieldsRegForm() throws InstantiationException {
        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();
        regPage.fillingInUserName(name);
        regPage.fillingInUserEmail(email);
        regPage.fillingInUserPassword(password);
        regPage.clickButtonRegistration();
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

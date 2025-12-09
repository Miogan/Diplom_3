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
import pages.RegistrationPage;
import practicum.steps.UserSteps;

@Epic("Авторизация")
@Feature("Проверка процесса авторизации с некорректными данными")
public class RegistrationUserTest {
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
        user.setPassword("p"  + RandomStringUtils.randomAlphanumeric(4));
        user.setName(RandomStringUtils.randomAlphanumeric(5));
    }
    @Test
    @DisplayName("Registration user with uncorrect pass")
    @Feature("Регистрация с некорректным паролем")
    public void testShortPasswordErrorMessage() throws InstantiationException {

        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();
        regPage.fillingInUserName(user.getName());
        regPage.fillingInUserEmail(user.getEmail());
        regPage.fillingInUserPassword(user.getPassword());
        regPage.clickButtonRegistrationUncorrectPass();
        regPage.checkPasswordError();
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

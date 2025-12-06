package ru.practicum;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

@Epic("Авторизация")
@Feature("Проверка процесса авторизации с некоррекными данными")
public class RegistrationUserTest {
    private String name;
    private String email;
    private String password;

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Login user with uncorrect pass")
    @Feature("Авторизация с некорректным паролем")
    public void testShortPasswordErrorMessage() throws InstantiationException {
        String name = "Иван";
        String email = "test_" + System.currentTimeMillis() + "@example.com";
        String password = RandomStringUtils.randomAlphanumeric(5);

        WebDriver driver = factory.getDriver();
        var regPage = new RegistrationPage(driver);
        regPage.openRegPage();
        regPage.fillingInUserName(name);
        regPage.fillingInUserEmail(email);
        regPage.fillingInUserPassword(password);
        regPage.clickButtonRegistration();
        regPage.checkPasswordError();
    }
}

package ru.practicum;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pages.RegistrationPage;

@Epic("Авторизация")
@Feature("Проверка процесса авторизации")
@RunWith(Parameterized.class)
public class RegistrationUserParamTest {

    private String name;
    private String email;
    private String password;

    @Rule
    public DriverFactory factory = new DriverFactory();

    public RegistrationUserParamTest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Имя: {0}, Емэил {1}, Пароль: {2}")
    public static Object[][] getOrderDetails() {
        return new Object[][]{
                {"Арсений",System.currentTimeMillis() + "@mail.ru", "123456"},
                {"Макар", System.currentTimeMillis() + "@mail.ru", "123456a"},
                {"Макар", System.currentTimeMillis() +"@mail.ru", "abcdefg"},
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

}

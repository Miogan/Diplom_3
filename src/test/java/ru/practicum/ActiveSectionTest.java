package ru.practicum;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.HomePage;

@Epic("Вкладки")
@Feature("Активные вкладки")
public class ActiveSectionTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    @DisplayName("Active section buns")
    @Feature("Активная вкладка булочек")
    public void testActiveSectionBuns() {
        WebDriver driver = factory.getDriver();
        var homePage = new HomePage(driver);
        homePage.openHomePage();
        String initialActiveSection = homePage.getActiveSectionText();
        Assert.assertEquals("Раздел 'Булки' активен",
                "Булки", initialActiveSection);

    }

    @Test
    @DisplayName("Active section sauces")
    @Feature("Активная вкладка соусов")
    public void testActiveSectionSauces() {
        WebDriver driver = factory.getDriver();
        var homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickSaucesSection();
        String initialActiveSection = homePage.getActiveSectionText();
        Assert.assertEquals("Раздел 'Соусы' активен",
                "Соусы", initialActiveSection);
    }

    @Test
    @DisplayName("Active section fillings")
    @Feature("Активная вкладка начинок")
    public void testActiveSectionFillings() {
        WebDriver driver = factory.getDriver();
        var homePage = new HomePage(driver);
        homePage.openHomePage();
        homePage.clickFillingsSection();
        String initialActiveSection = homePage.getActiveSectionText();
        Assert.assertEquals("Раздел 'Начинки' активен",
                "Начинки", initialActiveSection);

    }
}

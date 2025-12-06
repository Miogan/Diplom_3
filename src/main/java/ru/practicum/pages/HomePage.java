package ru.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import io.qameta.allure.Step;
import static ru.practicum.pages.LoginPage.LOGIN_PAGE_ADDRESS;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public static final String HOME_PAGE_ADDRESS = "https://stellarburgers.education-services.ru/";
    public static final By LOCATOR_LINK_PERSONAL_ACCOUNT =  By.cssSelector("a.AppHeader_header__link__3D_hX[href='/account']");
    public static final By LOCATOR_BUTTON_LOGIN_TO_ACCOUNT = By.cssSelector("button.button_button__33qZ0.button_button_type_primary__1O7Bx.button_button_size_large__G21Vg");

    // Локаторы для разделов
    private static final By LOCATOR_BUNS_SECTION = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Булки']/parent::div");
    private static final By LOCATOR_SAUCES_SECTION = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Соусы']/parent::div");
    private static final By LOCATOR_FILLINGS_SECTION = By.xpath("//div[contains(@class, 'tab_tab__1SPyG')]//span[text()='Начинки']/parent::div");

    // Локатор для активного раздела
    private static final By LOCATOR_ACTIVE_SECTION = By.cssSelector(".tab_tab__1SPyG.tab_tab_type_current__2BEPc");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Открытие стартовой страницы")
    public void openHomePage() {
        driver.get(HOME_PAGE_ADDRESS);
    }

    @Step("Клик по ссылке Личный кабинет")
    public void clickLinkPersonalAccount() {
        driver.findElement(LOCATOR_LINK_PERSONAL_ACCOUNT).click();
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_ADDRESS));

    }

    @Step("Клик по кнопке Войти в аккаунт")
    public void clickButtonLoginToAccount() {
        driver.findElement(LOCATOR_BUTTON_LOGIN_TO_ACCOUNT).click();
        wait.until(ExpectedConditions.urlToBe(LOGIN_PAGE_ADDRESS));
    }

    @Step("Клик по вкладке Соусы")
    public void clickSaucesSection() {
        driver.findElement(LOCATOR_SAUCES_SECTION).click();
    }

    @Step("Клик по вкладке Начинки")
    public void clickFillingsSection() {
        driver.findElement(LOCATOR_FILLINGS_SECTION).click();
    }

    @Step("Находим имя активного раздела")
    public String getActiveSectionText() {
        try {
            WebElement activeSection = driver.findElement(LOCATOR_ACTIVE_SECTION);
            return activeSection.findElement(By.tagName("span")).getText();
        } catch (Exception e) {
            return "Активный раздел не найден";
        }
    }
}

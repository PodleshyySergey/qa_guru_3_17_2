package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.Map;

public class CookieManager {

    public static void addCookiesToSite(Map<String, String> cookies) {
        cookies.entrySet()
                .stream()
                .forEach(cookie -> WebDriverRunner.getWebDriver()
                        .manage().addCookie(new Cookie(cookie.getKey(), cookie.getValue())));
    }
}
/*
open = открывем любую страницу сайта
Cookie authCookie = new Cookie("login", "password"); Создаем обьект класса и передаем результаты вызова API
WebDriverRunner.getWebDriver().manage().addCookie(authCookie); передаем драйверу куки
open = открываем сайт уже авторизованными и делаем там свои дейсвия;
 */
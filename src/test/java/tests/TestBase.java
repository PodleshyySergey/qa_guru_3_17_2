package tests;

import helpers.CookieManager;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static helpers.AuthApi.cookies;

public class TestBase {

    public String getNumberProduct() {
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        CookieManager.addCookiesToSite(cookies());
        open("http://demowebshop.tricentis.com/wishlist");
        return $(byName("removefromcart")).getValue();
    }

}

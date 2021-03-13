package tests;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.AuthApi.cookies;
import static tests.AuthApi.mapToString;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishListTests {
    String cookieText = mapToString(cookies());

    @Test
    @Order(1)
    public void addToWishListTest() {
        String bodyText = "product_attribute_28_7_10=25&product_attribute_28_1_11=29&addtocart_28.EnteredQuantity=1";

        AddWishListResponse response = given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .cookie(cookieText)
                .body(bodyText)
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/28/2")
                .then()
                .statusCode(200)
                .extract().as(AddWishListResponse.class);

        assertEquals(response.getUpdatetopwishlistsectionhtml(), "(1)");
    }

    @Test
    @Order(2)
    public void removeFromWishListTest() {
        String prodVal = getNumberProduct();
        String bodyText = "removefromcart=" + prodVal + "&itemquantity" + prodVal + "=1&updatecart=Update+wishlist";

        given()
                .cookie(cookieText)
                .contentType("application/x-www-form-urlencoded")
                .body(bodyText)
                .when()
                .post("http://demowebshop.tricentis.com/wishlist")
                .then()
                .statusCode(200);
    }

    public String getNumberProduct() {
        open("http://demowebshop.tricentis.com/Themes/DefaultClean/Content/images/logo.png");
        CookieManager.addCookiesToSite(cookies());
        open("http://demowebshop.tricentis.com/wishlist");
        return $(byName("removefromcart")).getValue();
    }

}

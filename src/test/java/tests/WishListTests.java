package tests;

import models.AddWishListResponse;
import org.junit.jupiter.api.*;
import static helpers.AuthApi.cookies;
import static helpers.AuthApi.mapToString;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WishListTests extends TestBase {
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
        String prodVal = getNumberProduct(); //Получение номера продукта из WishList для передачи в запросе на его удаление
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

}

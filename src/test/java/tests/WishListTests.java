package tests;

import models.AddWishListResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WishListTests {

    @Test
    public void addToWishListTest() {
        String cookieText = "__utmz=78382081.1614029933.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ARRAffinity=1fa9158750fcf7cee1728ac683a12594fe016bf3b1c0544237f51a4ffe2ef5ea; __utma=78382081.933151655.1614029933.1614029933.1614430339.2; __utmc=78382081; __utmt=1; NOPCOMMERCE.AUTH=7BD21B30130617C6142AA8D226438E5C09A4B52AB3C25934EB24764264540F03445466A61DDE1ED2C0C750549E6740B0E02665B86490B994B37341DC6D1E3EDFF006028000509DFD655BF2827674BD45F27800F2719F2BC842D0716C1EEF7DC2BA609AE2A3E8BDEC045EA9196F5227A44937D6A0E4B1C9ED6CE525B92CC0457F1150CCF437FEB309BDE1148C6952AC40; Nop.customer=597c73c1-efc5-4e96-8946-ef30500cadfa; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=28&RecentlyViewedProductIds=24&RecentlyViewedProductIds=13; __atuvc=3%7C8; __atuvs=603a4157602ba31e001; __utmb=78382081.13.10.1614430339";
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

//        String s = response.getUpdatetopwishlistsectionhtml();

        assertEquals(response.getUpdatetopwishlistsectionhtml(), "(1)");

    }


}

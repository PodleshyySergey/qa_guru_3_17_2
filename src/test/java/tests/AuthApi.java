package tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class AuthApi{
    private final static String EMAIL = "serpodl@test.ru";
    private final static String PASSWORD = "test1234";


    @Step("Get new session Cookie")
    public static Map<String, String> cookies() {

        return given()
                .filter(new AllureRestAssured())  //.filter(filters().withCustomTemplates()) Для логов
                //.accept(ContentType.ANY) //Принимаем любой контент
                .contentType("application/x-www-form-urlencoded")

                .body(getData())
        .when()
                .post("/login")
        .then()
                .log().all()
                .statusCode(302)
                .extract().cookies();
    }

    private static String getData() {
        return "email=" + EMAIL + "&password=" + PASSWORD + "&RememberMe=false"; //потому что = application/x-www-form-urlencoded
    }
}



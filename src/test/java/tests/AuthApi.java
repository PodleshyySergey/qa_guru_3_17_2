package tests;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
//                .contentType("application/x-www-form-urlencoded")
                .contentType("application/x-www-form-urlencoded")

                .body("Email=SerPodl%40test.ru&Password=test1234&RememberMe=false")
        .when()
                .post("http://demowebshop.tricentis.com/login")
        .then()
                .log().all()
                .statusCode(302)
                .extract().cookies();
    }

    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            if (stringBuilder.length() > 0) {
//                stringBuilder.append("&");
                stringBuilder.append("; ");
            }
            String value = map.get(key);
            try {
                stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                stringBuilder.append("=");
                stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return stringBuilder.toString();
    }

//    private static String getData() {
//        return "email=" + EMAIL + "&password=" + PASSWORD + "&RememberMe=false"; //потому что = application/x-www-form-urlencoded
//    }
}



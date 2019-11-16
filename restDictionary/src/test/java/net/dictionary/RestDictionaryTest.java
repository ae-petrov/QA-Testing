package net.dictionary;

import io.restassured.RestAssured;
import net.dictionary.api.client.EndpointURL;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class RestDictionaryTest {
    private static final String URL_KEY = "https://translate.yandex.net/api/v1.5/tr.json";
    private static final String API_KEY = "trnsl.1.1.20191116T112140Z.cb67707096a543ae.96cdd481fb479b909d60a75de0f23dedfcf2c46c";
    //https://translate.yandex.net/api/v1.5/tr.json/translate?key=API_KEY&lang=&text
    @Test
    public void test () {
        RestAssured.baseURI = URL_KEY;

        given()
                .header("User-Agent", "Chrome")
                .when()
                    .get(EndpointURL.TRANSLATE.addPath(String.format("?key=%s&lang=%s&text=%s", API_KEY, "ru-en", "Привет, мир!")))
                    .then()
                    .statusCode(HTTP_OK)
                    .body("text", hasItem("Hello world!"))
                    .body("lang", equalTo("ru-en"))
                    .body("code", equalTo(200));

    }


}

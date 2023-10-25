package utils;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;

public class Autenticacao {
  static String baseURL = "https://bookstore.toolsqa.com/Account/v1/Authorized";

  @Test
    public static String token(){
    String token =
              given()
                      .contentType(ContentType.JSON)
                      .body("""
                              {
                              "userName": "FelipeLSg",
                              "password": "Felipe0987@"
                              }
                              """)
                      .when()
                      .post(baseURL)
                      .then()
                      .extract()
                      .asString();
              return token;
  }
}

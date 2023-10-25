package base;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {
    @BeforeEach
    public void setUp(){
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}

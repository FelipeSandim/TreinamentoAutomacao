package specs;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import utils.Autenticacao;

public class SetupsRequestSpecification {
    public static RequestSpecification requestSpecification(){
        return new RequestSpecBuilder()
                .addHeader("Authorization", Autenticacao.token())
                .setContentType(ContentType.JSON)
                .build()
                ;
    }

    public static RequestSpecification requestDefault(){
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build()
                ;
    }


    public static RequestSpecification getRequestSpecification() {
        PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
        authScheme.setUserName("FelipeLSg");
        authScheme.setPassword("Felipe0987@");

        return RestAssured.given()
                .auth().preemptive().basic(authScheme.getUserName(), authScheme.getPassword())
                .contentType(ContentType.JSON);
    }
}

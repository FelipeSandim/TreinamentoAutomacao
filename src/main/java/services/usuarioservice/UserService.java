package services.usuarioservice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class UserService {

    public Response autenticacaoUsuario(String nome, String password){
        Response response =
            given()
                .contentType(ContentType.JSON)
                .body("""
                    {
                      "userName": "${nome}",
                      "password": "${password}"
                    }
                    """.replace("${nome}", nome).replace("${password}", password))
            .when()
                .post("https://bookstore.toolsqa.com/Account/V1/Authorized")
                ;
        return response;
    }
    public Response criarUsuario(String nome, String password){
        Response response =
            given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "userName": "${nome}",
                  "password": "${password}"
                }
                """.replace("${nome}", nome).replace("${password}", password))
            .when()
                .post("/Account/V1/Authorized")
                ;
        return response;
    }

    public Response deletarUsuario(String userID) {
        Response response =
             given()
                .spec(SetupsRequestSpecification.getRequestSpecification())
                .pathParam("UUID", userID)
            .when()
                .delete("/Account/v1/User/{UUID}")
        ;
        return response;
    }

    public Response consultarLivrosDoUsuario(String userID, String user, String password) {
        Response response =
            given()
                .spec(SetupsRequestSpecification.getRequestSpecification())
                .contentType(ContentType.JSON)
                .auth().preemptive().basic(user, password)
                .pathParam("UUID", userID)
            .when()
                .get("/Account/v1/User/{UUID}")
                ;
        return response;
    }
    public Response gerarToken(String nome, String password){
        Response response =
            given()
                    .body("""
            {
              "userName": "${nome}",
              "password": "${password}"
            }
            """.replace("${nome}", nome).replace("${password}", password))
                    .contentType(ContentType.JSON)
            .when()
                    .post("/Account/v1/GenerateToken")
            ;
        return response;
    }
}

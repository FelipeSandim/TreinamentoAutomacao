package services.bookstoreservice;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import specs.SetupsRequestSpecification;

import static io.restassured.RestAssured.given;

public class BookService {
    public Response excluirUmLivro( String userId, String isbn) {
        Response response =
            given()
                    .spec(SetupsRequestSpecification.getRequestSpecification())
                .body("""                                                           
                        {
                        "isbn": "${isbn}"                            
                        "userId": "${userId}"      
                        }                    
                        """.replace("${isbn}", isbn).replace("${userId}", userId))
            .when()
                .delete("/BookStore/v1/Books")
                ;
        return response;
    }

    public Response excluirTodosOsLivros(String userId) {
        Response response =
            given()
                    .spec(SetupsRequestSpecification.getRequestSpecification())
                .queryParam("UserId", userId)
            .when()
                .delete("/BookStore/v1/Books")
         ;
        return response;
    }


    public Response pesquisaDeUmLivroLivro(String isbn){
        Response response =
            given()
                    .spec(SetupsRequestSpecification.getRequestSpecification())
                .queryParam("ISBN",isbn)
            .when()
                .get("/BookStore/v1/Book")
                ;
        return response;
    }

    public Response buscarTodosOsLivros() {
        Response response =
            given()
                    .spec(SetupsRequestSpecification.getRequestSpecification())
            .when()
                .get("/BookStore/v1/Books");
        return response;
    }

    public Response cadastroLivro(String userID, String isbn){
        Response response =
            given()
                    .spec(SetupsRequestSpecification.getRequestSpecification())
                .body("""
                    {
                    "userId": "${userID}",
                    "collectionOfIsbns": [
                    {
                    "isbn": "${isbn}"                          
                    } ] 
                    }
                    """.replace("${userID}", userID).replace("${isbn}", isbn))
            .when()
                .post("/BookStore/v1/Books")
                ;
        return response;
    }

    public Response atualizarLivro(String userID, String newIsbn, String oldIsbn){
        var response=
            given()
                .spec(SetupsRequestSpecification.getRequestSpecification())
                .body("""
                     {
                       "userId": "${userID}",
                       "isbn": "${isbn}" 
                     }
                     """.replace( "${userID}",userID).replace("${isbn}", newIsbn))
                .pathParam("ISBN",oldIsbn)
            .when()
                .put("/BookStore/v1/Books/{ISBN}")
                ;
        return response;
    }


}

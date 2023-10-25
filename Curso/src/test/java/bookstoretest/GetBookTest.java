package bookstoretest;

import base.BaseTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import services.bookstoreservice.BookService;
import org.junit.jupiter.api.DisplayName;
import static org.hamcrest.Matchers.equalTo;

public class GetBookTest extends BaseTest {

    BookService bookService = new BookService();

    @Test
    @DisplayName("Teste: nesse método é possivel pesquisar um único livro na biblioteca")
    public void testPesquisaDeUmLivroLivroComSucesso(){
        bookService.pesquisaDeUmLivroLivro("9781449331818")
           .then()
                .statusCode(200)
                .body("isbn", equalTo( "9781449331818"))
                ;
    }
    @Test
    @DisplayName("Teste: tentar pesquisar um livro com isbn incorreto.")
    public void testPesquisaDeUmLivroComIsbnIncorreto(){
        bookService.pesquisaDeUmLivroLivro("9781449sdadasd")
            .then()
                .statusCode(400)
                .body("message", equalTo( "ISBN supplied is not available in Books Collection!"))
                .body("code", equalTo( "1205"))
        ;
    }

    @Test
    @DisplayName("Teste: tentar pesquisar um livro sem informar o numero do ISBN.")
    public void testPesquisaDeUmLivroComCampoIsbnVazio(){
        bookService.pesquisaDeUmLivroLivro("")
            .then()
                .statusCode(400)
                .body("message", equalTo( "ISBN supplied is not available in Books Collection!"))
                .body("code", equalTo( "1205"))
        ;
    }

    @Test
    @DisplayName("Teste: busca todos os livros da biblioteca.")
    public void testBuscarTodosOsLivros() {
        bookService.buscarTodosOsLivros()
            .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("books[0].isbn", equalTo( "9781449325862"))
                ;
    }

    @ParameterizedTest
    @MethodSource("dataFactory.BookDataFactory#isbnParameter")
    @DisplayName("Teste: nesse método é possivel consultar varios livros um de cada vez.")
    void testBuscarLivroso(String isbn){
        bookService.pesquisaDeUmLivroLivro(isbn)
            .then()
                .statusCode(200)
                .body("isbn", equalTo( isbn))
        ;
    }


}

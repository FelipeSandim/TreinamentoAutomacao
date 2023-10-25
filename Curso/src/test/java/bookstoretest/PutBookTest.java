package bookstoretest;

import base.BaseTest;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.bookstoreservice.BookService;

import static io.restassured.RestAssured.given;

public class PutBookTest extends BaseTest {

    BookService bookService = new BookService();

    @Test
    @DisplayName("Teste: Modificar um livro no acervo usando o ISBN do livro.")
    public void testAtualizarLivroComSucesso(){
        bookService.atualizarLivro( "33fdc164-2b47-49a4-a1de-09bb586b67c7", "9781449365035", "9781449325862")
            .then()
                .statusCode(200)
                .body("books[0].isbn", Matchers.equalTo("9781449365035"))
            ;
    }

    @Test
    @DisplayName("Teste: Erro Modificar um livro no acervo usando o ISBN do livro repetido.")
    public void testAtualizarLivroRepetido(){
        bookService.atualizarLivro( "33fdc164-2b47-49a4-a1de-09bb586b67c7", "9781449365035", "9781449365035")
            .then()
                .statusCode(400)
                .body("message", Matchers.equalTo("ISBN supplied is not available in User's Collection!"))
                ;
    }

    @Test
    @DisplayName("Teste: Erro Modificar um livro no acervo sem informar o ISBN do livro a ser atualizado.")
    public void testAtualizarLivroSemISBNNovo(){
        bookService.atualizarLivro( "33fdc164-2b47-49a4-a1de-09bb586b67c7", "", "9781449365035")
            .then()
                .statusCode(400)
                .body("message", Matchers.equalTo("Request Body is Invalid!"))
                ;
    }
}

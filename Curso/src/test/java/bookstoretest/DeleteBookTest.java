package bookstoretest;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.bookstoreservice.BookService;

import org.junit.jupiter.api.DisplayName;
public class DeleteBookTest extends BaseTest {

    BookService bookService = new BookService();

    @Test
    @DisplayName("Teste: exclui um único livro do acervo de um usuário.")
    public void testExcluirUmLivroComSucesso() {
        var response = bookService.excluirUmLivro("33fdc164-2b47-49a4-a1de-09bb586b67c7","9781449325862" )
            .then()
                .statusCode(204)
                .extract()
                .response();
        Assertions.assertEquals(204, response.getStatusCode());
    }

    @Test
    @DisplayName("Teste: Tenta excluir um único livro do acervo informando o ID incorreto.")
    public void testExcluirUmLivroSemOIDDoUsuario() {
        var response = bookService.excluirUmLivro("dasdhbashbj","9781449325862" )
            .then()
                .statusCode(400)
                .extract()
                .response();
        Assertions.assertEquals(400, response.getStatusCode())
        ;
    }

    @Test
    @DisplayName("Teste: Tenta excluir um único livro do acervo sem informar o ISBN.")
    public void testExcluirUmLivroSemInformarOIsbn() {
        var response = bookService.excluirUmLivro("33fdc164-2b47-49a4-a1de-09bb586b67c7","" )
            .then()
                .statusCode(400)
                .extract()
                .response();
        Assertions.assertEquals(400, response.getStatusCode())
        ;
    }

    @Test
    @DisplayName("Teste: Excluir todos os livros do acervo de um usuário.")
    public void testExcluirTodosLivrosComSucesso() {
        var response = bookService.excluirTodosOsLivros("33fdc164-2b47-49a4-a1de-09bb586b67c7")
            .then()
                .statusCode(401)
                .body("code", Matchers.equalTo("1207"))
                .body("message", Matchers.equalTo("User Id not correct!"))
                ;
    }

    @Test
    @DisplayName("Teste: Tenta excluir todos os livros do acervo de um usuário informando o ID incorreto.")
    public void testExcluirTodosLivrosComIDIncorreto() {
        var response = bookService.excluirTodosOsLivros("12377261372dsbfhbs.....")
            .then()
                .statusCode(401)
                .body("code", Matchers.equalTo("1207"))
                .body("message", Matchers.equalTo("User Id not correct!"))
                ;
    }

    @Test
    @DisplayName("Teste: Tenta excluir todos os livros do acervo de um usuário sem informar o ID.")
    public void testExcluirTodosLivrosSemID() {
        var response = bookService.excluirTodosOsLivros("")
            .then()
                .statusCode(401)
                .body("code", Matchers.equalTo("1207"))
                .body("message", Matchers.equalTo("User Id not correct!"))
                ;
    }
}

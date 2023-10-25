package bookstoretest;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.junit.Test;
import services.bookstoreservice.BookService;
import org.junit.jupiter.api.DisplayName;

public class PostBookTest extends BaseTest {

    BookService bookService = new BookService();
    @Test
    @DisplayName("Teste: nesse método cadastra um livro no acervo de um usuário.")
    public void testCadastroLivro(){
        bookService.cadastroLivro("33fdc164-2b47-49a4-a1de-09bb586b67c7", "9781449365035")
            .then()
                .statusCode(201)
                .body("books[0].isbn", Matchers.equalTo("9781449325862"))
            ;
    }
    @Test
    @DisplayName("Teste: Tentar cadastrar um livro no acervo de um usuário sem o ISBN.")
    public void testCadastroLivroSemIsbn(){
        bookService.cadastroLivro("33fdc164-2b47-49a4-a1de-09bb586b67c7", "")
            .then()
                .statusCode(400)
                .body("message", Matchers.equalTo("ISBN supplied is not available in Books Collection!"))
            ;
    }
    @Test
    @DisplayName("Teste: nesse método cadastra um livro no acervo de um usuário.")
    public void testCadastroLivroComIDDeUsuarioIncorreto(){
        bookService.cadastroLivro("33fdc164c7", "9781449325862")
            .then()
                .statusCode(401)
                .body("message", Matchers.equalTo("User Id not correct!"))
            ;
    }

}

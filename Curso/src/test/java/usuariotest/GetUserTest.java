package usuariotest;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import services.usuarioservice.UserService;

import static io.restassured.RestAssured.given;

public class GetUserTest extends BaseTest {

    UserService userService = new UserService();

    @Test
    @DisplayName("Teste: Consultar ol livros na biblioteca do usuário")
    public void testConsultarLivrosDoUsuario() {
        var response = userService.consultarLivrosDoUsuario("33fdc164-2b47-49a4-a1de-09bb586b67c7", "FelipeLSg", "Felipe0987@" )
            .then()
                .statusCode(200)
                .body("books[0].isbn", Matchers.equalTo("9781449365035"));
    }

    @Test
    @DisplayName("Teste: Consultar livros de um usuário com nome e senha corretos mas o id errado.")
    public void testConsultarLivrosComIDErrado() {
        var response = userService.consultarLivrosDoUsuario("33fdc164-2b47-49a4", "FelipeLSg", "Felipe0987@" )
            .then()
                .statusCode(401)
                .body("code", Matchers.equalTo("1207"))
                .body("message", Matchers.equalTo("User not found!"))
                ;
    }

    @Test
    @DisplayName("Teste: Consultar ol livros na biblioteca do usuário com ID e senha corretos mas nome do usuário incorreto")
    public void testConsultarLivrosComUsuárioInvalido() {
        var response = userService.consultarLivrosDoUsuario("33fdc164-2b47-49a4-a1de-09bb586b67c7", ".a.a.a.a", "Felipe0987@" )
                .then()
                .statusCode(401)
                .body("code", Matchers.equalTo("1200"))
                .body("message", Matchers.equalTo("User not authorized!"))
                ;
    }

    @Test
    @DisplayName("Teste: Consultar ol livros na biblioteca do usuário com ID e nome cporretos mas com senha inválida.")
    public void testConsultarLivrosDoUsuarioComSenhaInvalida() {
        var response = userService.consultarLivrosDoUsuario("33fdc164-2b47-49a4-a1de-09bb586b67c7", "FelipeLSg", ";a;a;a;a" )
                .then()
                .statusCode(401)
                .body("code", Matchers.equalTo("1200"))
                .body("message", Matchers.equalTo("User not authorized!"))
                ;
    }
}

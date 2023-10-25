package usuariotest;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import services.usuarioservice.UserService;

import static io.restassured.RestAssured.given;

public class AcountAutorizedTest extends BaseTest {

    UserService userService = new UserService();

    @Test
    @DisplayName("Teste: Nesse método é possível validar se é um usuário cadastrado.")
    public void testAutenticacaoUsuarioValidoComSucesso(){
        userService.autenticacaoUsuario("FelipeLSg", "Felipe0987@")
        .then()
            .statusCode(200)
            .body(Matchers.equalTo("true"))
        ;
    }

    @Test
    @DisplayName("Teste: Nesse método esta tentando validar um usuário inválido.")
    public void testAutenticacaoUsuarioInvalido(){
        userService.autenticacaoUsuario("Fe", "Felipe0987@")
                .then()
                .statusCode(404)
                .body("message", Matchers.equalTo("User not found!"))
                .body("code", Matchers.equalTo("1207"))
        ;
    }

    @Test
    @DisplayName("Teste: Nesse método esta tentando validar um usuario sem senha.")
    public void testAutenticacaoUsuarioSemSenha(){
        userService.autenticacaoUsuario("FelipeLSg", "")
                .then()
                .statusCode(400)
                .body("message", Matchers.equalTo("UserName and Password required."))
                .body("code", Matchers.equalTo("1200"))
        ;
    }
}

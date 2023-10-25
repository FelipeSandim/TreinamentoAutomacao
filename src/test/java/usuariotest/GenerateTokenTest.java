package usuariotest;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import services.usuarioservice.UserService;

public class GenerateTokenTest extends BaseTest {

    UserService userService = new UserService();
    @Test
    @DisplayName("Teste: Nesse método é possivel gerar o Token do usuário.")
    public void testGerarToken(){
        userService.gerarToken("FelipeLSg", "Felipe0987@")
            .then()
                .statusCode(200)
                .body("status", Matchers.equalTo("Success"))
                .body("result", Matchers.equalTo("User authorized successfully."))
        ;
    }

    @Test
    @DisplayName("Teste: Nesse método tenta gerar um token com um usuário inexistente")
    public void testGerarTokenComNomeErrado(){
        userService.gerarToken("nenhum", "Felipe0987@")
            .then()
                .statusCode(200)
                .body("token", Matchers.equalTo(null))
                .body("expires", Matchers.equalTo(null))
                .body("status", Matchers.equalTo("Failed"))
                .body("result", Matchers.equalTo("User authorization failed."))
        ;
    }

    @Test
    @DisplayName("Teste: Nesse método tenta gerar um tokem com usuário correto e senha incorreta.")
    public void testGerarTokenComSenhaErrada(){
        userService.gerarToken("FelipeLSg", ".a.a.a.a.a")
            .then()
                .statusCode(200)
                .statusCode(200)
                .body("token", Matchers.equalTo(null))
                .body("expires", Matchers.equalTo(null))
                .body("status", Matchers.equalTo("Failed"))
                .body("result", Matchers.equalTo("User authorization failed."))
        ;
    }

    @Test
    @DisplayName("Teste: Nesse método tenta gerar um token sem nome de usuário.")
    public void testGerarTokenSemNome(){
        userService.gerarToken("", "Felipe0987@")
            .then()
                .statusCode(400)
                .body("code", Matchers.equalTo("1200"))
                .body("message", Matchers.equalTo("UserName and Password required."))
        ;
    }
    @Test
    @DisplayName("Teste: Nesse método tenta gerar um token sem senha.")
    public void testGerarTokenSemSenha(){
        userService.gerarToken("FelipeLSg", "")
            .then()
                .statusCode(400)
                .body("code", Matchers.equalTo("1200"))
                .body("message", Matchers.equalTo("UserName and Password required."))
        ;
    }
}

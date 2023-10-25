package usuariotest;

import base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import services.usuarioservice.UserService;

import static io.restassured.RestAssured.given;

public class AcounterUserTest extends BaseTest {

    UserService userService = new UserService();

    @Test
    @DisplayName("Teste: Nesse método é possivel criar um usuário.")
    public void testCriarUsuarioComSucesso(){
        userService.criarUsuario("FElipeD", "Dc12345@")
            .then()
                .statusCode(201)
                .body("username", Matchers.equalTo("FElipeD"))
            ;
    }

    @Test
    @DisplayName("Teste: Nesse método tenta criar um usuário sem nome.")
    public void testCriarUsuarioSemNome(){
        userService.criarUsuario("", "Dc12345@")
                .then()
                .statusCode(400)
                .body( "message", Matchers.equalTo("UserName and Password required."))
                .body( "code", Matchers.equalTo( "1200"))
        ;
    }

    @Test
    @DisplayName("Teste: Nesse método tenta criar um usuário sem senha.")
    public void testCriarUsuarioSemSenha(){
        userService.criarUsuario("FelipeD", "")
                .then()
                .statusCode(400)
                .body( "message", Matchers.equalTo("UserName and Password required."))
                .body( "code", Matchers.equalTo( "1200"))
        ;
    }
}

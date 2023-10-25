package usuariotest;

import base.BaseTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import services.usuarioservice.UserService;

public class DeleteUserTest extends BaseTest {

    UserService userService = new UserService();

    @Test
    @DisplayName("Teste: nesse método exclui o usuário com sucesso.")
    public void testDeletarUsuarioComSucesso() {
        userService.deletarUsuario("c4490911-7ad3-4596-acf9-7cdee9d58e50")
            .then()
                .statusCode(204)
            ;
    }
    @Test
    @DisplayName("Teste: nesse método tenta excluir usuario inexistente.")
    public void testDeletarUsuarioInexistente() {
        userService.deletarUsuario("uidhahsduasd.................")
                .then()
                .statusCode(200)
                .body( "message", Matchers.equalTo("User Id not correct!"))
                .body( "code", Matchers.equalTo( "1207"))
        ;
    }
    @Test
    @DisplayName("Teste: nesse método tenta excluir usuario com campo de ID vazio.")
    public void testDeletarUsuarioCampoIDVazio() {
        var response = userService.deletarUsuario("")
            .then()
                .statusCode(302)
                .extract()
                .statusCode();
            Assertions.assertEquals(302, response)
        ;
    }
}

package api.lojinha.testes;

import api.lojinha.core.BaseTest;
import api.lojinha.dataFactory.UsuarioDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Testes do dominio de Login")
public class LoginTest extends BaseTest {

    private static final String ENDPOINT_LOGIN = "/v2/login";

    @Test
    @DisplayName("Quando usuario administrador cadastrado e credencial correta então login com sucesso")
    public void testValidarLoginUsuarioAdministradorComSucesso(){
        given()
                .body(UsuarioDataFactory.loginUsuarioAdministrador())
        .when()
                .post(ENDPOINT_LOGIN)
        .then()
                .statusCode(200)
                .body("message",is("Sucesso ao realizar o login"));
    }

    @Test
    @DisplayName("Quando usuario sem credencial cadastrada então login não autorizado")
    public void testValidarTentativaDeLoginUsuarioSemCredencial(){
        given()
                .body(UsuarioDataFactory.loginUsuarioSemCadastro())
        .when()
                .post(ENDPOINT_LOGIN)
        .then()
                .statusCode(401);
    }

    @Test
    @DisplayName("Quando usuario com credencial valida entao login realizado com sucesso")
    public void testValidarLoginUsuarioComSucesso() throws IOException {
        given()
                .body(UsuarioDataFactory.loginUsuario())
        .when()
                .post(ENDPOINT_LOGIN)
        .then()
                .statusCode(200)
                .body("message",is("Sucesso ao realizar o login"));
    }
}

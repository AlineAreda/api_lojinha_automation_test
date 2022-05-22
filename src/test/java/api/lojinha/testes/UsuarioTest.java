package api.lojinha.testes;

import api.lojinha.core.BaseTest;
import api.lojinha.dataFactory.UsuarioDataFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Testes do dominio do usuario")
public class UsuarioTest extends BaseTest {

    private static final String ENDPOINT_USUARIOS= "/v2/usuarios";

    @Test
    @DisplayName("Quando dados do usuario validos, então usuario cadastrado com sucesso")
    public void testCadastroNovoUsuarioComSucesso() throws IOException {
        given()
                .body(UsuarioDataFactory.adicionaNovoUsuario())
        .when()
                .post(ENDPOINT_USUARIOS)
        .then()
                .statusCode(201)
                .log().all()
                .body("message", is("Usuário adicionado com sucesso"))
        ;
    }

    @Test
    @DisplayName("Quando usuario já cadastrado não realizar novo cadastro")
    public void testUsuarioJaCadastado() throws IOException {
        given()
                .body(UsuarioDataFactory.adicionaUsuario())
        .when()
                .post(ENDPOINT_USUARIOS)
        .then()
                .statusCode(409)
                .log().all()
                .body("error", is("O usuário alineareda já existe."))
        ;
    }
}

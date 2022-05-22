package api.lojinha.testes;

import api.lojinha.core.BaseTest;
import api.lojinha.dataFactory.ProdutoDataFactory;
import api.lojinha.utilsTest.UtilsLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static api.lojinha.utilsTest.UtilsProduto.getProdutoId;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Testes do dominio de produto")
public class ProdutoTest extends BaseTest {

    private static final String ENDPOINT_PRODUTOS= "/v2/produtos";

    @Test
    @DisplayName("Quando credencial e dados para cadastro do produto validos, entao produto cadastrado com sucesso")
    public void testCadastraNovoProdutoComSucesso() throws IOException {
        given()
                .header("token", UtilsLogin.loginAdmin())
                .body(ProdutoDataFactory.cadastraNovoProduto())
        .when()
                .post(ENDPOINT_PRODUTOS)
        .then()
                .statusCode(201)
                .body("message", is("Produto adicionado com sucesso"))
                .body("data.produtoNome", is("Teclado sem fio K390"))
                .body("data.produtoValor", is(549))
                .body("data.produtoCores[0]", is("preto"))
                .body("data.produtoCores[1]", is("amarelo"))
                .body("data.componentes[0].componenteNome", is("Mouse sem fio M535"))
                .body("data.componentes[0].componenteQuantidade", is(1));
    }


    @Test
    @DisplayName("Quando valor do produto invalido R$0.00, entao produto nao cadastrado")
    public void testCadastraNovoProdutoValorAbaixoDoRangePermetido() throws IOException {
        given()
                .header("token", UtilsLogin.loginAdmin())
                .body(ProdutoDataFactory.cadastraProdutoValorInvalido(0.00))
        .when()
                .post(ENDPOINT_PRODUTOS)
        .then()
                .statusCode(422)
                .body("error", is("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"));
    }

    @Test
    @DisplayName("Quando valor do produto invalido R$7000.01, entao produto nao cadastrado")
    public void testCadastraNovoProdutoValorAcimaDoRangePermetido() throws IOException {
        given()
                .header("token", UtilsLogin.loginAdmin())
                .body(ProdutoDataFactory.cadastraProdutoValorInvalido(7000.01))
        .when()
                .post(ENDPOINT_PRODUTOS)
        .then()
                .statusCode(422)
                .body("error", is("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"));
    }

    @Test
    @DisplayName("Quando Id do produto cadastrado valido entao retorna detalhamento dos dados do produto")
    public void testGetProdutoPorIdComSucesso() throws IOException {
        int produtoId = getProdutoId();

        given()
                .header("token", UtilsLogin.loginAdmin())
        .when()
                .get(ENDPOINT_PRODUTOS + "/"+ produtoId)
        .then()
                .statusCode(200)
                .body("message", is("Detalhando dados do produto"))
                .body("data.produtoId", is(produtoId))
                .body("data.produtoNome", is("TECLADO LOGITECH K480"))
                .body("data.produtoValor", is(399))
                .body("data.produtoCores[0]", is("preto"))
                .body("data.componentes[0].componenteNome", is("MOUSE LOGITECH M535"))
                .body("data.componentes[0].componenteQuantidade", is(1));
    }

}


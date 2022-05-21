package api.lojinha.utilsTest;

import api.lojinha.dataFactory.ProdutoDataFactory;
import java.io.IOException;
import static io.restassured.RestAssured.given;

public class UtilsProduto {

    public static int getProdutoId() throws IOException {

        int produtoId=  given()
                .header("token", UtilsLogin.loginAdmin())
                .body(ProdutoDataFactory.cadastraProdutoPadrao())
        .when()
                .post("/v2/produtos")
        .then()
                .extract()
                .path("data.produtoId");

        return produtoId;
    }
}

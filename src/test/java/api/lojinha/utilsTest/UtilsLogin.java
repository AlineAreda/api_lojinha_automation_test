package api.lojinha.utilsTest;

import api.lojinha.dataFactory.UsuarioDataFactory;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class UtilsLogin {

    public static String loginAdmin() {
        String token = given()
                .contentType(ContentType.JSON)
                .body(UsuarioDataFactory.LoginUsuarioAdministrador())
        .when()
                .post("/v2/login")
        .then()
            .extract()
                .path("data.token");

        return token;
    }

}

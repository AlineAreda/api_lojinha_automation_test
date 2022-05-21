package api.lojinha.core;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.aeonbits.owner.ConfigFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    @BeforeAll
    public static void setup(){

        ServerConfig config = ConfigFactory.create(ServerConfig.class);
        RestAssured.baseURI = config.BASE_URL();
        RestAssured.basePath = config.BASE_PATH();

        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(ContentType.JSON);
        RestAssured.requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(config.MAX_TIMEOUT()));

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }
}

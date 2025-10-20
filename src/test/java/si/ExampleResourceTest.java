package si;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ExampleResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(404);
    }
    @Test
    void testHighestStandardVatRate() {
        given()
                .when().get("/highest-standard-vat")
                .then()
                .statusCode(200);
    }
    @Test
    void testLowestReducedVatRate() {
        given()
                .when().get("/lowest-reduced-vat")
                .then()
                .statusCode(200);
    }
}
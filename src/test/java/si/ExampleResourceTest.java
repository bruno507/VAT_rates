package si;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ExampleResourceTest {
    @Test
    void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(404);
    }
    void testHighestStandardVatRate() {
        given()
                .when().get("/highest-standard-vat")
                .then()
                .statusCode(200);
    }
    void testLowestReducedVatRate() {
        given()
                .when().get("/lowest-reduced-vat")
                .then()
                .statusCode(200);
    }

}
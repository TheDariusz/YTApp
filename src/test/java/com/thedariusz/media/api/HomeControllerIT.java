package com.thedariusz.media.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class HomeControllerIT {

    @LocalServerPort
    int randomPort;

    @BeforeEach
    public final void setup() {
        port = randomPort;
    }

    @Test
    void shouldReturnValidPage() {
        given()
                .when()
                .get("/home")
                .then()
                .statusCode(200)
                .body(containsString("Click link below if you want to login"));
    }
}
package com.thedariusz.ytapp.controllers;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerIT {

    @LocalServerPort
    int randomPort;

    @BeforeEach
    public final void setup() {
        RestAssured.port = randomPort;
    }

    String HOME_PAGE = """
            <html lang="en">
              <head>
                <meta charset="UTF-8"/>
                <title>YTApp</title>
              </head>
              <body>
                <h1>
                Hello stranger!
            </h1>
                <p>
                Click link below if you want to login
            </p>
                <a shape="rect" href="/home/yt">
                secret page
            </a>
              </body>
            </html>
            """;

    @Test
    void shouldReturnValidPage() {
        RestAssured.given()
                .when()
                .get("/home")
                .then()
                .statusCode(200)
                .body(Matchers.is(HOME_PAGE));
    }
}
package br.com.marcia.restassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.collection.IsCollectionWithSize.*;
import static org.hamcrest.core.IsCollectionContaining.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.StringContains.containsString;

import io.restassured.http.ContentType;

import org.junit.Test;

public class TesteApi {

    @Test
    public void testePost() {

        String baseUri = "https://reqres.in/api/login";

        String myJson = "{\"email\":\"eve.holt@reqres.in\",\"password\": \"cityslicka\"}";

        given().
            contentType(ContentType.JSON).
            body(myJson).
        when().
            post(baseUri).
        then().
            statusCode(200).
            body("token", containsString("QpwL5tke4Pnpja7X4"));

    }

    @Test
    public void testeGet() {

        String baseUri = "https://reqres.in/api/users";

        given().
            params("page", 2).
        when().
            get(baseUri).
        then().
            statusCode(200).
            body("page", equalTo(2),
                "data[0].id", equalTo(7),
                        "data[0].email", equalTo("michael.lawson@reqres.in"),
                        "data", hasSize(6),
                        "data.first_name", hasItems("Michael", "Rachel"));
    }

}
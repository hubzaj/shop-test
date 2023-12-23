package org.shop.test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;
import org.shop.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class AlbumControllerTest extends BaseTest {

    @Test
    void createNewAlbumTest() {
        // Given
        RequestSpecification httpRequest = RestAssured.given();

        // When
        Response response = httpRequest.get("health");

        // Then
        response.then()
                .log()
                .ifValidationFails(LogDetail.BODY)
                .statusCode(200)
                .body(equalTo("\"Hi! I am alive!\""));
    }

}

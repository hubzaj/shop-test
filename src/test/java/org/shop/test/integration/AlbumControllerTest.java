package org.shop.test.integration;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.shop.test.Tag.INTEGRATION_TEST;
import static org.shop.test.Tag.SMOKE_TEST;

@Tags({
        @Tag(INTEGRATION_TEST),
        @Tag(SMOKE_TEST),
})
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
                .body(equalTo(extendWithQuotes("Hi! I am alive!")));
    }

    private static String extendWithQuotes(String body) {
        return String.format("\"%s\"", body);
    }

}

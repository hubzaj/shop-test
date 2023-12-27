package org.shop.test.integration.album;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.shop.model.Album;
import org.shop.test.integration.BaseTest;

import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;
import static org.shop.Endpoint.CREATE_ALBUM;
import static org.shop.Endpoint.GET_ALBUMS;
import static org.shop.test.Tag.SMOKE_TEST;
import static org.shop.utils.TestDataGenerator.generateRandomAlphabeticString;
import static org.shop.utils.TestDataGenerator.generateRandomFloat;

@Tags({
        @Tag(SMOKE_TEST),
})
public class AlbumControllerTest extends BaseTest {

    @Test
    void createNewAlbumTest() {
        // Given
        RequestSpecification httpRequest = RestAssured.given();

        Album album = Album.builder()
                .title(generateRandomAlphabeticString())
                .artist(generateRandomAlphabeticString())
                .price(generateRandomFloat())
                .build();

        // When
        Response response = httpRequest
                .contentType(JSON)
                .body(album)
                .post(CREATE_ALBUM);

        // Then
        response.then()
                .log()
                .ifValidationFails(BODY)
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("title", Matchers.equalTo(album.getTitle()))
                .body("artist", Matchers.equalTo(album.getArtist()))
                .body("price", Matchers.equalTo(album.getPrice()));
    }

    @Test
    void getAlbumsTest() {
        // Given
        RequestSpecification httpRequest = RestAssured.given();

        Album album = createNewAlbum();

        // When
        Response response = httpRequest.get(GET_ALBUMS);

        // Then
        response.then()
                .log()
                .ifValidationFails(BODY)
                .statusCode(200)
                .body("id", Matchers.hasItem(album.getId().toString()))
                .body("title", Matchers.hasItem(album.getTitle()))
                .body("artist", Matchers.hasItem(album.getArtist()))
                .body("price", Matchers.hasItem(album.getPrice()));
    }

    private static Album createNewAlbum() {
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest
                .contentType(JSON)
                .body(
                        Album.builder()
                                .title(generateRandomAlphabeticString())
                                .artist(generateRandomAlphabeticString())
                                .price(generateRandomFloat())
                                .build()
                )
                .post(CREATE_ALBUM);

        response.then()
                .log()
                .body()
                .statusCode(201);

        return response.as(Album.class);
    }

}

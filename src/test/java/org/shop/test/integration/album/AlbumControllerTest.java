package org.shop.test.integration.album;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.shop.model.Shop;
import org.shop.test.integration.BaseTest;

import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;
import static org.shop.Endpoint.CREATE_ALBUM;
import static org.shop.Endpoint.GET_ALBUMS;
import static org.shop.test.Tag.SMOKE_TEST;
import static org.shop.utils.Protobuf.fromJson;
import static org.shop.utils.Protobuf.toJson;
import static org.shop.utils.TestDataGenerator.generateRandomAlphabeticString;
import static org.shop.utils.TestDataGenerator.generateRandomFloat;

@Tags({
        @Tag(SMOKE_TEST),
})
public class AlbumControllerTest extends BaseTest {

    @Test
    @SneakyThrows
    void createNewAlbumTest(RequestSpecification httpRequest) {
        // Given
        Shop.Album album = Shop.Album.newBuilder()
                .setTitle(generateRandomAlphabeticString())
                .setArtist(generateRandomAlphabeticString())
                .setPrice(generateRandomFloat())
                .build();

        // When
        Response response = httpRequest
                .contentType(JSON)
                .body(toJson(album))
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

    @SneakyThrows
    @Test
    void getAlbumsTest(RequestSpecification httpRequest) {
        // Given
        Shop.Album album = createNewAlbum(httpRequest);

        // When
        Response response = httpRequest.get(GET_ALBUMS);

        // Then
        response.then()
                .log()
                .ifValidationFails(BODY)
                .statusCode(200)
                .body("id", Matchers.hasItem(album.getId()))
                .body("title", Matchers.hasItem(album.getTitle()))
                .body("artist", Matchers.hasItem(album.getArtist()))
                .body("price", Matchers.hasItem(album.getPrice()));
    }

    @SneakyThrows
    private static Shop.Album createNewAlbum(RequestSpecification httpRequest) {
        Response response = httpRequest
                .contentType(JSON)
                .body(
                        toJson(
                                Shop.Album.newBuilder()
                                        .setTitle(generateRandomAlphabeticString())
                                        .setArtist(generateRandomAlphabeticString())
                                        .setPrice(generateRandomFloat())
                                        .build()
                        )
                )
                .post(CREATE_ALBUM);

        response.then()
                .log()
                .body()
                .statusCode(201);

        return (Shop.Album) fromJson(response);
    }

}

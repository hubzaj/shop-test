package org.shop;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.shop.config.Config;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
public class BaseTest {

    private static final Config CONFIG = Config.getConfig();

    @BeforeAll
    void beforeAll() {
        RestAssured.baseURI = CONFIG.getShopHost();
        RestAssured.port = CONFIG.getShopPort();
    }

}

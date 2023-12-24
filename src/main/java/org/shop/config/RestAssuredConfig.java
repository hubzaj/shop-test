package org.shop.config;


import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class RestAssuredConfig implements BeforeAllCallback {

    private static final Config CONFIG = Config.getConfig();

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        RestAssured.baseURI = CONFIG.getShopHost();
        RestAssured.port = CONFIG.getShopPort();
    }

}

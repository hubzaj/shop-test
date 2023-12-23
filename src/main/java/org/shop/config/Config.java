package org.shop.config;

import lombok.Getter;

@Getter
public class Config {

    private static final String SHOP_HOST_ENV = "SHOP_HOST";
    private static final String SHOP_PORT_ENV = "SHOP_PORT";
    private static final String SHOP_BASE_API_URL_ENV = "SHOP_BASE_API_URL";

    private static Config config;

    private String shopHost = "http://localhost";
    private int shopPort = 9000;
    private String shopBaseApiUrl = "/api/v1/shop";

    private String os;

    private Config() {
        loadShopBaseApiUrl();
        loadShopPort();
        loadShopHost();
        loadOsName();
    }

    public synchronized static Config getConfig() {
        if (config == null) {
            config = new Config();
        }
        return config;
    }

    private void loadShopBaseApiUrl() {
        String url = System.getenv(SHOP_BASE_API_URL_ENV);
        shopBaseApiUrl = url != null ? url : shopBaseApiUrl;
    }

    private void loadShopPort() {
        String port = System.getenv(SHOP_PORT_ENV);
        shopPort = port != null ? Integer.parseInt(port) : shopPort;
    }

    private void loadShopHost() {
        String url = System.getenv(SHOP_HOST_ENV);
        shopHost = url != null ? url + shopBaseApiUrl : shopHost + shopBaseApiUrl;
    }

    private void loadOsName() {
        os = System.getProperty("os.name");
    }

}

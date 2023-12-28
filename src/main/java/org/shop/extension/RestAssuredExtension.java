package org.shop.extension;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.shop.config.Config;

public class RestAssuredExtension implements ParameterResolver {

    private static final Config CONFIG = Config.getConfig();

    private static final String ON_DEMAND_ENV_COOKIE = "on_demand_env";

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == RequestSpecification.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return RestAssured.given()
                .baseUri(CONFIG.getShopHost())
                .port(CONFIG.getShopPort())
                .basePath(CONFIG.getShopBaseApiUrl())
                .cookie(ON_DEMAND_ENV_COOKIE, CONFIG.getShopOnDemandSuffix());
    }

}

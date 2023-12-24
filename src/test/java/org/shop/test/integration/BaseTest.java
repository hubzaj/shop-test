package org.shop.test.integration;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.shop.config.RestAssuredConfig;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@ExtendWith({
        RestAssuredConfig.class
})
public class BaseTest {

}

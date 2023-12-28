package org.shop.test.integration;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.shop.extension.RestAssuredExtension;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

@TestInstance(PER_CLASS)
@ExtendWith({
        RestAssuredExtension.class
})
public class BaseTest {

}

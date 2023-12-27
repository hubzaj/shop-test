package org.shop.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

@UtilityClass
public class TestDataGenerator {

    public String generateRandomAlphabeticString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public String generateRandomAlphabeticString() {
        return generateRandomAlphabeticString(10);
    }


    public float generateRandomFloat(float leftLimit, float rightLimit) {
        return leftLimit + new Random().nextFloat() * (rightLimit - leftLimit);
    }

    public float generateRandomFloat() {
        return generateRandomFloat(1, 100);
    }

}

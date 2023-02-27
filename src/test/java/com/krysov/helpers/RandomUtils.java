package com.krysov.helpers;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class RandomUtils {
    static Faker faker = new Faker();

    public static Integer id = faker.number().randomDigitNotZero(),
            userStatus = faker.number().randomDigit();

    public static String
            petName = faker.animal().name(),
            username = faker.name().username(),
            firstname = faker.name().firstName(),
            lastname = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password(),
            phone = faker.phoneNumber().phoneNumber();

    public static String getFirstname () {
        return faker.name().firstName();
    }

    public static String getEmail () {
        return faker.internet().emailAddress();
    }

    public static List<String> getPhotoUrls () {
        List<String> petPhotoUrls = new ArrayList<>();

        for (int i=0; i<2; i++) {
            petPhotoUrls.add(faker.internet().url());
        }

        return petPhotoUrls;
    }
}

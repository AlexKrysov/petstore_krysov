package com.krysov.specs.pets;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;

public class PetsResponse {
    public static ResponseSpecification petsResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(ALL)
            .build();
}

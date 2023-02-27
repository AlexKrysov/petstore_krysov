package com.krysov.specs.users;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.ResponseSpecification;

public class UsersResponse {
    public static ResponseSpecification userResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();
}

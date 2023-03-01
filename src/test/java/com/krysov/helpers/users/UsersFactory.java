package com.krysov.helpers.users;

import com.krysov.models.users.User;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.krysov.randomData.RandomUtils.*;
import static com.krysov.specs.users.UsersRequest.userRequestSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class UsersFactory {
    @Step("Создаем нового пользователя")
    public static User createNewUser() {
        return User
                .builder()
                .id(id)
                .username(username)
                .firstName(firstname)
                .lastName(lastname)
                .email(email)
                .password(password)
                .phone(phone)
                .userStatus(userStatus)
                .build();
    }

    @Step("Добавляем нового пользователя в магазин")
    public static Response addNewUserToStore(User newUser) {
        return given()
                .spec(userRequestSpec)
            .when()
                .body(newUser)
                .post("/user")
            .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/users/postUserSchema.json"))
                .extract().response();
    }
}

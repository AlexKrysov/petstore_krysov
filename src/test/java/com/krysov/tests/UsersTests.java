package com.krysov.tests;

import com.krysov.helpers.RandomUtils;
import com.krysov.models.users.User;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.krysov.helpers.users.UsersFactory.addNewUserToStore;
import static com.krysov.helpers.users.UsersFactory.createNewUser;
import static com.krysov.specs.users.UsersRequest.userRequestSpec;
import static com.krysov.specs.users.UsersResponse.userResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование функций пользователя")
public class UsersTests {

    @DisplayName("Добавление нового пользователя")
    @Test
    void createUserTest() {
        User userProfile = createNewUser();
        Response newUserInStore = addNewUserToStore(userProfile);

        step("Сервер должен прислать в ответе Id созданного пользователя", () -> {
            assert newUserInStore.path("message").equals(userProfile.getId().toString());
        });
    }

    @DisplayName("Получение информации о пользователе по username")
    @Test
    void getUserByIdTest() {
        User userProfile = createNewUser();
        addNewUserToStore(userProfile);

        step("Отправляем GET запрос", () -> {
            User user = given()
                    .spec(userRequestSpec)
                .when()
                    .get("/user/" + userProfile.getUsername())
                .then()
                    .spec(userResponseSpec)
                    .statusCode(200)
                    .body(matchesJsonSchemaInClasspath("schemas/users/getUserSchema.json"))
                    .extract().as(User.class);

            assertThat(user.getUsername()).isEqualTo(userProfile.getUsername());
            assertThat(user.getEmail()).isEqualTo(userProfile.getEmail());
        });
    }

    @DisplayName("Изменение информации о пользователе")
    @Test
    void updateUserInfoTest() {
        User userProfile = createNewUser();
        addNewUserToStore(userProfile);

        step("Отправляем PUT запрос на изменение данных", () -> {
            userProfile.setFirstName(RandomUtils.getFirstname());
            userProfile.setEmail(RandomUtils.getEmail());

            given()
                    .spec(userRequestSpec)
                .when()
                    .body(userProfile)
                    .put("/user/" + userProfile.getUsername())
                .then()
                    .spec(userResponseSpec)
                    .statusCode(200);

            step("Проверяем информацию о юзере", () -> {
                User user = given()
                        .spec(userRequestSpec)
                    .when()
                        .get("/user/" + userProfile.getUsername())
                    .then()
                        .spec(userResponseSpec)
                        .statusCode(200)
                        .body(matchesJsonSchemaInClasspath("schemas/users/getUserSchema.json"))
                        .extract().as(User.class);

                assertThat(user.getFirstName()).isEqualTo(userProfile.getFirstName());
                assertThat(user.getEmail()).isEqualTo(userProfile.getEmail());
            });
        });
    }
}

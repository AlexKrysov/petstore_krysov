package com.krysov.tests.user;

import com.krysov.randomData.RandomUtils;
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

@DisplayName("User testing")
public class UsersTests {

    @DisplayName("Create a new user")
    @Test
    void createUserTest() {
        User userProfile = createNewUser();
        Response newUserInStore = addNewUserToStore(userProfile);

        step("Checking id for create user", () -> {
            assert newUserInStore.path("message").equals(userProfile.getId().toString());
        });
    }

    @DisplayName("Get information for new user")
    @Test
    void getUserByIdTest() {
        User userProfile = createNewUser();
        addNewUserToStore(userProfile);

        step("send a get request", () -> {
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

    @DisplayName("Change user data")
    @Test
    void updateUserInfoTest() {
        User userProfile = createNewUser();
        addNewUserToStore(userProfile);

        step("send a put request", () -> {
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

            step("Check user data", () -> {
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

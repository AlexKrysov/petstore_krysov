package com.krysov.tests;

import com.krysov.helpers.RandomUtils;
import com.krysov.models.pets.Pet;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.krysov.helpers.pets.PetsFactory.addNewPetToStore;
import static com.krysov.helpers.pets.PetsFactory.createNewPet;
import static com.krysov.specs.pets.PetsRequest.petsRequestSpec;
import static com.krysov.specs.pets.PetsResponse.petsResponseSpec;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование раздела питомцев")
public class PetsTests {

    @DisplayName("Добавление нового питомца в магазин")
    @Test
    void createPetTest() {
        Pet petProfile = createNewPet();
        Pet newPet = addNewPetToStore(petProfile);

        step("Проверяем, что полученные данные соответсвуют отправленным", () -> {
            assertThat(newPet.getName()).isEqualTo(petProfile.getName());
            assertThat(newPet.getId()).isEqualTo(petProfile.getId());
        });
    }

    @DisplayName("Получение информации о питомце по ID")
    @Test
    void getPetByIdTest() {
        Pet petProfile = createNewPet();
        addNewPetToStore(petProfile);

        Pet getPetById = given()
                .spec(petsRequestSpec)
            .when()
                .get("/pet/" + petProfile.getId())
            .then()
                .spec(petsResponseSpec)
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/pets/getPetSchema.json"))
                .extract().as(Pet.class);

        step("Проверяем, что данные питомца соответствует заданным", () -> {
            assertThat(getPetById.getId()).isEqualTo(petProfile.getId());
            assertThat(getPetById.getName()).isEqualTo(petProfile.getName());
            assertThat(getPetById.getPhotoUrls()).isEqualTo(petProfile.getPhotoUrls());
        });
    }

    @DisplayName("Обновление информации о питомце")
    @Test
    void updatePetInformationTest() {
        Pet petProfile = createNewPet();
        addNewPetToStore(petProfile);

        step("Меняем данные питомца", () -> {
            petProfile.setName("New_Pet_Name");
            petProfile.setPhotoUrls(RandomUtils.getPhotoUrls());
            Pet updatedPetProfile = given()
                    .spec(petsRequestSpec)
                    .when()
                    .body(petProfile)
                    .put("/pet")
                    .then()
                    .spec(petsResponseSpec)
                    .body(matchesJsonSchemaInClasspath("schemas/pets/postPetSchema.json"))
                    .statusCode(200)
                    .extract().as(Pet.class);

            step("Проверяем, что данные питомца изменились", () -> {
                assertThat(updatedPetProfile.getName()).isEqualTo(petProfile.getName());
                assertThat(updatedPetProfile.getPhotoUrls()).isEqualTo(petProfile.getPhotoUrls());
            });
        });

    }

    @DisplayName("Удаление информации о питомце по ID")
    @Test
    void delPetByIdTest() {
        Pet petProfile = createNewPet();
        Pet newPetInStore = addNewPetToStore(petProfile);

        step("Удаляем информацию о созданном питомце", () -> {
            Response response = given()
                    .spec(petsRequestSpec)
                    .when()
                    .delete("/pet/" + newPetInStore.getId())
                    .then()
                    .spec(petsResponseSpec)
                    .body(matchesJsonSchemaInClasspath("schemas/pets/deletePetSchema.json"))
                    .statusCode(200)
                    .extract().response();

            step("Сервер должен прислать в ответе Id удаленного питомца", () -> {
                assert response.path("message").equals(newPetInStore.getId().toString());
            });
        });

    }
}

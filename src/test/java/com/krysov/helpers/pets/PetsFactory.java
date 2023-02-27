package com.krysov.helpers.pets;

import com.krysov.models.pets.Pet;
import io.qameta.allure.Step;

import static com.krysov.helpers.RandomUtils.*;
import static com.krysov.specs.pets.PetsRequest.petsRequestSpec;
import static com.krysov.specs.pets.PetsResponse.petsResponseSpec;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class PetsFactory {

    @Step("Создаем нового питомца")
    public static Pet createNewPet() {
        Pet petsData = new Pet();
        petsData.setId(id);
        petsData.setName(petName);
        petsData.setPhotoUrls(getPhotoUrls());
        return petsData;
    }

    @Step("Добавляем нового питомца в магазин")
    public static Pet addNewPetToStore(Pet petInfo) {
        return given()
                .spec(petsRequestSpec)
                .when()
                .body(petInfo)
                .post("/pet")
                .then()
                .spec(petsResponseSpec)
                .body(matchesJsonSchemaInClasspath("schemas/pets/postPetSchema.json"))
                .statusCode(200)
                .extract().as(Pet.class);
    }

}

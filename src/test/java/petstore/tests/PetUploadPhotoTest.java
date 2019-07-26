package petstore.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.PetEndPoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import java.io.File;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SerenityRunner.class)
public class PetUploadPhotoTest {
    @Steps
    private PetEndPoint petEndPoint;
    private PetModel petModel;
    private File petImage;

    @Before
    public void preCondition(){

        petModel = new PetModel(
                78,
                new CategoryModel(),
                "Zombie",
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndPoint
                .createPet(petModel)
                .statusCode(200)
                .body("size()", is(6))
                .body("any{it.value ==" + petModel.getId() + "}", is(true));

        try {
            petImage = new File(getClass().getClassLoader().getResource("testimonials-dog-taller.png").getFile());
        }
        catch(Exception e) {
            e.getMessage();
        }
}

    @After
    public void postCondition(){

        petEndPoint
                .deletePet(petModel.getId())
                .statusCode(200);
        petEndPoint.getPetById(petModel.getId())
                .statusCode(404)
                .body("any{it.value == 'Pet not found'}", is(true));
    }

    @Test
    public void uploadPetPhotoTest(){

        petEndPoint.uploadImage(petModel.getId(),petImage)
                .statusCode(200)
                .body("message", containsString("testimonials-dog-taller.png"))
                .log().all();

    }
}

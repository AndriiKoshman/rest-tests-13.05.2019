package petstore.tests;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.PetEndPoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class PetCreateTest {

    @TestData
    public static Collection<Object[]> testData(){
        return Arrays.asList(new Object[][]{
                {"Sharik",20, 200},
                {"Rex",21, 200},
                {"Zhuchka",22, 200}
        });
    }
    private final String petName;
    private final int petId;
    private final int statusCode;

    public PetCreateTest(String petName, int petId, int statusCode) {
        this.petName = petName;
        this.petId = petId;
        this.statusCode = statusCode;
    }

    @Steps
    private PetEndPoint petEndPoint;
    private  PetModel petModel;

    @Test
    public void createPetTest() {
        petModel = new PetModel(
                this.petId,
                new CategoryModel(),
                this.petName,
                new String[]{"www.zoo.com"},
                new TagModel[]{new TagModel()},
                "AVAILABLE");

        petEndPoint.createPet(petModel)
                .statusCode(this.statusCode);
    }

    @After
    public void postCondition(){

        petEndPoint
                .deletePet(petModel.getId())
                .statusCode(200);
    }

}





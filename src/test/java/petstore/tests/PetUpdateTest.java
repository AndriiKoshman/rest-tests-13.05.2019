package petstore.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import petstore.endpoints.PetEndPoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import static org.hamcrest.core.Is.is;

public class PetUpdateTest {

  private PetEndPoint petEndPoint = new PetEndPoint();
  private PetModel petModel;

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
        .body("size()",is(6))
        .body("any{it.value == 78}", is(true));
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
  public void updatePetTest(){
    petModel.setName("lion");
    petModel.setStatus("SOLD");

    petEndPoint.updatePet(petModel)
        .statusCode(200)
        .body("size()",is(6))
        .body("any{it.value == 'SOLD'}", is(true))
        .body("any{it.value == 'lion'}", is(true));

    petEndPoint
        .getPetById(petModel.getId())
        .statusCode(200)
        .body("size()",is(6));
  }
}

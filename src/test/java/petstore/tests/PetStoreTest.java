package petstore.tests;

import org.junit.Before;
import org.junit.Test;
import petstore.endpoints.PetEndPoint;
import petstore.models.CategoryModel;
import petstore.models.PetModel;
import petstore.models.TagModel;

import static petstore.endpoints.PetEndPoint.Status;

public class PetStoreTest {

  private PetEndPoint petEndPoint = new PetEndPoint();
  private PetModel petModel;

  @Before
  public void createPetTest() {
    petModel = new PetModel(
        20,
        new CategoryModel(),
        "Zombie",
        new String[]{"www.zoo.com"},
        new TagModel[]{new TagModel()},
        "AVAILABLE");

    petEndPoint.createPet(petModel)
        .statusCode(200);
  }

  @Test
  public void getPetByIdTest() {

    petEndPoint
        .getPetById(petModel.getId())
        .statusCode(200);
  }

  @Test
  public void getPetByStatusTest() {

    for (Status status : Status.values()) {
      petEndPoint.getPetByStatus(status)
      .statusCode(200);
    }
  }



}




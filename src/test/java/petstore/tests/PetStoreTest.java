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

import static petstore.endpoints.PetEndPoint.Status;

@RunWith(SerenityRunner.class)
public class PetStoreTest {

  @Steps
  private PetEndPoint petEndPoint;
  private  PetModel petModel;

  @Before
  public void createPetTest() {
    petModel = new PetModel(
        20,
        new CategoryModel(),
        "Rex",
        new String[]{"www.zoo.com"},
        new TagModel[]{new TagModel()},
        "AVAILABLE");

    petEndPoint.createPet(petModel)
        .statusCode(200);
  }

  @After
  public void postCondition(){

    petEndPoint
            .deletePet(petModel.getId())
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




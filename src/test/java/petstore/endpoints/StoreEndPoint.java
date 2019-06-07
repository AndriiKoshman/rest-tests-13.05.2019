package petstore.endpoints;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import petstore.models.OrderModel;

public class StoreEndPoint {

  Logger log = LoggerFactory.getLogger(this.getClass().getName());

  private RequestSpecification given(){
    SerenityRest.enableLoggingOfRequestAndResponseIfValidationFails();
    return RestAssured.given()
        .baseUri(Config.BASE_URI)
        .contentType("application/json");
        //.log().uri();
  }

  @Step
  public ValidatableResponse getInventories(int petId){
    return given()
        .get(Config.GET_INVENTORIES_BY_STATUS, petId)
        .then();
        //.log().all();

  }

  @Step
  public ValidatableResponse createOrder(OrderModel orderModel) {
    return given()
        .body(orderModel)
        .post(Config.CREATE_ORDER)
        .then();
        //.log().all();
  }

  public enum Status {
    PLACED,
    APPROVED,
    DELIVERED
  }

  @Step
  public ValidatableResponse getOrderById(int id) {
    log.info("Executing: getOrderById");
    return given()
        .get(Config.FIND_ORDER_BY_ID,id)
        .then();
        //.log().all();
  }


  @Step
  public ValidatableResponse deleteOrder(int id) {
    return given()
        .delete(Config.FIND_ORDER_BY_ID,id)
        .then();
        //.log().all();
  }

}

package petstore.endpoints;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class StoreEndPoint {

  private RequestSpecification given(){
    return RestAssured.given()
        .baseUri(Config.BASE_URI)
        .contentType("application/json")
        .log().uri();
  }
}

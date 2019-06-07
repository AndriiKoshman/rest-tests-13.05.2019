package weather;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.is;

@RunWith(SerenityRunner.class)
public class WeatherTest {
  @Test
  public void getWeatherPerCityTest() {
    String cityName = "Kharkiv";
    //get city Id by city name
    RestAssured.baseURI = "https://pinformer.sinoptik.ua";
    ValidatableResponse response= RestAssured.given()
        .basePath("search.php")
        .param("lang", "ua")
        .param("return_id", 1)
        .param("q", cityName)
        //.log().uri()
        .get()
        .then()
        //.log().all()
        .statusCode(200);

    String array[] =response.extract().asString().split("\\|");
    String cityId = array[2];

    //get weather by city Id
    response= RestAssured.given()
        .basePath("pinformer4.php")
        .param("lang", "ua")
        .param("type", "js")
        .param("id", cityId)
        //.log().uri()
        .get()
        .then()
        .log().all()
        .statusCode(200)
        .body("any{it.key == '{pcity}'}", is(true));
        //.body("'{pcity}'",is(cityId));

      String weather = response.extract().asString();
  }

}

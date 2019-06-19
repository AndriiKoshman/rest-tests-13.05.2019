package petstore.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Concurrent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import petstore.endpoints.StoreEndPoint;
import petstore.models.OrderModel;

import static org.hamcrest.core.Is.is;

@RunWith(SerenityRunner.class)
    @Concurrent
    public class PetOrderTest {

      @Steps
      private StoreEndPoint storeEndPoint;
      private OrderModel orderModel;

      @Before
      public void preCondition(){

        orderModel = new OrderModel(
                5,
                1,
                1,
                "2019-05-31T16:11:00.667Z",
                "PLACED",
                false);

        storeEndPoint
                .createOrder(orderModel)
                .statusCode(200)
                .body("size()",is(6))
                .body("any{it.value =="+ orderModel.getId() +"}", is(true));
      }

      @After
      public void postCondition(){

        storeEndPoint
                .deleteOrder(orderModel.getId())
                .statusCode(200);
        storeEndPoint.getOrderById(orderModel.getId())
                .statusCode(404)
                .body("any{it.value == 'Order not found'}", is(true));
      }

      @Test
      public void getOrderTest(){
        storeEndPoint
                .getOrderById(orderModel.getId())
                .statusCode(200)
        .body("any{it.value =="+ orderModel.getId() +"}",is(true));
  }
}

package steps;

import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.junit.Assert;
import report.Log;
import stepshelpers.Memory;

public class CreateOrderSteps {

  @And("Проверяем, что ID заказа в ответе совпадает с ID из запроса")
  public void verifyOrderIdInResponse() {
    Response response = (Response) Memory.get("response");
    String requestBody = (String) Memory.get("requestBody");
    int expectedId = Integer.parseInt(response.jsonPath().getString("id"));
    int actualId = Integer.parseInt(response.jsonPath().getString("id"));
    Assert.assertEquals("ID заказа в ответе не соответствует ID из запроса", expectedId, actualId);
    Log.log("ID заказа совпадает: " + actualId);
  }
}

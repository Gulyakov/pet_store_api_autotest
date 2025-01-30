package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.junit.Assert;
import report.Log;
import stepshelpers.Memory;

public class FindByStatusSteps {

  @Given("Для параметра status указано значение {string}")
  public void givenStatusValue(String value) {
    Memory.put("value", value);
  }

  @When("Отправляем GET запрос на {string}")
  public void whenSendGetRequestToFindPetsByStatus(String endpoint) {
    Response response = RestAssured.given().queryParam("status", Memory.get("value")).when()
        .get(Memory.get("baseURI") + endpoint);
    Memory.put("response", response);
  }

  @And("Возвращается список животных со статусом {string}")
  public void andVerifyPetsStatusInResponse(String expectedStatus) {
    List<String> statuses = CommonSteps.getResponse().jsonPath().getList("status");
    Assert.assertNotNull("Ответ не содержит поле 'status' ", statuses);
    Assert.assertFalse("Список статусов пуст ", statuses.isEmpty());
    for (String actualStatus : statuses) {
      Assert.assertEquals(expectedStatus, actualStatus);
    }
    Log.log("Тело ответа: " + CommonSteps.getResponse().getBody().asString());
  }
}

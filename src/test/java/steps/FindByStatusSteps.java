package steps;

import static io.restassured.RestAssured.given;
import static steps.CommonSteps.getResponse;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
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
    Response response = given().queryParam("status", Memory.get("value")).when()
        .get(Memory.get("baseURI") + endpoint);
    Memory.put("response", response);
  }

  @And("Возвращается список животных со статусом {string}")
  public void andVerifyPetsStatusInResponse(String expectedStatus) {
    List<String> statuses = getResponse().jsonPath().getList("status");
    for (String actualStatus : statuses) {
      Assert.assertEquals(expectedStatus, actualStatus);
    }
    Log.log("Тело ответа: " + getResponse().getBody().asString());
  }
}

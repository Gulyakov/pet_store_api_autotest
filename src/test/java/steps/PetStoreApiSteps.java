package steps;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import java.util.List;
import org.junit.Assert;
import stepshelpers.Memory;

public class PetStoreApiSteps {

  @Given("Для параметра status указано значение {string}")
  public void givenStatusValue(String value) {
    Memory.put("value", value);
  }

  @When("Отправляем GET запрос на {string}")
  public void whenSendGetRequestToFindPetsByStatus(String endpoint) {
    Memory.put("baseURI", "https://petstore.swagger.io/v2");
    Response response = given()
        .queryParam("status", Memory.get("value"))
        .when()
        .get(Memory.get("baseURI") + endpoint);
    Memory.put("response", response);

  }

  @Then("Получаем код ответа {int}")
  public void thenVerifyResponseStatusCode(int statusCode) {
    Assert.assertEquals(200, getResponse().getStatusCode());
  }

  @And("Возвращается список животных со статусом {string}")
  public void andVerifyPetsStatusInResponse(String expectedStatus) {
    List<String> statuses = getResponse().jsonPath().getList("status");
    for (String actualStatus : statuses) {
      Assert.assertEquals(expectedStatus, actualStatus);
    }
  }

  private Response getResponse() {
    return (Response) Memory.get("response");
  }

}
package steps;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import java.nio.file.Paths;
import org.junit.Assert;
import report.Log;
import stepshelpers.Memory;
import utils.FileUtil;


public class CommonSteps {

  public static Response getResponse() {
    return (Response) Memory.get("response");
  }

  @Given("Тело запроса загружено из {string}")
  public static void loadRequestBody(String filePath) throws Exception {
    String requestBody = FileUtil.read(filePath);
    Memory.put("requestBody", requestBody);
    Log.log("Тело запроса: " + requestBody);
  }

  @Then("Получаем код ответа {int}")
  public void thenVerifyStatusCode(int statusCode) {
    Assert.assertEquals("Код ответа не соответствует ожидаемому", statusCode,
        getResponse().statusCode());
    Log.log("Код ответа: " + getResponse().getStatusCode());
  }

  @And("Проверяем, что ответ соответствует схеме {string}")
  public void verifyResponseMatchesSchema(String schemaPath) {
    Response response = getResponse();
    response.then().assertThat()
        .body(JsonSchemaValidator.matchesJsonSchema(Paths.get(schemaPath).toFile()));
    Log.log("Ответ соответствует заданной JSON-схеме: " + schemaPath);
  }

  @When("Отправляем {string} запрос на {string}")
  public void sendPostRequest(String method, String endpoint) {
    String requestBody = (String) Memory.get("requestBody");
    if (requestBody == null) {
      requestBody = "";
    }
    Response response = given()
        .header("Content-Type", "application/json")
        .body(requestBody)
        .request(method.toUpperCase(), Memory.get("baseURI") + endpoint);
    Memory.put("response", response);
    Log.log("Адрес запроса: " + Memory.get("baseURI") + endpoint);
    Log.log("Тело ответа: " + response.getBody().asString());
  }

}


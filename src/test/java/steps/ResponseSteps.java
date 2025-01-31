package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import report.Log;
import stepshelpers.ApiHelper;
import stepshelpers.Memory;
import utils.JsonUtil;

public class ResponseSteps {

  @Then("Проверяем, что код ответа равен {int}")
  public void verifyStatusCode(int expectedStatusCode) {
    int actualStatusCode = ApiHelper.getResponse().statusCode();
    Assert.assertEquals(
        "Код ответа не соответствует ожидаемому",
        expectedStatusCode,
        actualStatusCode
    );
    Log.log("Код ответа: " + actualStatusCode);
  }

  @And("Проверяем, что ответ соответствует схеме {string}")
  public void verifyResponseMatchesSchema(String schemaPath) {
    ApiHelper.getResponse().then().assertThat()
        .body(JsonSchemaValidator.matchesJsonSchema(Paths.get(schemaPath).toFile()));
    Log.log("Ответ соответствует заданной JSON-схеме: " + schemaPath);
  }

  @And("Проверяем, что значения свойств из ответа совпадают со значениями свойств из запроса")
  public void verifyPropertiesInResponse() {
    String requestBody = ApiHelper.getRequestBody();
    String responseBody = ApiHelper.getResponse().getBody().asString();
    Map<String, Object> requestMap = JsonUtil.jsonToMap(requestBody);
    Map<String, Object> responseMap = JsonUtil.jsonToMap(responseBody);
    Log.log("Свойства и значения запроса: " + requestMap + System.lineSeparator()
        + "Свойства и значения ответа: " + responseMap);
    Assert.assertEquals("JSON-запрос и ответ не совпадают", requestMap, responseMap);
  }

  @And("Возвращается список животных со статусом {string}")
  public void verifyPetsStatusInResponse(String expectedStatus) {
    List<String> statuses = ApiHelper.getResponse().jsonPath().getList("status");
    Assert.assertNotNull("Ответ не содержит поле 'status' ", statuses);
    Assert.assertFalse("Список статусов пуст ", statuses.isEmpty());
    for (String actualStatus : statuses) {
      Assert.assertEquals(expectedStatus, actualStatus);
    }
    Log.log("Тело ответа: " + ApiHelper.getResponse().getBody().asString());
  }

  @And("Проверяем, что сообщение в ответе равно {string}")
  public void verifyMessageInResponse(String expectedMessage) {
    String actualMessage = ApiHelper.getResponse().jsonPath().getString("message");
    Assert.assertEquals("Сообщение в ответе не соответствует ожидаемому", expectedMessage,
        actualMessage);
    Log.log(
        "Сообщение в ответе: '" + actualMessage + "' соответствует ожидаемому: '" + expectedMessage
            + "'");
  }

  @And("ID созданного животного сохранен в Memory как petId")
  public void savePetIdInMemory() {
    Memory.put("petId", ApiHelper.getResponse().jsonPath().getString("id"));
  }
}

package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import report.Log;
import stepshelpers.ApiHelper;
import stepshelpers.Memory;
import utils.FileUtil;

public class RequestSteps {

  @Given("Тело запроса загружено из {string}")
  public static void loadRequestBody(String filePath) throws Exception {
    String requestBody = FileUtil.read(filePath);
    Memory.put("requestBody", requestBody);
    Log.log("Тело запроса: " + requestBody);
  }

  @When("Отправляем GET запрос на {string}")
  public void sendGetRequest(String endpoint) {
    String statusParam = (String) Memory.get("status");
    if (statusParam != null) {
      ApiHelper.sendRequest("GET", endpoint, null, "status", statusParam);
    } else {
      ApiHelper.sendRequest("GET", endpoint, null, null, null);
    }
  }

  @When("Отправляем POST запрос на {string}")
  public void sendPostRequest(String endpoint) {
    ApiHelper.sendRequest("POST", endpoint, ApiHelper.getRequestBody(), null, null);
  }

  @And("Отправляем PUT запрос на {string}")
  public void sendPutRequest(String endpoint) {
    ApiHelper.sendRequest("PUT", endpoint, ApiHelper.getRequestBody(), null, null);
  }

  @And("Отправляем DELETE запрос на {string}")
  public void sendDeleteRequest(String endpoint) {
    ApiHelper.sendRequest("DELETE", endpoint, ApiHelper.getRequestBody(), null, null);
  }

  @Given("Для параметра status указано значение {string}")
  public void givenStatusValue(String status) {
    Memory.put("status", status);
  }
}

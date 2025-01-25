package steps;

import static org.junit.Assert.assertEquals;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import java.io.IOException;
import report.Log;
import stepshelpers.Memory;
import utils.FileUtil;


public class UpdatePetSteps {

  @And("Проверяем, что ответ идентичен телу запроса из {string}")
  public void verifyResponseMatchesRequestBody(String requestBodyPath) throws IOException {
    String expectedBody = FileUtil.read(requestBodyPath);
    Response response = (Response) Memory.get("response");
    String actualBody = response.getBody().asString();
    JsonObject expectedJson = JsonParser.parseString(expectedBody).getAsJsonObject();
    JsonObject actualJson = JsonParser.parseString(actualBody).getAsJsonObject();
    assertEquals("Ответ не совпадает с ожидаемым телом запроса", expectedJson, actualJson);
    Log.log("Ответ совпадает с телом запроса: " + actualJson.toString());
  }

}
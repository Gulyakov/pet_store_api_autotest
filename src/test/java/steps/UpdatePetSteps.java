package steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import java.io.IOException;
import org.junit.Assert;
import report.Log;
import utils.FileUtil;

public class UpdatePetSteps {

  @And("Проверяем, что ответ идентичен телу запроса из {string}")
  public void verifyResponseMatchesRequestBody(String requestBodyPath) throws IOException {
    String expectedBody = FileUtil.read(requestBodyPath);
    String actualBody = CommonSteps.getResponse().getBody().asString();
    JsonObject expectedJson = JsonParser.parseString(expectedBody).getAsJsonObject();
    JsonObject actualJson = JsonParser.parseString(actualBody).getAsJsonObject();
    Assert.assertEquals("Ответ не совпадает с ожидаемым телом запроса", expectedJson, actualJson);
    Log.log("Ответ совпадает с телом запроса: " + actualJson.toString());
  }
}

package steps;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.junit.Assert;
import report.Log;
import stepshelpers.Memory;

public class CreatePetSteps {

  @And("Проверяем, что имя животного в ответе соответствует имени из запроса")
  public void verifyPetNameInResponse() {
    Response response = CommonSteps.getResponse();
    String actualName = response.jsonPath().getString("name");
    String expectedName = getPetNameFromRequest();
    Assert.assertEquals("Имя животного в ответе не соответствует имени из запроса", expectedName,
        actualName);
    Log.log("Имя животного из запроса: " + getPetNameFromRequest() + System.lineSeparator()
        + "Имя животного в ответе совпадает с именем из запроса: " + actualName);
  }

  private String getPetNameFromRequest() {
    JsonObject jsonObject = JsonParser.parseString((String) Memory.get("requestBody"))
        .getAsJsonObject();
    String petName = jsonObject.get("name").getAsString();
    Memory.put("expectedName", petName);
    return (String) Memory.get("expectedName");
  }
}

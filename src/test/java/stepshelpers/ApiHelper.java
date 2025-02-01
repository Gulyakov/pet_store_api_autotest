package stepshelpers;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import report.Log;

public class ApiHelper {

  public static Response getResponse() {
    return (Response) Memory.get("response");
  }

  public static String getRequestBody() {
    return (String) Memory.get("requestBody");
  }

  public static String getBaseURI() {
    return Memory.get("baseURI").toString();
  }

  public static Response sendGetRequest(String endpoint) {
    return RestAssured.given()
        .when()
        .get(getBaseURI() + endpoint);
  }

  public static Response sendGetRequest(String endpoint, String paramKey, String paramValue) {
    return RestAssured.given()
        .queryParam(paramKey, paramValue)
        .when()
        .get(getBaseURI() + endpoint);
  }

  public static Response sendPostRequest(String endpoint, String requestBody) {
    return RestAssured.given()
        .header("Content-Type", "application/json")
        .body(requestBody)
        .post(getBaseURI() + endpoint);
  }

  public static Response sendPutRequest(String endpoint, String requestBody) {
    return RestAssured.given()
        .header("Content-Type", "application/json")
        .body(requestBody)
        .put(getBaseURI() + endpoint);
  }

  public static Response sendDeleteRequest(String endpoint, String requestBody) {
    return RestAssured.given()
        .header("Content-Type", "application/json")
        .body(requestBody)
        .delete(getBaseURI() + endpoint);
  }

  public static void sendRequest(
      String method,
      String endpoint,
      String requestBody,
      String queryParamKey,
      String queryParamValue
  ) {
    endpoint = EndpointHelper.replacePathVariables(endpoint);
    Response response;
    switch (method.toUpperCase()) {
      case "GET":
        if (queryParamKey != null && queryParamValue != null) {
          response = ApiHelper.sendGetRequest(endpoint, queryParamKey, queryParamValue);
        } else {
          response = ApiHelper.sendGetRequest(endpoint);
        }
        break;
      case "POST":
        response = ApiHelper.sendPostRequest(endpoint, requestBody);
        break;
      case "PUT":
        response = ApiHelper.sendPutRequest(endpoint, requestBody);
        break;
      case "DELETE":
        response = ApiHelper.sendDeleteRequest(endpoint, requestBody);
        break;
      default:
        throw new IllegalArgumentException("Метод HTTP не поддерживается: " + method);
    }
    Memory.put("response", response);
    Log.log(
        method + " запрос отправлен на: " + ApiHelper.getBaseURI() + endpoint
            + System.lineSeparator() +
            "Тело ответа: " + response.getBody().asString()
    );
  }
}

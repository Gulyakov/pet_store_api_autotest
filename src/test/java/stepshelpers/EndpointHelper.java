package stepshelpers;

public class EndpointHelper {

  public static String replacePathVariables(String endpoint) {
    if (endpoint.contains("{petId")) {
      String petId = (String) Memory.get("petId");
      return endpoint.replace("{petId}", petId);
    } else {
      return endpoint;
    }
  }
}
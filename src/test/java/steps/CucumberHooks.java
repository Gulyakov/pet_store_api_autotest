package steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import report.Log;
import stepshelpers.Memory;


public class CucumberHooks {

  @Before
  public void before(Scenario scenario) {
    Memory.getInstance();
    Memory.put("baseURI", "https://petstore.swagger.io/v2");
    Log.setScenario(scenario);
  }

  @After
  public void after() {
    Memory.clear();
  }
}

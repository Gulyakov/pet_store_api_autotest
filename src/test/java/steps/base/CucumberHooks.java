package steps.base;

import org.junit.After;
import org.junit.Before;
import stepshelpers.Memory;

public class CucumberHooks {

  @Before
  public void before() {
    Memory.getInstance();

  }

  @After
  public void after() {
    Memory.clear();
  }
}

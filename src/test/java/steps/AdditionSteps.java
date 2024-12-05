package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import stepshelpers.Memory;

public class AdditionSteps {

  @Given("два числа {int} и {int}")
  public void givenTwoNumbers(int number1, int number2) {
    Memory.put("number1", number1);
    Memory.put("number2", number2);
  }

  @When("выполняем сложение заданных чисел")
  public void whenAddTheseTwoNumbers() {
    Memory.put("result", (Integer) Memory.get("number1") + (Integer) Memory.get("number2"));
  }

  @Then("результат должен быть равен {int}")
  public void thenTheResultShouldBe(int expectedResult) {
    Assert.assertEquals("Результат сложения неверный", expectedResult, Memory.get("result"));
  }
}
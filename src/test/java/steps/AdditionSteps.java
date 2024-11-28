package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class AdditionSteps {

  private int number1;
  private int number2;
  private int result;

  @Given("два числа {int} и {int}")
  public void givenTwoNumbers(int number1, int number2) {
    this.number1 = number1;
    this.number2 = number2;
  }

  @When("выполняем сложение заданных чисел")
  public void whenAddTheseTwoNumbers() {
    this.result = number1 + number2;
  }

  @Then("результат должен быть равен {int}")
  public void thenTheResultShouldBe(int expectedResult) {
    Assert.assertEquals("Результат сложения неверный", expectedResult, result);
  }
}
# encoding: UTF-8
# language: en

Feature: Сложение двух чисел
  Как пользователь
  Я хочу сложить два числа
  Чтобы проверить, что логика сложения работает корректно

  @debug @All
  Scenario: Сложить два позитивных числа
    Given два числа 2 и 3
    When выполняем сложение заданных чисел
    Then результат должен быть равен 5
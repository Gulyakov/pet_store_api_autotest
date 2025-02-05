# encoding: UTF-8
# language: en

Feature: Получить список животных по статусу
  Как пользователь
  Я хочу получить список животных по статусу
  Чтобы проверить, что возвращается правильный список

  @All @FindPetByStatus
  Scenario Outline: Получить список животных со статусом <status>
    Given Для параметра status указано значение "<status>"
    When Отправляем GET запрос на "/pet/findByStatus?status=<status>"
    Then Проверяем, что код ответа равен 200
    And Возвращается список животных со статусом "<status>"

    Examples:
      | status    |
      | available |
      | pending   |
      | sold      |

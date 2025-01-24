# encoding: UTF-8
# language: en

Feature: Создание нового животного
  Как пользователь
  Я хочу создать новое животное в системе
  Чтобы проверить, что оно успешно добавляется

  @debug @All
  Scenario: Успешное создание животного
    Given Тело запроса загружено из "src/test/resources/requests/post_pet_request_body.json"
    When Отправляем POST запрос на "/pet"
    Then Получаем код ответа 200
    And Проверяем, что ответ соответствует схеме "src/test/resources/schemas/post_pet_schema.json"
    And Проверяем, что имя животного в ответе соответствует имени из запроса

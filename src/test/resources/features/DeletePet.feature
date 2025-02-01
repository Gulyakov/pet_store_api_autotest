# encoding: UTF-8
# language: en

Feature: Удаление животного
  Как пользователь
  Я хочу иметь возможность удалить животное из системы по его ID
  Чтобы поддерживать актуальные данные в системе

  Background:
    Given Тело запроса загружено из "src/test/resources/requests/post_pet_request_body.json"
    When Отправляем POST запрос на "/pet"
    Then Проверяем, что код ответа равен 200
    And Проверяем, что ответ соответствует схеме "src/test/resources/schemas/post_pet_schema.json"
    And Проверяем, что значения свойств из ответа совпадают со значениями свойств из запроса
    And ID созданного животного сохранен в Memory как petId

  @All @DeletePet
  Scenario: Успешное удаление животного по его ID
    When Отправляем DELETE запрос на "/pet/{petId}"
    Then Проверяем, что код ответа равен 200
    And Отправляем GET запрос на "/pet/{petId}"
    Then Проверяем, что код ответа равен 404
    And Проверяем, что сообщение в ответе равно "Pet not found"

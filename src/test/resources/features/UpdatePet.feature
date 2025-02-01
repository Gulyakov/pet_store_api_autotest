# encoding: UTF-8
# language: en

Feature: Обновление данных о животном
  Как пользователь
  Я хочу иметь возможность обновлять данные о животном
  Чтобы поддерживать актуальную информацию

  @All @UpdatePet
  Scenario: Успешное обновление данных о животном
    Given Тело запроса загружено из "src/test/resources/requests/post_pet_request_body.json"
    When Отправляем POST запрос на "/pet"
    And Тело запроса загружено из "src/test/resources/requests/put_pet_request_body.json"
    And Отправляем PUT запрос на "/pet"
    Then Проверяем, что значения свойств из ответа совпадают со значениями свойств из запроса

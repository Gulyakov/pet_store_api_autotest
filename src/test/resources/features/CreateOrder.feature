# encoding: UTF-8
# language: en

Feature: Создание заказа
  Как пользователь
  Я хочу создать заказ на животное в системе
  Чтобы проверить, что заказ успешно создаётся и возвращается корректный ответ

  @All @CreateOrder
  Scenario: Успешное создание заказа
    Given Тело запроса загружено из "src/test/resources/requests/post_order_request_body.json"
    When Отправляем POST запрос на "/store/order"
    Then Проверяем, что код ответа равен 200
    And Проверяем, что ответ соответствует схеме "src/test/resources/schemas/post_order_schema.json"
    And Проверяем, что значения свойств из ответа совпадают со значениями свойств из запроса

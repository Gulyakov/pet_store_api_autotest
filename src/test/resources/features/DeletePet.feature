## encoding: UTF-8
## language: en
#
#Feature: Удаление животного
#  Как пользователь
#  Я хочу иметь возможность удалить животное из системы по его ID
#  Чтобы поддерживать актуальные данные в системе
#
#  Background:
#    Given В системе создано животное с телом запроса из "src/test/resources/requests/post_pet_request_body.json"
#    And ID созданного животного сохранен в Memory как "petId"
#
#  @debug @All
#  Scenario: Успешное удаление животного по его ID
#    When Отправляем "DELETE" запрос на "/pet/{petId}"
#    Then Получаем код ответа 200
#    And Отправляем "GET" запрос на "/pet/{petId}"
#    Then Получаем код ответа 404
#    And Проверяем, что сообщение в ответе равно "Pet not found"

# Pet Store API autotest
Проект автоматизации тестирования сервиса API зоомагазина http://petstore.swagger.io

# Технологии
- Java 8
- Cucumber / Gherkin
- Gradle
- RestAssured
- JUnit

# Java Code Convention

Описание: https://google.github.io/styleguide/javaguide.html

Для настройки Intellij IDEA:
- Файл для настройки в папке codestyle проекта
- Как импортировать файл: File > Settings > Editor > Code Style > Java > Scheme: (Иконка шестеренки) > Import scheme > IntelliJ IDEA code style xml

# Запуск

./gradlew cucumber_local -Dcucumber.tags="@debug"
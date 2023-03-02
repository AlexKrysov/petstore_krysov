## Проект по автоматизации API на тестовой платформе [Swagger Petstore](https://petstore.swagger.io/#/)

### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:heavy_check_mark: [Тесты для раздела Pets](https://github.com/alekseilisenkov/petstore-api-project/tree/main/src/test/java/com/alexlis/test/pet)

### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:heavy_check_mark: [Тесты для раздела Store](https://github.com/alekseilisenkov/petstore-api-project/tree/main/src/test/java/com/alexlis/test/store)

## :rocket: Технологии и инструменты:

<p  align="center"
<code><img width="5%" title="IntelliJ IDEA" src="images/logo's/allure-ee-logo.svg"></code>
<code><img width="5%" title="Java" src="images/logo's/git-logo.svg"></code>
<code><img width="5%" title="Selenide" src="images/logo's/gradle-logo.svg"></code>
<code><img width="5%" title="REST-Assured" src="images/logo's/IDEA-logo.svg"></code>
<code><img width="5%" title="Selenoid" src="images/logo's/java-logo.svg"></code>
<code><img width="5%" title="Gradle" src="images/logo's/jenkins-logo.svg"></code>
<code><img width="5%" title="JUnit5" src="images/logo's/jira-logo.svg"></code>
<code><img width="5%" title="Allure Report" src="images/logo's/junit5-logo.svg"></code>
<code><img width="5%" title="Allure TestOps" src="images/logo's/Telegram.svg"></code>
</p>

## Покрыт следующий функционал:
* API тесты:
    * ✅ Создание экземпляра питомца по заданным параметрам
    * ✅ Создание экземпляра питомца с рандомными параметрами
    * ✅ Обновление питомца
    * ✅ Удаление созданного питомца
    * ✅ Создание заказа с привязкой к питомцу
    * ✅ Удаление заказа


## Как запустить:
### Локально без параметров запуск всех тестов
```bash
gradle clean test
```

### Локально с параметрами запуск всех тестов
```bash
gradle clean -Dthreads=1 test
```

### Запуск тестов в разделе store
```bash
gradle clean api_store
```

### Запуск тестов в разделе pet
```bash
gradle clean api_pet
```

## Запуск в Jenkins:
OVERVIEW
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/Screenshot_1 Allure.png" border="0"></a>

SUITES
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/Screenshot_1 Allure.png" alt="" border="0"></a>

BEHAVIORS
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/Screenshot_3 Allure.png" alt="" border="0"></a>

STATUS
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/Screenshot_4 Allure.png" alt="" border="0"></a>

START PARAMETERS
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/Allure.png" alt="" border="0"></a>

## Интеграция с JIRA:
<a href="https://ibb.co/TvwPjzj"><img src="images/Screenshot_5 Allure.png" alt="" border="0"></a>

## Интеграция с TELEGRAM:
<a href="https://ibb.co/TvwPjzj"><img src="images/Screenshot_6 Telegram.png" alt="" border="0"></a>

## Интеграция с Allure TestOPS:
CUSTOM DASHBOARD
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/1 allure testops.png" alt="" border="0"></a>

LAUNCHES
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/2 allure testops.png" alt="" border="0"></a>

DEFAULT DASHBOARD
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/3 allure testops.png" alt="" border="0"></a>

SUITES
<br >
<a href="https://ibb.co/TvwPjzj"><img src="images/4 allure testops.png" alt="" border="0"></a>
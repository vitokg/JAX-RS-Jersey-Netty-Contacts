
REST API
------------------------------
ТЕХСТЕК:

JAX-RS - специфікація API Jakarta EE, яка стандартизує
розробку та розгортання RESTful Web-сервісів за допомогою
технологій Java та JEE, відповідно до архітектурного шаблону
REST (REpresentational State Transfer).

Jersey RESTful Web Services Framework для розробки
RESTful Web Services на Java
https://projects.eclipse.org/projects/ee4j.jersey/

Jackson процесор JSON, який використовується для
маршалінгу/серіалізації та демаршалінгу/десеріалізації
об’єктів із Java у JSON і навпаки.
Jersey використовує Jackson внутрішньо.

Серіалізація — це процес перетворення об’єкта даних —
комбінації коду й даних, представлених в області зберігання
даних — у ряд байтів, який зберігає стан об’єкта у формі,
яку легко передавати. Десеріалізація - зворотній процес.

Такі формати даних, як JSON і XML, часто використовуються як
формат для зберігання серіалізованих даних.

Netty - NIO клієнт-серверний фреймворк, який дозволяє
розробляти мережеві програми, такі як протокольні сервери
та клієнти.
https://netty.io/

HK2 - фреймворк для впровадження залежностей.
https://javaee.github.io/hk2/

MySQL
https://www.mysql.com/

Hibernate
https://hibernate.org/

Lombok
https://projectlombok.org/

------------------------------

1) Визначаємо об'єкти (сутності) реального світу.
На основі цих об'єктів сформуємо таблиці БД та
моделі Java-класів

2) Складаємо SQL-запити SQLs.sql.

3) Створюємо Maven-проект.
Підтягуємо залежності (pom.xml).

4) Формуємо пакети, класи.

5) Запускаємо
org.example.app.App

6) Для тестування REST API, запускаємо
Postman (https://www.postman.com/)

7) Тестуємо REST API
INFO/REST_API_User.txt
INFO/REST_API_Product.txt


ЦІКАВЕ:
https://stackoverflow.com/questions/11552248/when-to-use-queryparam-vs-pathparam


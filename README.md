# HW5, HW6, HW7

## Product Service (в директории hw5)

В данном сервисе реализованы CRUD для сущности Product (поля: name, price). Переменные окружения настроены через конфигурацию запуска Intellij IDEA.

*Демонстрация:*

1. GET запрос (получение списка всех продуктов)
<img width="451" height="633" alt="Снимок экрана 2026-01-16 в 16 02 31" src="https://github.com/user-attachments/assets/40640272-0333-44bf-a089-87a85167e0ff" />

2. POST запрос (создание продукта)
<img width="383" height="440" alt="Снимок экрана 2026-01-16 в 16 04 44" src="https://github.com/user-attachments/assets/dc223888-8406-4988-aef4-6a7e2d56d840" />

3. GET запрос (получение продукта по id)
<img width="384" height="365" alt="Снимок экрана 2026-01-16 в 16 05 27" src="https://github.com/user-attachments/assets/ea7c575e-e4c2-404f-be6d-ec78d02e71ae" />

4. PUT запрос (изменение продукта по id)
<img width="367" height="426" alt="Снимок экрана 2026-01-16 в 16 06 45" src="https://github.com/user-attachments/assets/5a16c322-db67-418a-b885-d6bb01866828" />

5. DELETE запрос (удаление продукта по id)
<img width="765" height="286" alt="Снимок экрана 2026-01-16 в 16 07 08" src="https://github.com/user-attachments/assets/06c10fdd-fdab-46e3-84cd-e671fb85a9c9" />

## Delivery Service (в директории hw6)

В данном сервисе реализована сущность Delivery (поля: productId, address). Переменные окружения настроены через конфигурацию запуска Intellij IDEA. Настроено взаимодействие сервисов через OpenFeign.
Данный сервис создает новую доставку по id продукта и адресу. Сервис обращается на Product Service, чтобы проверить, существует ли продукт с переданным id.

*Демонстрация:*

POST запрос (создание доставки)
<img width="370" height="435" alt="Снимок экрана 2026-01-16 в 16 12 40" src="https://github.com/user-attachments/assets/781b213b-5179-4f73-848b-61af7f6599cd" />

## Домашнее задание 7 - добавление Basic Auth в Product Service (в директории hw5)

В зависимости приложения добавлен spring security. Через переменные окружения был настроен пользователь с username и password.
Добавлени security config с описанием правил доступа к ресурсам.

*Демонстрация:*

1. Если дать не правильный креденшалы, то запрос не пройдет и вернется статус 401 Unauthorized:
<img width="983" height="450" alt="Снимок экрана 2026-01-16 в 16 18 04" src="https://github.com/user-attachments/assets/67f92780-3c1d-4e8c-966a-47b438f4a8b8" />

2. Если данные правильные, то запрос проходит как надо:
<img width="961" height="745" alt="Снимок экрана 2026-01-16 в 16 19 04" src="https://github.com/user-attachments/assets/0d29f198-ce76-4ad8-8d3e-b892b209913b" />

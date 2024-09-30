# servlet-reactive-benchmark
Benchmarking servlet, reactive and coroutine spring web applications

Основные способы обработки запросов микросервисами: servlet, projectReactor или coroutins. Чтобы разобраться в эффектиновсти использования каждого из этих подходов были написаны ms с двумя запросами: post на запись в таблицу и get на получение данных этой таблицы. Для проведения нагрузочного тестирования использовалось приложение jmeter.

    Первая нагрузка: 25 запросов в 5 секунд (по 5 запросов в секунду).
Результат сервиса servlet-app: среднее на выполнения post - 3 мсек, get - 1 мсек, ошибок: 0%.
![image](https://github.com/user-attachments/assets/cad04098-3c68-4811-b53e-9410676c149b)

Результат сервиса coroutine-app: среднее на выполнения post - 9 мсек, get - 4 мсек, ошибок: 0%.
![image](https://github.com/user-attachments/assets/94182ae3-2332-4d8e-ba7c-741d41f77345)

Результаты сервиса reactive-app: среднее на выполнения post - 4 мсек, get - 2 мсек, ошибок: 0%.
![image](https://github.com/user-attachments/assets/a8bcea9c-174a-4606-a882-c81a28d1013d)

С небольшой нагрузкой справились все сервисы, каждый запрос отработал корректно, однако быстрее всех обрабатывал запросы servlet-app, а медленее - coroutine-app.

    Вторая нагрузка: 500 запросов в 10 секунд (по 50 запросов в секунду).
Результат сервиса servlet-app: среднее на выполнения post - 2 мсек, get - 5 мсек, ошибок: 0%.
![image](https://github.com/user-attachments/assets/0c209adb-ea4a-4aec-b427-329c6f08ca72)

Результат сервиса coroutine-app: среднее на выполнения post - 2 мсек, get - 7 мсек, ошибок: 0%.
![image](https://github.com/user-attachments/assets/dbae52a2-8f19-42cb-b26e-359476f6e726)

Результаты сервиса reactive-app: среднее на выполнения post - 3 мсек, get - 11 мсек, ошибок: 0%.
![image](https://github.com/user-attachments/assets/cf1fe1e8-29e0-455f-88c1-148b2a2f7bf4)

С такой нагрузкой также справились все сервисы, каждый запрос отработал корректно, закономерность осталось прежней.

    Третья нагрузка: 10000 запросов в 20 секунд (по 500 запросов в секунду).
Результат сервиса servlet-app: среднее на выполнения post - 23 сек, get - 110 сек, ошибок: 5.12%, записей в бд: 9488.
![image](https://github.com/user-attachments/assets/884fafb5-8222-47b2-85e5-f991ad5dfdc4)
![image](https://github.com/user-attachments/assets/04389b01-f5d1-4500-a128-f8a05cdae7fa)

Результат сервиса coroutine-app: среднее на выполнения post - 1.3 сек, get - 1.6 сек, ошибок: 71%, записей в бд: 2859.
![image](https://github.com/user-attachments/assets/476f1dc7-2d5f-426c-a640-67594b25e646)
![image](https://github.com/user-attachments/assets/032ecc0b-12e7-4948-bc1e-99d24eb2b9a9)

Результаты сервиса reactive-app: среднее на выполнения post - 16 сек, get - 260 сек, ошибок: 29%, записей в бд: 7039.
![image](https://github.com/user-attachments/assets/8fd4207c-99a3-4870-8f48-d9ea2c60b5a6)
![image](https://github.com/user-attachments/assets/82893a20-4544-4934-8400-d6d9d89afef4)

С большой нагрузкой самым выигрышным, на мой взгляд, оказался servlet-app, несмотря на один из самых слабых показателей по скорости, сохранено около 95% запросов. Основная проблема заключалась в резкой нагрузке, что можно увидеть на графике, вначале пропускная способность уверенно увеличивалась, пока сервис не "захлебнулся" количеством запросов, однако достаточно быстро смог возобновить обработку.
coroutine-app обработал менее 30% запросов (на данном сервисе запрос был отправлен дважды, так как в первый раз было тяжело поверить в такие цифры).
reactive-app хоть и обработал большее количество запросов, чем coroutine-app, скорость выполнения запросов не более выигрышна, чем у servlet-app.

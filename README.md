# Pizzeria Microservices

Микросервисное приложение для управления пиццерией. Проект разделён на независимые сервисы, взаимодействующие через HTTP REST. Используется база данных PostgreSQL.

## 📦 Архитектура

Проект состоит из следующих микросервисов:

- **menu-service** — управление меню пиццерии
- **client-service** — работа с клиентами
- **order-service** — обработка заказов
- **kitchen-service** — передача заказов на кухню
- **PostgreSQL** — централизованное хранилище данных (с отдельными схемами для каждого сервиса)

## 🛠️ Технологии

- Java 21 + Spring Boot 3.2
- Spring Web / Spring Data JPA
- PostgreSQL 15
- Gradle
- Docker (опционально)
- REST API
- HikariCP

## 🧱 Структура проекта

```
pizzeria-microservices/
├── client-service/
├── kitchen-service/
├── menu-service/
├── order-service/
└── docker-compose.yml (опционально)
```

## 🚀 Запуск без Docker

1. Убедитесь, что PostgreSQL запущен локально на `localhost:5432`
2. Создайте схемы в базе:
   - `menu_schema`
   - `client_schema`
   - `order_schema`
   - `kitchen_schema`
3. Убедитесь, что логин/пароль `postgres/postgres`
4. Из каждого модуля запустите:

```bash
./gradlew bootRun
```

Проверьте порты:
- menu-service: `localhost:8081`
- client-service: `localhost:8082`
- kitchen-service: `localhost:8083`
- order-service: `localhost:8084`

## 🧪 Тестирование через Postman

1. Создайте коллекцию с запросами:
   - **Добавить клиента:** `POST http://localhost:8082/api/clients`
   - **Добавить пиццу в меню:** `POST http://localhost:8081/api/menu`
   - **Создать заказ:** `POST http://localhost:8084/api/orders`
   - **Посмотреть заказы:** `GET http://localhost:8084/api/orders`

2. Пример запроса:
```json
POST /api/orders
{
  "clientId": 1,
  "items": [
    { "menuItemId": 2, "quantity": 1 }
  ]
}
```

## 🧩 Использование DBeaver

1. Подключитесь к базе PostgreSQL
2. Выберите нужную схему (`menu_schema`, `client_schema`, ...)
3. Проверьте таблицы:
   - `menu_items`, `clients`, `orders`, `order_items` и т.д.
4. Отслеживайте изменения данных после выполнения REST-запросов

## 📊 Демонстрация

1. Заполните базу с помощью Postman
2. Покажите структуру данных в DBeaver
3. Проследите ход создания заказа:
   - клиент → заказ → кухня

## 📌 Возможности

- Создание и просмотр меню
- Регистрация клиентов
- Оформление заказов
- Передача заказов на кухню
- Работа с базой через REST API
- Хранение данных в отдельных схемах PostgreSQL

## 📷 Архитектурная схема

![Архитектура](docs/architecture.png)

## 📝 Автор

Проект создан как учебный пример микросервисной архитектуры в Java.

# Проект "Explore With Me"

## Стэк
Java, Spring Boot, Hibernate, Docker, Maven

## Описание
Бэкенд сервиса-афиши, где можно предложить какое-либо событие от выставки до похода в кино и набрать компанию для участия в нём.

## Реализовано
* Микросервисная архитектура - модуль "stats" для сбора статистики и модуль "main" для основного функционала
* Размещение и просмотр событий
* Объединение событий в подборки, закрепление их на главной странице
* Поиск событий с возможностью фильтрации
* Категории событий
* Сбор статистики просмотров событий
* Возможность ставить событиям оценки - лайки и дизлайки <i>(Дополнительная функциональность)</i>
# API

## Основная функциональность
* <strong>ewm-main-service-spec.json</strong> - Спецификация основного сервиса
* <strong>ewm-stats-service-spec.json</strong> - Спецификация сервиса статистики

## Дополнительная функциональность
### PATCH /users/{userId}/events/{eventId}/like - Поставить событию лайк
* Пользователь может поставить только одну оценку событию. 
* Пользователь должен был посетить событие.
* Если ранее пользователь ставил событию дизлайк - оценка меняется на лайк
* Если ранее пользователь ставил событию лайк - оценка пользователя уберётся
### PATCH /users/{userId}/events/{eventId}/dislike - Поставить событию дизлайк
* Пользователь может поставить только одну оценку событию.
* Пользователь должен был посетить событие.
* Если ранее пользователь ставил событию лайк - оценка меняется на дизлайк 
* Если ранее пользователь ставил событию дизлайк - оценка пользователя уберётся

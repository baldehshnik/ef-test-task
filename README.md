# Введение
Данное техническое задание предназначено для Junior Android Developers, которые будут разрабатывать мобильное приложение для поиска обучающих курсов по программированию на платформе Android. 
Приложение должно помочь пользователям находить подходящие курсы, фильтровать их по различным параметрам и отслеживать прогресс обучения.

# Цель разработки
Разработать мобильное приложение для Android, которое позволяет находить обучающие курсы для ИТ-специалистов, получать рекомендации, отслеживать прогресс в обучении и получать информацию о новых курсах.

# Основные требования

## Пользовательский интерфейс (UI)

**Меню:**

- Меню должно отображаться на всех экранах приложения и состоять из следующих кнопок:
  - **Главная** - переход к главному экрану
  - **Избранное** - переход в раздел избранного
  - **Аккаунт** - переход к экрану с заглушкой

**Главный экран:**

- Экран не имеет заголовка.
- Поле для поиска, с иконкой лупы и текстом "Search courses" в качестве подсказки. Элемент является нефункциональным.
- Список курсов с основными данными: название, краткое описание, цена, рейтинг, дата добавления.
- Фильтры для поиска:
  - Категория (например, Kotlin, Android SDK, UI/UX)
  - Уровень сложности (Beginner, Intermediate, Advanced)
  - Цена (бесплатный/платный)
- Возможность сортировки курсов по:
  - Популярности
  - Рейтингу
  - Дате добавления
 
![home](https://github.com/user-attachments/assets/961bf562-e50f-43c7-b07e-073837429d96)


**Экран курса:**

- Подробная информация о курсе:
  - Описание
  - Автор
  - Продолжительность
  - Стоимость
  - Рейтинг
  - Отзывы
- Кнопка "Перейти на платформу" - переводит на внешнюю ссылку курса.
- Кнопка "Добавить в избранное" - добавляет курс в раздел избранных.
- Статичный текст "О курсе" отображается для всех курсов.
- Описание берется с платформы Stepik.

![details](https://github.com/user-attachments/assets/41977455-8b79-4a44-958d-8288238c1cbf)


**Экран избранного:**

- Список курсов, добавленных в избранное.
- Если курс добавлен в избранное, его иконка окрашивается в зеленый цвет, иначе остается без заливки с белым контуром.
- Кнопка "Подробнее" открывает карточку курса.
- 
![favorite](https://github.com/user-attachments/assets/e5d302a1-d3ff-410e-a320-fa86f2a871bc)

---

## Использованный технологический стек
- **Android:**
  - **Kotlin**
  - **Coroutine**
  - **Flow**, **LiveData**
  - **Hilt**
  - **MVVM**
  - **Clean Architecture**
  - **Многомодульность**
  - **Room**
  - **Retrofit**

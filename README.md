# Описание проекта
Проект представляет собой веб-приложение, разработанное с использованием Java и ряда популярных инструментов и технологий для создания современных веб-приложений:
- Spring Boot, Spring Web, Spring Validation;
- PostgreSQL, JDBC, Hibernate, Spring JPA;
- Thymeleaf, Bootstrap.

## Пользователь Библиотеки:
### Список пользователей:
http://localhost:9999/people
<img width="1912" alt="Screenshot 2024-05-14 at 23 15 37" src="https://github.com/devolw/library-project-boot/assets/104515806/811e7e14-f111-4f4d-8dbb-9b3adae81a76">


### Создание нового пользователя:
http://localhost:9999/people/new
<img width="1912" alt="Screenshot 2024-05-14 at 23 24 47" src="https://github.com/devolw/library-project-boot/assets/104515806/98d70f86-bfd7-41bc-9f1e-755a1eccb2d9">
#### Валидация полей формы создания новго пользователя:
##### Валидация ФИО:
<img width="1912" alt="Screenshot 2024-05-14 at 23 38 56" src="https://github.com/devolw/library-project-boot/assets/104515806/b00d706e-0ff8-4716-8c61-fac85e707207">

##### Валидация возраста:
<img width="1912" alt="Screenshot 2024-05-14 at 23 38 34" src="https://github.com/devolw/library-project-boot/assets/104515806/077bd881-7f40-47c5-9c9f-ba57268205f6">

##### Валидация существующего пользователя:
<img width="1912" alt="Screenshot 2024-05-14 at 23 38 41" src="https://github.com/devolw/library-project-boot/assets/104515806/967843af-a6ee-4c60-82e4-7bc4c6138969">


### Страница пользователя:
http://localhost:9999/people/{id}
#### Пользователь не взявший книгу:
<img width="1912" alt="Screenshot 2024-05-14 at 23 26 08" src="https://github.com/devolw/library-project-boot/assets/104515806/927cabdc-b925-49ce-b1bd-b0289540e431">

#### Список книг пользователя(Книга отображается зеленым цветом - не просрочена):
<img width="1912" alt="Screenshot 2024-05-14 at 23 24 06" src="https://github.com/devolw/library-project-boot/assets/104515806/cc52e828-034a-4e83-bad9-2c2596492fdd">

#### Список книг пользователя(Книга отображается красным цветом - просрочена):
<img width="1912" alt="Screenshot 2024-05-14 at 23 16 08" src="https://github.com/devolw/library-project-boot/assets/104515806/e9a4b7c8-3f4c-4f14-a806-bd3dffe9cff2">




## Книги Библиотеки:
### Список книг:
http://localhost:9999/books
<img width="1912" alt="Screenshot 2024-05-14 at 23 16 58" src="https://github.com/devolw/library-project-boot/assets/104515806/301bacf1-90f7-42bf-8998-fe81d30e2a60">
#### Пагинация - отобразить первую страницу, четыре книги:
http://localhost:9999/books?bookspage=0&books_per_page=4
<img width="1912" alt="bookspage=0 books_per_page=4" src="https://github.com/devolw/library-project-boot/assets/104515806/30598794-bd96-483e-88fe-d460203ea3b4">
#### Пагинация + сортировка по году издания - отобразить первую страницу, четыре книги и отсортировать по году:
http://localhost:9999/books?bookspage=0&books_per_page=4&sort_by_year=true
<img width="1912" alt="bookspage=0 books_per_page=4 sort_by_year=true" src="https://github.com/devolw/library-project-boot/assets/104515806/78af4c18-ca1e-4ec4-889c-ca7be8f98364">
#### Сортировка по году издания книги:
http://localhost:9999/books?sort_by_year=true
<img width="1912" alt="books?sort_by_year=true" src="https://github.com/devolw/library-project-boot/assets/104515806/d243c278-ec6f-4335-bbc9-139e01ff18f0">


## Добавление новой книги:
http://localhost:9999/books/new
<img width="1912" alt="Screenshot 2024-05-14 at 23 19 28" src="https://github.com/devolw/library-project-boot/assets/104515806/c54e39f4-aa20-400c-8604-e4ca886fa00d">
### Валидация полей форма добавления новой книги:
#### Валидация названия книги:
<img width="1912" alt="Screenshot 2024-05-14 at 23 40 52" src="https://github.com/devolw/library-project-boot/assets/104515806/97eddf55-41aa-468a-9b5e-46c8ab6c5fdf">

#### Валидация имени автора книги:
<img width="1912" alt="Screenshot 2024-05-14 at 23 41 21" src="https://github.com/devolw/library-project-boot/assets/104515806/be7ec0bd-e1dd-409e-aede-31a8994d76ee">

#### Валидация года издания книги:
<img width="1912" alt="Screenshot 2024-05-14 at 23 41 35" src="https://github.com/devolw/library-project-boot/assets/104515806/052092dd-f8c3-455d-85b1-46c1ca824937">

#### Тест. Значения всех полей формы введены некорректны:
<img width="1912" alt="Screenshot 2024-05-14 at 23 41 46" src="https://github.com/devolw/library-project-boot/assets/104515806/a15acb27-9a57-40d4-91dc-b581786996bd">


## Страница книги:
http://localhost:9999/books/{id}
<img width="1912" alt="Screenshot 2024-05-14 at 23 30 22" src="https://github.com/devolw/library-project-boot/assets/104515806/e82a3240-08cd-4d31-a24d-da7809f4b447">



## Страница поиска книги:
http://localhost:9999/books/search
### Тест1. Попытка выполнить поиск с пустым запросом:
<img width="1912" alt="Screenshot 2024-05-14 at 23 50 11" src="https://github.com/devolw/library-project-boot/assets/104515806/ddd3a8da-b3da-4983-90a9-9947262650e4">

### Тест2. Поиск книги по одной букве названия:
<img width="1912" alt="Screenshot 2024-05-14 at 23 50 38" src="https://github.com/devolw/library-project-boot/assets/104515806/ef2ae3a4-4733-4da7-9726-a940bf4ad42b">

### Тест3. Поиск книги по полному названию:
<img width="1912" alt="Screenshot 2024-05-14 at 23 51 05" src="https://github.com/devolw/library-project-boot/assets/104515806/9e3b6631-395b-441d-94df-e7e637ed230b">

### Тест4. Поиск книги, которая не добавлена в библиотеку:
<img width="1912" alt="Screenshot 2024-05-14 at 23 51 13" src="https://github.com/devolw/library-project-boot/assets/104515806/5aa3b57a-7295-4bd0-b026-fde07f4e6cc2">
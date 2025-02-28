<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список книг</title>
    <link rel="stylesheet" href="/css/books.css">
</head>
<body>

<nav class="navbar">
    <ul>
        <li><a href="/register">Регистрация</a></li>
        <li><a href="/login">Войти</a></li>
        <li><a href="/books">Главная</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</nav>

<div class="book-container">
    <h1>Список книг</h1>
    <#list books as book>
        <div class="book-card">
            <div class="book-image">
                <img src="${book.image}" alt="Обложка ${book.title}">
            </div>
            <div class="book-info">
                <div class="book-title">${book.title}</div>
                <div class="book-author">${book.author}</div>
                <div class="book-status">${(book.issued)?string("Выдана", "Доступна")}</div>
                 <div class="book-description">${book.description}</div>
                <a href="/book/${book.id}" class="book-link">Подробнее</a>
            </div>
        </div>
    </#list>
</div>

</body>
</html>

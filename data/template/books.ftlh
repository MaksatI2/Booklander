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
        <#if currentUser??>
            <li><a href="/">Главная</a></li>
            <li><a href="/books">Список книг</a></li>
            <li><a href="/employees">Сотрудники</a></li>
            <li><a href="/log">Журнал</a></li>
            <li><a href="/profile">Профиль</a></li>
        <#else>
        <li><a href="/">Главная</a></li>
            <li><a href="/login">Войти</a></li>
            <li><a href="/books">Список книг</a></li>
            <li><a href="/employees">Сотрудники</a></li>
        </#if>
    </ul>
</nav>

<div class="book-container">
    <h1 class="card-header">Список книг</h1>
    <#list books as book>
        <div class="book-card">
            <div class="book-image">
                <img src="${book.image}" alt="Обложка ${book.title}">
            </div>
            <div class="book-info">
                <div class="book-title">${book.title}</div>
                <div class="book-author">${book.author}</div>
                <div class="book-status">${(book.issued)?string("Выдана", "Доступна")}</div>
                 <div class="book-description">${book.shortDescription}</div>
                <a href="/book?id=${book.id}" class="book-link">Подробнее</a>
            </div>
        </div>
    </#list>
</div>

<footer class="footer">
    <div class="footer-content">
        <p>&copy; 2025 Библиотека | Все права защищены</p>
        <p>Контакты: MBook@gmail.com | Телефон: +996 700-27-09-84</p>
        <p>О сайте: Это онлайн-библиотека для сотрудников офиса</p>
    </div>
</footer>

</body>
</html>

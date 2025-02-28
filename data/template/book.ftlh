<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${book.title}</title>
    <link rel="stylesheet" href="/css/book.css">
</head>
<body>

<nav class="navbar">
    <ul>
        <li><a href="/login">Войти</a></li>
        <li><a href="/books">Главная</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</nav>

<div class="book-card">
    <div class="book-image">
         <img src="${book.image}" alt="Обложка ${book.title}">
    </div>
    <div class="book-title">${book.title}</div>
    <div class="book-author">${book.author}</div>
    <div class="book-status">${book.issued?string("Выдана", "Свободна")}</div>
    <#if book.issued>
        <div class="borrower">Кому выдана: ${borrowerName}</div>
    <#else>
        <div class="borrower">Книга свободна</div>
    </#if>
    <a href="/books" class="back-link">Назад к списку</a>
</div>

</body>
</html>

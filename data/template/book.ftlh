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
        <#if currentUser??>
            <li><a href="/books">Главная</a></li>
            <li><a href="/employees">Сотрудники</a></li>
            <li><a href="/profile">Профиль</a></li>
            <li><a href="/logout">Выйти</a></li>
        <#else>
            <li><a href="/login">Войти</a></li>
            <li><a href="/books">Главная</a></li>
            <li><a href="/employees">Сотрудники</a></li>
        </#if>
    </ul>
</nav>

<div class="book-card">
    <div class="book-image">
         <img src="${book.image}" alt="Обложка ${book.title}">
    </div>
    <div class="book-title">${book.title}</div>
    <div class="book-author">${book.author}</div>
    <div class="book-status">${book.issued?string("Выдана", "Свободна")}</div>

    <#if !book.issued>
        <form action="/borrow" method="post">
            <input type="hidden" name="bookId" value="${book.id}">
            <button type="submit" class="back-link">Взять книгу</button>
        </form>
    <#else>

        <#if currentUser?? && currentUser.borrowedBooks?? && currentUser.borrowedBooks?seq_contains(book.id)>
            <form action="/return" method="post">
                <input type="hidden" name="bookId" value="${book.id}">
                <button type="submit" class="back-link return-button">Вернуть книгу</button>
            </form>
        </#if>
    </#if>
    <a href="/books" class="back-link">Назад к списку</a>
</div>

</body>
</html>

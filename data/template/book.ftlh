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
    <div class="book-header">
        <h1 class="book-title">${book.title}</h1>
    </div>
    <div class="book-content">
        <div class="book-image">
            <img src="${book.image}" alt="Обложка ${book.title}">
        </div>
        <div class="book-info">
            <p><strong>Автор:</strong> ${book.author}</p>
            <p><strong>Год издания:</strong> ${book.age}</p>
            <div class="book-description">${(book.description)!"Нет полного описания"}</div>
            <div class="book-status">Статус: ${book.issued?string("Выдана", "Свободна")}</div>
            <#if borrowLimit?? && borrowLimit == "exceeded">
            <div class="error-message">
                <p>Вы не можете взять больше двух книг одновременно.</p>
            </div>
            </#if>
            <form action="${book.issued?then('/return', '/borrow')}" method="post">
                <input type="hidden" name="bookId" value="${book.id}">
                <#if book.issued>
                    <#if currentUser?? && currentUser.borrowedBooks?? && currentUser.borrowedBooks?seq_contains(book.id)>
                        <button type="submit" class="return-button">Вернуть книгу</button>
                    </#if>
                <#else>
                    <button type="submit" class="borrow-button">Взять книгу</button>
                </#if>
            </form>
        </div>
    </div>
    <div class="book-footer">
        <a href="/books" class="back-link">Назад к списку</a>
    </div>
</div>

</body>
</html>

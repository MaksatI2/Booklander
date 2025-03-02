<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Информация о сотруднике</title>
     <link rel="stylesheet" href="/css/employee.css">
</head>
<body>

<nav class="navbar">
    <ul>
        <#if currentUser??>
        <li><a href="/">Главная</a></li>
            <li><a href="/books">Список книг</a></li>
            <li><a href="/employees">Сотрудники</a></li>
            <li><a href="/profile">Профиль</a></li>
        <#else>
        <li><a href="/">Главная</a></li>
            <li><a href="/login">Войти</a></li>
            <li><a href="/books">Список книг</a></li>
            <li><a href="/employees">Сотрудники</a></li>
        </#if>
    </ul>
</nav>

<div class="content">
    <h1>Информация о сотруднике</h1>
    <h2>Имя: ${employee.name}</h2>

    <h2>Текущие книги:</h2>
    <div class="book-container">
        <#list borrowedBooks as book>
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

    <h2>Прошлые книги:</h2>
    <div class="book-container">
        <#list pastBooks as book>
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

    <a href="/employees" class="back-link">Назад к списку</a>
</div>

</body>
</html>

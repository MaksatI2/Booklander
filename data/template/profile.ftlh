<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль пользователя</title>
    <link rel="stylesheet" href="/css/profile.css">
</head>
<body>

<nav class="navbar">
    <ul>
        <li><a href="/">Главная</a></li>
        <li><a href="/books">Список книг</a></li>
        <li><a href="/employees">Сотрудники</a></li>
        <li><a href="/log">Журнал</a></li>
    </ul>
</nav>

<div class="profile-container">
    <h1>Профиль пользователя</h1>
    <p><strong>Имя:</strong> ${employee.name}</p>
    <p><strong>Email:</strong> ${employee.email}</p>

    <h2>Взятые книги:</h2>
    <ul>
        <#if borrowedBooks?has_content>
            <#list borrowedBooks as book>
                <li>${book.title} (${book.author})</li>
        <form action="${book.issued?then('/return', '/borrow')}" method="post">
            <input type="hidden" name="bookId" value="${book.id}">
            <#if book.issued>
            <#if currentUser?? && currentUser.borrowedBooks?? && currentUser.borrowedBooks?seq_contains(book.id)>
            <button type="submit" class="button borrow-action">Вернуть книгу</button>
        </#if>
    </#if>
    </form>
            </#list>
        <#else>
            <p>У вас нет взятых книг.</p>
        </#if>
    </ul>

    <h2>История прочитанных книг:</h2>
        <ul>
            <#if pastBooks?has_content>
                <#list pastBooks as book>
                    <li>${book.title} (${book.author})
                        <form action="${book.issued?then('/return', '/borrow')}" method="post">
                            <input type="hidden" name="bookId" value="${book.id}">
                            <#if book.issued>
                                <#if currentUser?? && currentUser.borrowedBooks?? && currentUser.borrowedBooks?seq_contains(book.id)>
                            </#if>
                            <#else>
                                <button type="submit" class="button borrow-action">Взять книгу</button>
                            </#if>
                         </form>
                    </li>

                </#list>
            <#else>
                <p>Вы еще не возвращали книги.</p>
            </#if>
        </ul>

<form action="/logout" method="post">
    <button type="submit" class="back-link">Выйти из аккаунта</button>
</form>
</div>

</body>
</html>

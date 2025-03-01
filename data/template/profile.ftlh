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
        <li><a href="/books">Главная</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</nav>

<div class="profile-container">
    <h1>Профиль пользователя</h1>
    <p><strong>ID:</strong> ${employee.id}</p>
    <p><strong>Имя:</strong> ${employee.name}</p>
    <p><strong>Email:</strong> ${employee.email}</p>

    <h2>Взятые книги:</h2>
    <ul>
        <#if borrowedBooks?has_content>
            <#list borrowedBooks as book>
                <li>${book.title} (${book.author})</li>
            </#list>
        <#else>
            <p>У вас нет взятых книг.</p>
        </#if>
    </ul>

    <h2>История возвратов:</h2>
        <ul>
            <#if pastBooks?has_content>
                <#list pastBooks as book>
                    <li>${book.title} (${book.author})</li>
                </#list>
            <#else>
                <p>Вы еще не возвращали книги.</p>
            </#if>
        </ul>

    <a href="/login" class="back-link">Выйти</a>
</div>

</body>
</html>

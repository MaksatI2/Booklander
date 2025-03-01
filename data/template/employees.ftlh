<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список сотрудников</title>
    <link rel="stylesheet" href="/css/employees.css">
</head>
<body>

<nav class="navbar">
    <ul>
        <li><a href="/login">Войти</a></li>
        <li><a href="/books">Главная</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</nav>

<h1 class="employee-container-header">Список сотрудников</h1>
<div class="employee-container">
    <#list employees as employee>
    <div class="employee-card">
        <div class="employee-info">
            <div class="employee-name">${employee.name}</div>
            <div class="employee-id">ID: ${employee.id}</div>
            <h3>Нынешние книги:</h3>
            <div class="employee-books">
                <#assign books = borrowedBooksMap[employee.id]![]>
                <#if books?size == 0>
                Нет взятых книг
                <#else>
                <ul>
                    <#list books as bookTitle>
                    <li>${bookTitle}</li>
                </#list>
                </ul>
            </#if>
        </div>
        <h3>Прошлые книги:</h3>
        <div class="employee-books">
            <#assign books = pastBooksMap[employee.id]![]>
            <#if books?size == 0>
            Нет прошлых книг
            <#else>
            <ul>
                <#list books as bookTitle>
                <li>${bookTitle}</li>
            </#list>
            </ul>
        </#if>
    </div>
    <a href="/employee/${employee.id}" class="employee-link">Инфо о сотруднике</a>
</div>
</div>
</#list>
</div>

</body>
</html>

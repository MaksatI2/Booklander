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

<h1 class="employee-container-header">Список сотрудников</h1>
<div class="employee-container">
    <#list employees as employee>
    <div class="employee-card">
        <div class="employee-info">
            <div class="employee-name">${employee.name}</div>
            <h3>Текущие книги:</h3>
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
    </div>
    <a href="/employee?id=${employee.id}" class="employee-link">Инфо о сотруднике</a>
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

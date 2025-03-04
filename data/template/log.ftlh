<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Журнал выдачи книг</title>
    <link rel="stylesheet" href="/css/log.css">
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

<div class="log-container">
    <h1 class="log-header">Журнал выдачи</h1>
    <table class="log-table">
        <thead>
            <tr>
                <th>Книга</th>
                <th>Сотрудник</th>
                <th>Дата выдачи</th>
                <th>Дата возврата</th>
            </tr>
        </thead>
        <tbody>
            <#list logs as log>
                <tr>
                    <td>${log.bookName}</td>
                    <td>${log.employeeId}</td>
                    <td>${log.borrowDate}</td>
                    <td>${log.returnDate?default("—")}</td>
                </tr>
            </#list>
        </tbody>
    </table>
</div>


</body>
</html>

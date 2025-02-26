<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список сотрудников</title>
    <style>
        /* Общие стили */
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #6a11cb 0%, #2575fc 100%);
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }

        /* Навигационное меню */
        header {
            width: 100%;
            background: #333;
            padding: 15px;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 10;
            display: flex;
            justify-content: center;
        }

        header ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            gap: 20px;
        }

        header ul li {
            display: inline;
        }

        header ul li a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            padding: 8px 12px;
        }

        header ul li a:hover {
            background: #555;
            border-radius: 5px;
        }

        /* Заголовок страницы */
        .employee-container-header {
            font-size: 30px;
            font-weight: bold;
            text-align: center;
            color: #333;
            margin-top: 80px; /* Отступ сверху, чтобы не перекрывалось с меню */
            margin-bottom: 40px;
        }

        /* Контейнер сотрудников */
        .employee-container {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 30px;
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        /* Карточка сотрудника */
        .employee-card {
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        /* Информация о сотруднике */
        .employee-info {
            flex-grow: 1;
        }

        .employee-name {
            font-size: 20px;
            font-weight: bold;
            color: #333;
        }

        .employee-id {
            font-size: 16px;
            color: #666;
        }

        .employee-books {
            font-size: 14px;
            color: #4CAF50;
        }

        /* Ссылка на информацию о сотруднике */
        .employee-link {
            display: inline-block;
            padding: 10px 20px;
            background: #2575fc;
            color: white;
            border-radius: 25px;
            text-decoration: none;
            font-weight: bold;
            text-align: center;
            transition: background-color 0.3s ease;
        }

        .employee-link:hover {
            background-color: #1e60d2;
        }
    </style>
</head>
<body>

<header>
    <ul>
        <li><a href="/books">Список книг</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</header>

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

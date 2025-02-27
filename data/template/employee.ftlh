<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Инфо о сотруднике</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: white;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }

        .navbar {
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

        .navbar ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            gap: 20px;
        }

        .navbar ul li {
            display: inline;
        }

        .navbar ul li a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            padding: 8px 12px;
        }

        .navbar ul li a:hover {
            background: #555;
            border-radius: 5px;
        }

        h1 {
            font-size: 30px;
            font-weight: bold;
            color: #333;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 22px;
            color: #333;
            margin-top: 20px;
        }

        .book-card {
            background: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            text-align: center;
            margin-top: 100px;
        }

        ul {
            list-style-type: none;
            padding-left: 0;
            text-align: left;
            margin-bottom: 20px;
        }

        ul li {
            font-size: 16px;
            margin: 5px 0;
            padding: 8px;
            border-radius: 5px;
        }

        .back-link {
            display: inline-block;
            padding: 12px 20px;
            background: #2575fc;
            color: white;
            border-radius: 25px;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .back-link:hover {
            background-color: #1e60d2;
        }
    </style>
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
    <h1>Имя:${employee.name}</h1>
    <h2>Email:${employee.email}</h2>

    <h2>Текущие книги:</h2>
    <ul>
        <#list borrowedBooksMap[employee.id] as book>
            <li>${book}</li>
        </#list>
    </ul>

    <h2>Прошлые книги:</h2>
    <ul>
        <#list pastBooksMap[employee.id] as book>
            <li>${book}</li>
        </#list>
    </ul>

    <a href="/employees" class="back-link">Назад к списку</a>
</div>

</body>
</html>

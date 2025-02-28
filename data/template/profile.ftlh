<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Профиль пользователя</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: white;
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .profile-container {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px 50px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            width: 100%;
            text-align: center;
        }

        h1 {
            color: #333;
            font-size: 30px;
            margin-bottom: 20px;
            font-weight: bold;
            letter-spacing: 1px;
        }

        h2 {
            color: #333;
            font-size: 22px;
            margin-top: 20px;
            margin-bottom: 10px;
        }

        p {
            font-size: 16px;
            margin: 10px 0;
        }

        strong {
            font-weight: bold;
        }

        ul {
            list-style-type: none;
            padding-left: 0;
            text-align: left;
            margin-bottom: 20px;
        }

        ul li {
            font-size: 16px;
            background-color: #f2f2f2;
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
            margin-top: 20px;
            transition: background-color 0.3s ease;
        }

        .back-link:hover {
            background-color: #1e60d2;
        }

        .navbar {
            background: #333;
            padding: 15px;
            width: 100%;
            position: absolute;
            top: 0;
            left: 0;
        }

        .navbar ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            display: flex;
            gap: 15px;
            justify-content: center;
        }

        .navbar ul li {
            display: inline;
        }

        .navbar ul li a {
            text-decoration: none;
            font-size: 16px;
            padding: 8px 12px;
        }

        .navbar ul li a:hover {
            background: #555;
            border-radius: 5px;
        }
    </style>
</head>
<body>

<nav class="navbar">
    <ul>
        <li><a href="/register">Регистрация</a></li>
        <li><a href="/profile">Профиль</a></li>
        <li><a href="/books">Главная</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</nav>

<div class="profile-container">
    <h1>Профиль пользователя</h1>
    <p><strong>ID:</strong> ${employee.id}</p>
    <p><strong>Имя:</strong> ${employee.name}</p>
    <p><strong>Email:</strong> ${employee.email}</p>

    <h2>Текущие книги:</h2>
    <ul>
        <#list borrowedBooks as book>
        <li>${book}</li>
        </#list>
    </ul>

    <h2>Прошлые книги:</h2>
    <ul>
        <#list pastBooks as book>
        <li>${book}</li>
        </#list>
    </ul>

    <a href="/login" class="back-link">Выйти</a>
</div>

</body>
</html>

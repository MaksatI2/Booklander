<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список книг</title>
    <style>
        body {
            font-family: Arial, sans-serif;
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

        .book-container {
            display: flex;
            flex-direction: column;
            gap: 15px;
            max-width: 1000px;
            margin: 120px auto 0;
            padding-top: 20px;
        }

        .book-card {
            background: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            display: flex;
            gap: 20px;
        }

        .book-image img {
            width: 180px;
            height: 220px;
            border-radius: 5px;
        }

        .book-info {
            flex-grow: 1;
        }

        .book-title {
            font-size: 20px;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .book-author {
            color: #EFB060;
            font-size: 14px;
            margin-bottom: 10px;
        }

        .book-status {
            font-size: 14px;
            color: green;
            margin-bottom: 10px;
        }

        .book-description {
            font-size: 14px;
            color: #555;
        }

        .book-link {
            display: inline-block;
            margin-top: 10px;
            color: blue;
            text-decoration: none;
            font-weight: bold;
        }

        .book-link:hover {
            text-decoration: underline;
        }

        .book-description {
            font-size: 18px;
            color: #555;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>

<nav class="navbar">
    <ul>
        <li><a href="/register">Регистрация</a></li>
        <li><a href="/login">Войти</a></li>
        <li><a href="/books">Главная</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</nav>

<div class="book-container">
    <h1>Список книг</h1>
    <#list books as book>
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

</body>
</html>

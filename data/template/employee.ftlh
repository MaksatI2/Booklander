<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Инфо о сотруднике</title>
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
        h1 {
            font-size: 30px;
            font-weight: bold;
            color: #333;
            margin-bottom: 20px;
        }

        /* Карточка информации о сотруднике */
        .book-card {
            background: rgba(255, 255, 255, 0.9);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            text-align: center;
            margin-top: 100px; /* Отступ сверху, чтобы не перекрывалось с меню */
        }

        /* Заголовки для списка книг */
        h2 {
            font-size: 22px;
            color: #333;
            margin-top: 20px;
        }

        /* Список книг */
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

        /* Ссылка на возврат */
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

<header>
    <ul>
        <li><a href="/books">Список книг</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
</header>

<div class="book-card">
    <h1>${employee.name}</h1>

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

    <a href="/employees" class="back-link">Назад к списку</a>
</div>

</body>
</html>

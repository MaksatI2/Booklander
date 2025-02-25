<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список книг</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f9f9f9; padding: 20px; }
        .book-container { display: flex; flex-direction: column; gap: 15px; max-width: 1000px; margin: auto; }
        .book-card { background: white; padding: 15px; border-radius: 10px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); display: flex; gap: 15px; }
        .book-image img { width: 180px; height: 220px; border-radius: 5px; }
        .book-info { flex-grow: 1; }
        .book-title { font-size: 20px; font-weight: bold; margin-bottom: 5px; }
        .book-author { color: #EFB060; font-size: 14px; margin-bottom: 10px; }
        .book-status { font-size: 14px; color: green; margin-bottom: 10px; }
        .book-description { font-size: 14px; color: #555; }
        .book-link { display: block; margin-top: 10px; color: blue; text-decoration: none; font-weight: bold; }
        ul { list-style-type: none; margin: 0; padding: 0;}
    </style>
</head>
<body>
<header>
    <ul>
        <li><a href="/employees">Employees</a></li>
    </ul>
</header>
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
                <a href="/book/1" class="book-link">Подробнее</a>
            </div>
        </div>
    </#list>
</div>
</body>
</html>

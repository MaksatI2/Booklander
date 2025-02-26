<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Инфо о сотруднике</title>
    <style>
        body { font-family: Arial, sans-serif;
          background: #f9f9f9;
          padding: 20px;
          display: flex;
          justify-content: center;
         }
        .book-card { background: white;
          padding: 20px;
          border-radius: 10px;
          box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
          max-width: 500px;
          text-align: center;
         }
        .book-title { font-size: 25px; font-weight: bold; margin-bottom: 10px; }
        .back-link {
          display: block;
          margin-top: 20px;
          color: blue;
          text-decoration: none;
          font-weight: bold;
         }
    </style>
</head>
<body>
<header>
    <ul>
        <li><a href="/books">Список книг</a></li>
        <li><a href="/employees">Сотрудники</a></li>
    </ul>
<header>
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




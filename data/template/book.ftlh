<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>${book.title}</title>
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
        .book-image img { width: 250px; height: 300px; border-radius: 5px; margin-bottom: 10px; }
        .book-title { font-size: 25px; font-weight: bold; margin-bottom: 10px; }
        .book-author { color: #d9534f; font-size: 20px; margin-bottom: 10px; }
        .book-status { font-size: 16px; font-weight: bold; margin-bottom: 10px; }
        .book-description { font-size: 14px; color: #555; margin-bottom: 10px; }
        .borrower { font-size: 14px; color: #333; }
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
</header>
<div class="book-card">
    <div class="book-image">
         <img src=${book.image} alt="Обложка ${book.title}">
    </div>
    <div class="book-title">${book.title}</div>
    <div class="book-author">${book.author}</div>
    <div class="book-status">${book.issued?string("Выдана", "Свободна")}</div>
    <#if book.issued>
        <div class="borrower">Кому выдана: ${borrowerName}</div>
    <#else>
        <div class="borrower">Книга свободна</div>
    </#if>
    <a href="/books" class="back-link">Назад к списку</a>
</div>
</body>
</html>




<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Список сотрудников</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f9f9f9; padding: 20px; }
        .employee-container-header {
            margin: 30px;
            gap: 15px;
            max-width: 1000px;
            padding: 15px;
        }
        .employee-container { display: grid; grid-template-columns: repeat(3, 1fr);
            gap: 15px;
            max-width: 1200px;
            margin: auto;
        }
        .employee-card { background: white; padding: 15px;
            border-radius: 10px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        .employee-info { flex-grow: 1;}
        .employee-name { font-size: 18px; font-weight: bold; margin-bottom: 5px;}
        .employee-id { font-size: 14px;margin-bottom: 10px;}
        .employee-books {font-size: 14px; color: green; margin-bottom: 10px;}
        .employee-link {
            display: block;
            margin-top: 10px;
            color: blue;
            text-decoration: none;
            font-weight: bold;
        }
    </style>
</head>
<body>
<ul>
    <li><a href="/books">Book List</a></li>
</ul>
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
                <a href="/employee/01" class="employee-link">Инфо о сотруднике</a>
            </div>
        </div>
    </#list>
</div>
</body>
</html>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <link rel="stylesheet" href="/css/register.css">
</head>
<body>

<div class="form-container">
    <h2>Регистрация сотрудника</h2>
    <form action="/register" method="post">
        <label for="email">Email (идентификатор):</label>
        <input type="email" id="email" name="email" required>

        <label for="name">Имя:</label>
        <input type="text" id="name" name="name" required>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required>

        <button type="submit">Зарегистрироваться</button>
    </form>

    <p>${message!}</p>
    <a href="/books">На главную</a>
</div>

</body>
</html>

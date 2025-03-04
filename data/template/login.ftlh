<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Вход в систему</title>
  <link rel="stylesheet" href="/css/login.css">
</head>
<body>
<div class="form-container">
  <h1>Вход в систему</h1>
  <form method="POST" action="/login">
    <div class="input-container">
      <i class="fa fa-user"></i>
      <input type="text" name="email" placeholder="Идентификатор" required><br>
    </div>
    <div class="input-container">
      <i class="fa fa-lock"></i>
      <input type="password" name="password" placeholder="Пароль" required><br>
    </div>
    <input type="submit" value="Войти">
        <div class="btn"><a href="/books">На главную</a></div>
        <div class="btn"><p>У вас нет аккаунта?</p><a href="/register">Зарегистрироваться</a><div>

  </form>

  <#if errorMessage??>
  <p class="error-message">${errorMessage}</p>
  </#if>
</div>
</body>
</html>

<!DOCTYPE html>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Вход в систему</title>
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

    .form-container {
      background: rgba(255, 255, 255, 0.9);
      padding: 40px 50px;
      border-radius: 15px;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
      max-width: 400px;
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

    input[type="text"], input[type="password"] {
      width: 100%;
      padding: 12px 15px;
      margin: 10px 0;
      border: 1px solid #ddd;
      border-radius: 25px;
      font-size: 16px;
      box-sizing: border-box;
      transition: all 0.3s ease;
    }

    input[type="submit"] {
      width: 100%;
      padding: 12px;
      background: #2575fc;
      color: white;
      border: none;
      border-radius: 25px;
      cursor: pointer;
      font-size: 18px;
      transition: background-color 0.3s ease;
    }

    input[type="submit"]:hover {
      background-color: #1e60d2;
    }

    .error-message {
      color: #e74c3c;
      font-size: 14px;
      margin-top: 10px;
    }

    .input-container {
      position: relative;
    }

    .input-container i {
      position: absolute;
      left: 10px;
      top: 50%;
      transform: translateY(-50%);
      color: #888;
    }

    input[type="text"], input[type="password"] {
      padding-left: 35px;
    }

    a {
                display: block;
                margin-top: 20px;
                color: #2575fc;
                text-decoration: none;
            }

            a:hover {
                text-decoration: underline;
            }
  </style>
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
        <a href="/books">На главную</a>
  </form>

  <#if errorMessage??>
  <p class="error-message">${errorMessage}</p>
  </#if>
</div>
</body>
</html>

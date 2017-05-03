<!DOCTYPE html>
<html>
<head>
  <#include "header.ftl">
</head>

<body>

  <#include "nav.ftl">

<div class="container">

    <#if authentication == "Succeeded" >
        <p>LOGIN_AUTH_FAILED</p>
    <#elseif authentication == "Failed" >
        <p>LOGIN_AUTH_SUCCEEDED</p>
    <#elseif authentication == "Logout" >
        <p>"LOGIN_LOGGED_OUT"</p>
    </#if>
    <h1>LOGIN PAGE</h1>
    <p>mike : 123</p>
    <form id="loginForm" method="post">
        <label>User Name :</label>
        <input type="text" name="username" placeholder="User Name" value="" required>
        <label>Password :</label>
        <input type="password" name="password" placeholder="Passowrd" value="" required>
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>

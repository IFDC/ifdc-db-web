<!DOCTYPE html>
<html>
<head>
  <#include "header.ftl">
</head>

<body>

  <#include "nav.ftl">

<div class="container">

    <h1>LOGIN PAGE</h1>
    <#if operation_result == "Failed" >
        <p>LOGIN_AUTH_FAILED</p>
    <#elseif operation_result == "Succeeded" >
        <p>LOGIN_AUTH_SUCCEEDED</p>
    </#if>
    <form id="loginForm" method="post">
        <label>User Name :</label>
        <input type="text" name="username" placeholder="User Name" value="" required><br>
        <label>Password :</label>
        <input type="password" name="password" placeholder="Passowrd" value="" required><br>
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>

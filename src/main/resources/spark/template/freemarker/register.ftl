<!DOCTYPE html>
<html>
<head>
  <#include "header.ftl">
</head>

<body>

  <#include "nav.ftl">

<div class="container">

    <h1>REGISTRATION PAGE</h1>
    <#if operation_result == "Failed" >
        <p>REGISTRATION FAILED</p>
    <#elseif operation_result == "Succeeded" >
        <p>REGISTRATION SUCCEEDED</p>
    </#if>
    <form id="loginForm" method="post">
        <label>User Name : </label>
        <input type="text" name="username" placeholder="User Name" value="" required>
        <label>Password : </label>
        <input type="password" name="password" placeholder="Passowrd" value="" required>
        <input type="submit" value="Register">
    </form>
</div>

</body>
</html>

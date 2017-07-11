<!DOCTYPE html>
<html>
    <head>
        <#include "../header.ftl">
    </head>
    <style>
        th, td {
            padding: 5px;
            text-align: left;
        }
    </style>
    <body>

        <#include "../nav.ftl">

        <div class="container">

            <#if operation_result == "Failed" >
            <p>${error_message!"Project Already Exist"}</p>
            </#if>
            <form id="createIndicatorForm" method="post">
                <fieldset>
                    <legend>Create New Indicator</legend>
                    <table>
                        <tr>
                            <td><label>Name : </label></td>
                            <td><input type="text" name="name" placeholder="Indicator Short Name" value="${name!}" required></td>
                        </tr>
                        <tr>
                            <td><label>Title : </label></td>
                            <td><input type="text" name="title" placeholder="Indicator Full Name" value="${title!}"></td>
                        </tr>
                        <tr>
                            <td><label>EG Code 1 : </label></td>
                            <td><input type="text" name="eg_code1" placeholder="EG Code 1" value="${eg_code1!}"></td>
                        </tr>
                        <tr>
                            <td><label>EG Code 2 : </label></td>
                            <td><input type="text" name="eg_code2" placeholder="EG Code 2" value="${eg_code2!}"></td>
                        </tr>
                        <tr>
                            <td><label>Unit : </label></td>
                            <td><input list="units" name="unit" placeholder="Unit" value="${unit!}"></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Create"></td>
                        </tr>
                    </table>
                </fieldset>
                <datalist id="units">
                    <#list units as x>
                    <option value="${x}">
                    </#list>
                </datalist>
            </form>
        </div>

        <#include "../footer.ftl">
    </body>
</html>

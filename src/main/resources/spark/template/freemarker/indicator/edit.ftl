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
            <form id="createIndicatorForm" method="post">
                <fieldset>
                    <legend>Edit Indicator</legend>

                    <#if operation_result == "Failed" >
                    <p>${error_message!"Indicator Change Failed"}</p>
                    </#if>
                    <table>
                        <tr>
                            <td><label>Name : </label></td>
                            <td><input type="hidden" name="name" value="${name!}" required>${name!}</td>
                        </tr>
                        <tr>
                            <td><label>Title : </label></td>
                            <td><input type="hidden" name="title" value="${title!}" required>${title!}</td>
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
                            <td><input type="submit" value="Save"></td>
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

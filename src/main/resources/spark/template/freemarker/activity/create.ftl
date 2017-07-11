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
            <p>${error_message!"Activity Already Exist"}</p>
            </#if>
            <form id="createActivityForm" method="post">
                <fieldset>
                    <legend>Create New Activity</legend>
                    <table>
                        <tr>
                            <td><label>Project : </label></td>
                            <#if project_name??>
                            <td><input type="hidden" name="project_name" value="${project_name!}" required>${project_name!}</td>
                            <#else>
                            <td>
                                <select name="project_name" required>
                                    <#list projects as x>
                                    <option value="${x}">${x}</option>
                                    </#list>
                                </select>
                            </td>
                            </#if>
                        </tr>
                        <tr>
                            <td><label>Crop : </label></td>
                            <td><input list="crops" name="crop" placeholder="Crop Name" value="${crop!}" required></td>
                        </tr>
                        <tr>
                            <td><label>Technology : </label></td>
                            <td><input list="Technologies" name="tech" placeholder="Technology Code" value="${tech!}" required></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Create"></td>
                        </tr>
                    </table>
                    <datalist id="crops">
                        <#list crops as x>
                        <option value="${x}">
                        </#list>
                    </datalist>
                    <datalist id="Technologies">
                        <#list technologies as x>
                        <option value="${x}">
                         </#list>
                    </datalist>
                </fieldset>
            </form>
        </div>
        <#include "../footer.ftl">

    </body>
</html>

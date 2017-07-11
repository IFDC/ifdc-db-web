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
            <form id="createProjectForm" method="post">
                <fieldset>
                    <legend>Create New Project</legend>
                    <table>
                        <tr>
                            <td><label>Name : </label></td>
                            <td><input type="text" name="name" placeholder="Project Name" value="${name!}" required></td>
                        </tr>
                        
                        <tr>
                            <td><label>Description : </label></td>
                            <td><input type="text" name="description" placeholder="Project Description" value="${description!}"></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Create"></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>

        <#include "../footer.ftl">
    </body>
</html>

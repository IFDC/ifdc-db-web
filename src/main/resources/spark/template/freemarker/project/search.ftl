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
            <div>
                <#if operation_result == "Failed" >
                <p>Empty Result</p>
                </#if>
                <form id="createProjectForm" method="post">
                    <fieldset>
                        <legend>Search New Project</legend>
                        <table>
                            <tr>
                                <td><label>Project : </label></td>
                                <td><input list="projectNames" name="name" placeholder="Project Name" value="${name!}" required></td>
                            </tr>
                            <tr></tr>
                            <tr>
                                <td><input type="submit" value="Search"></td>
                            </tr>
                        </table>
                    </fieldset>
                    <datalist id="projectNames">
                        <#list projects as x>
                        <option value="${x}">
                            </#list>
                    </datalist>
                </form>
                <br />
            </div>
            <div>
                <#if operation_result == "Successed" >
                <ul>
                    <p>Search Result : </p>
                    <#list results as result>
                    <li><a href="project/find?id=${result[id]!}">${result[name]!}</a></li>
                    </#list>
                </ul>
                </#if>
            </div>
        </div>

        <#include "../footer.ftl">
    </body>
</html>

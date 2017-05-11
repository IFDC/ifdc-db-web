<!DOCTYPE html>
<html>
    <head>
        <#include "../header.ftl">
    </head>

    <body>

        <#include "../nav.ftl">

        <div class="container">

            <#if operation_result == "Failed" >
            <p>Activity Already Exist</p>
            <#elseif operation_result == "Succeeded" >
            <p>Activity Created</p>
            </#if>
            <form id="createActivityForm" method="post">
                <fieldset>
                    <legend>Create New M&E Activity</legend>
                    <table>
                        <tr>
                            <td><label>Project : </label></td>
                            <td><input list="projects" name="project" placeholder="Project Name" value="${project!}" required></td>
                        </tr>
                        <tr>
                            <td><label>Crop : </label></td>
                            <td><input list="crops" name="crop" placeholder="Crop Name" value="${crop!}" required></td>
                        </tr>
                        <tr>
                            <td><label>Technology : </label></td>
                            <td><input list="Technologies" name="technology" placeholder="Technology Code" value="${technology!}" required></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Create"></td>
                        </tr>
                    </table>
                    <datalist id="projects">
                        <#list projects as x>
                        <option value="${x}">
                        </#list>
                    </datalist>
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

    </body>
</html>

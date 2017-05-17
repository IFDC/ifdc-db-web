<!DOCTYPE html>
<html>
    <head>
        <#include "../header.ftl">
    </head>
    <style>
        table#t01, table#t01 th, table#t01 td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        table#t01 th, table#t01 td {
            padding: 5px;
            text-align: left;
        }
        th#descCol {
            width: 600px;
        }
        table#t01 {
            width: 100%;
        }
        table#t01 tr:nth-child(even) {
            background-color: #eee;
        }
        table#t01 tr:nth-child(odd) {
            background-color:#fff;
        }
        table#t01 th {
            background-color: lightskyblue;
            color: black;
        }
    </style>

    <body>

        <#include "../nav.ftl">

        <div class="container">

            <fieldset>
                <legend>Project List</legend>
                <#if operation_result == "Failed" >
                <p>No Project Exists</p>
                <#else>
                <table id="t01">
                    <tr>
                        <th>Name</th>
                        <th id="descCol">Description</th>
                        <th>Option</th>
                    </tr>
                    <#list projects as project>
                    <tr>
                        <td><a href="/project/find?id=${project["_id"]["$oid"]!}">${project["name"]!}</a></td>
                        <td>${project["description"]!}</td>
                        <td><a href="/activity/create?project_name=${project["name"]!}" class="btn btn-default">Add Activity</a></td>
                    </tr>
                    </#list>
                </table>
                </#if>
            </fieldset>
        </div>

        <#include "../footer.ftl">
    </body>
</html>

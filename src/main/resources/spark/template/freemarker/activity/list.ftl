<!DOCTYPE html>
<html>
    <head>
        <#include "../header.ftl">
    </head>
    <style>
        td {
            padding: 5px;
            text-align: left;
        }
        table#t02, table#t02 th, table#t02 td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        table#t02 th, table#t02 td {
            padding: 5px;
            text-align: left;
        }
        table#t02 {
            width: 50%;
        }
        table#t02 tr:nth-child(even) {
            background-color: #eee;
        }
        table#t02 tr:nth-child(odd) {
            background-color:#fff;
        }
        table#t02 th {
            background-color: lightskyblue;
            color: black;
        }
    </style>
    <body>

        <#include "../nav.ftl">

        <div class="container">

            <fieldset>
                <legend>Activity List</legend>
                <#if operation_result == "Failed" >
                <p>Activity Record Not Found</p>
                <#else>
                <table id="t02">
                    <tr>
                        <th><label>Project</label></th>
                        <th><label>Crop</label></th>
                        <th><label>Technology</label></th>
                        <th><label>M&E Report Option</label></th>
                    </tr>
                    <#list avtivities as activity>
                    <tr>
                        <td>${activity["project_name"]!}</td>
                        <td>${activity["crop"]!}</td>
                        <td>${activity["tech"]!}</td>
                        <td>
                            <a href="/report/edit?project_name=${activity["project_name"]!}&crop=${activity["crop"]!}&tech=${activity["tech"]!}" class="btn btn-default">Edit</a>
                            <a href="/report/view?project_name=${activity["project_name"]!}&crop=${activity["crop"]!}&tech=${activity["tech"]!}" class="btn btn-default">View</a>
                        </td>
                    </tr>
                    </#list>
                </table>
                </#if>
            </fieldset>

        </div>

        <#include "../footer.ftl">
    </body>
</html>

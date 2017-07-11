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
            width: 400px;
        }
        table#t01 {
            width: 90%;
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
                <legend>Indicator List</legend>
                <#if operation_result == "Failed" >
                <p>No Indicator Exists</p>
                <#else>
                <table id="t01">
                    <tr>
                        <th>Short Name</th>
                        <th id="descCol">Full Name</th>
                        <th>Eg Code 1</th>
                        <th>Eg Code 2</th>
                        <th>Unit</th>
                        <th>Option</th>
                    </tr>
                    <#list indicators as indicator>
                    <tr>
                        <td>${indicator["name"]!}</td>
                        <td>${indicator["title"]!}</td>
                        <td>${indicator["eg_code1"]!}</td>
                        <td>${indicator["eg_code2"]!}</td>
                        <td>${indicator["unit"]!}</td>
                        <td><a href="/indicator/edit?name=${indicator["name"]!}" class="btn btn-default">Edit</a></td>
                    </tr>
                    </#list>
                </table>
                </#if>
            </fieldset>
        </div>

        <#include "../footer.ftl">
    </body>
</html>

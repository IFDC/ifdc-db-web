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
        .meta {
        }
        .indi {
            width: 80px;
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
                <legend>M&E Report List</legend>
                <#if operation_result == "Failed" >
                <p>Report Record Not Found</p>
                <#else>
                <table id="t02">
                    <tr>
                        <th class="meta"><label>Project</label></th>
                        <th class="meta"><label>Crop</label></th>
                        <th class="meta"><label>Technology</label></th>
                        <th class="meta"><label>year</label></th>
                        <#list indicators as indicator>
                        <th class="indi"><label>${indicator["title"]!} <#if indicator["unit"]??>(${indicator["unit"]})</#if></label></th>
                        </#list>
                    </tr>
                    <#list reports as report>
                    <tr>
                        <td>${report["project_name"]!}</td>
                        <td>${report["crop"]!}</td>
                        <td>${report["tech"]!}</td>
                        <td>${report["year"]!}</td>
                        <#list indicators as indicator>
                        <td>${report[indicator["name"]]!}</td>
                        </#list>
                    </tr>
                    </#list>
                </table>
                </#if>
            </fieldset>

        </div>

        <#include "../footer.ftl">
    </body>
</html>

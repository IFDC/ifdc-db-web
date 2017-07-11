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
        table#t01 {
            width: 30%;
        }
        table#t02, table#t02 th, table#t02 td {
            border: 1px solid black;
            border-collapse: collapse;
        }
        table#t02 th, table#t02 td {
            padding: 5px;
            text-align: center;
        }
        table#t02 {
            width: 30%;
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
                <legend>Activity Info</legend>
                <#if operation_result == "Failed" >
                <p>Activity Record Not Found</p>
                <#else>
                <form id="editReportForm" method="post">
                    <div>
                        <table id="t01">
                            <tr>
                                <td><label>Project : </label></td>
                                <td><input type="hidden" name="project_name" value="${project_name!}" required>${project_name!}</td>
                            </tr>
                            <tr>
                                <td><label>Crop : </label></td>
                                <td><input type="hidden" name="crop" value="${crop!}" required>${crop!}</td>
                            </tr>
                            <tr>
                                <td><label>Technology : </label></td>
                                <td><input type="hidden" name="tech" value="${tech!}" required>${tech!}</td>
                            </tr>
                        </table>
                    </div>
                    <br/>
                    <a href="/report/edit?project_name=${project_name!}&crop=${crop!}&tech=${tech!}" class="btn btn-default">Edit Report</a>
                    <br/>
                    <br/>
                    <div>
                        <fieldset>
                            <legend>M&E Report</legend>
                            <table id="t02">
                                <caption>Activity Indicators</caption>
                                <tr>
                                    <th>Year</th>
                                    <#list indicators as indicator>
                                    <th>${indicator["title"]} <#if indicator["unit"]??>(${indicator["unit"]})</#if></th>
                                    </#list>
                                </tr>
                                <#list years as year>
                                <tr>
                                    <td>${year}</td>
                                    <#list indicators as indicator>
                                    <td>${indicatorValues[year][indicator["name"]]!}</td>
                                    </#list>
                                </tr>
                                </#list>
                            </table>
                        </fieldset>
                    </div>
                    <br/>
                </form>
                </#if>
            </fieldset>

        </div>

        <#include "../footer.ftl">
    </body>
</html>

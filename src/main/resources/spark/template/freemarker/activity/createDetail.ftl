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
        table#t01 {
            width: 30%;
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

            <#if operation_result == "Failed" >
            <p>Activity Already Exist</p>
            <#elseif operation_result == "Succeeded" >
            <p>Activity Created</p>
            </#if>
            <form id="createActivityDetailForm" method="post">
                <fieldset>
                    <legend>Create New M&E Activity</legend>
                    <input type="hidden" name="detail_flg" value="addDetail">
                    <input type="hidden" name="project" value="${project!}">
                    <input type="hidden" name="crop" value="${crop!}">
                    <input type="hidden" name="technology" value="${technology!}">
                    <table>
                        <tr>
                            <td><label>Project : </label></td>
                            <td><label>${project!}</label></td>
                        </tr>
                        <tr>
                            <td><label>Crop : </label></td>
                            <td><label>${crop!}</label></td>
                        </tr>
                        <tr>
                            <td><label>Technology : </label></td>
                            <td><label>${technology!}</label></td>
                        </tr>
                    </table>
                    <br/>
                    <table>
                        <tr>
                            <td><label>Year : </label></td>
                            <td><input type="number" name="year" min="1960" max="2017" value="${year!}"></td>
                        </tr>
                        <tr>
                            <td><label>Indicator : </label></td>
                            <td><input list="indicators" name="indicator" value="${indicator!}"></td>
                        </tr>
                        <tr>
                            <td><label>Value : </label></td>
                            <td><input type="number" name="value" value="${value!}"></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Add"></td>
                        </tr>
                    </table>
                    <datalist id="indicators">
                        <#list allIndicators as x>
                        <option value="${x}">
                        </#list>
                    </datalist>
                </fieldset>
            </form>
            <br/>
            <table id="t01">
                <caption>Activity Indicators</caption>
                <tr>
                    <th>Year</th>
                    <#list indicators as x>
                    <th>${x}</th>
                    </#list>
                    <th>...</th>
                </tr>
                <#list years as y>
                <tr>
                    <td>${y}</td>
                    <#list indicators as x>
                    <td>${indicatorValues[y][x]!}</td>
                    </#list>
                    <td></td>
                </tr>
                </#list>
        </div>

    </body>
</html>
